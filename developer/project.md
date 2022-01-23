## 项目结构

* 父工程： pom类型，公共依赖，管理依赖版本
    * 子模块

## 数据库

* 库名与应用名称尽量一致
* 表名，字段名必须使用小写字母或者数字，禁止出现数字开头
* 表名不能用复数
* 表名最好是 业务_表作用
* 表必须有id,gmt_create,gmt_modified 三个字段
    * 单表 自增 类型为bigint unsigned
    * 集群 类型为varchar, 非自增，业务中使用分布式id生成器
        * 前端long和后端long的长度不一样，用bigint会出问题 出现精度损失
* 单表超过500万行，才考虑分表
* 表达是否概念,必须使用is_xxx的方式命名，数据类型是unsigned tinyint(1 表示是,0 表示否)
* 小数类型为decimal， 禁止使用float 和 double
* 如果存储的字符串长度几乎相同，使用char定长字符串类型
* varchar是可变长字符串，
* 唯一索引名为uk_字段名，普通索引名为 idx_字段名
* 不得使用外键与级联，一切外键概念必须在应用层解决，外键与级联更新适合单机低并发，不适合分布式，高并发，级联是强阻塞，外键影响数据库插入速度

## 讲师开发

* 逻辑删除： 设置删除标识位置，而非真正删除。 通过mybatis-plus 对实体类添加@TableLogic 实现
* 不同模块，为了扫描包，需要在启动类上加@ComponentScan(basePackages = {"sd"})


* Swagger3 一直调试失败 应该和guava有关系，而且swagger和springboot兼容性很不好 后续采用springdoc-openapi 替代. 但因为mybatis plus generator
  不能生成open-api 的注释，所以最终选择降低springboot版本，使用swagger2 开发


* mybatis 对server也做了丰富，基本操作最好在server中进行，复杂的sql在mapper中进行
* StringUtils.isEmpty 会判断是否为空字符串

## COS

* 常量两种方式 
  * 第一种，使用PropertiesLoaderUtils.loadProperties 读取 properties 文件
  * 第二种，使用常量类 @value 继承 initializingbean

## Nginx 配置 实现反向代理

nginx 根据url判断转发对象
/edu/xxx

## 数据模型

* PO/DO: Persistent  Object 持久数据 对应数据库 一个表一个对象
* VO: Value Object view object 业务层之间的数据传递
* DAO: Data access object DAO 中包含了各种数据库的操作方法。通过它的方法 , 结合 PO 对数据库进行相关的操作。夹在业务逻辑与数据库资源中间。
* DTO：data trasfer object，数据传输对象。主要用于远程调用等需要大量传输对象的地方。 只把部分数据封装起来传输， 要是传输给客户端，就是vo层了

vo -> controller -> dto -> service -> po -> database
databast -> po -> service -> DTO

## EasyExcel bug
默认依赖有问题  
需要额外引入poi  
```xml
 <dependency>
                <groupId>org.apache.poi</groupId>
                <artifactId>poi</artifactId>
                <version>5.0.0</version>
            </dependency>

            <dependency>
                <groupId>org.apache.poi</groupId>
                <artifactId>poi-ooxml</artifactId>
                <version>5.0.0</version>
            </dependency>
            <dependency>
                <groupId>org.apache.poi</groupId>
                <artifactId>poi-scratchpad</artifactId>
                <version>5.0.0</version>
            </dependency>
```

## Mybatis-plus 
* Mybatis框架会调用这个默认构造方法来构造实例对象，即实体类需要通过Mybatis进行动态反射生成。 反射的Class.forName("className").newInstance();需要对应的类提供一个无参构造函数。
  * 如果在类中没有提供任何构造方法，虚拟机会自动提供默认构造方法（无参构造器），但是如果提供了其他有参数的构造方法的话，虚拟机就不再为提供默认构造方法，所以默认的构造方法不是必须的，只在有多个构造方法时才是必须的显式声明的。

code at org.apache.ibatis.executor.resultset.DefaultResultSetHandler.

```java
 private Object createByConstructorSignature(ResultSetWrapper rsw, Class<?> resultType, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) throws SQLException {
        Constructor<?>[] constructors = resultType.getDeclaredConstructors();
        Constructor<?> defaultConstructor = this.findDefaultConstructor(constructors);
        if (defaultConstructor != null) {
            return this.createUsingConstructor(rsw, resultType, constructorArgTypes, constructorArgs, defaultConstructor);
        } else {
            Constructor[] var7 = constructors;
            int var8 = constructors.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                Constructor<?> constructor = var7[var9];
                if (this.allowedConstructorUsingTypeHandlers(constructor, rsw.getJdbcTypes())) {
                    return this.createUsingConstructor(rsw, resultType, constructorArgTypes, constructorArgs, constructor);
                }
            }

            throw new ExecutorException("No constructor found in " + resultType.getName() + " matching " + rsw.getClassNames());
        }
    }
```
 
mybatis会查看所有构造器，然后跟数据库参数进行匹配