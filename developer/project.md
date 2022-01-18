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
## bug
* Swagger3 一直调试失败 应该和guava有关系，而且swagger和springboot兼容性很不好 后续采用springdoc-openapi 替代. 但因为mybatis plus generator 不能生成open-api 的注释，所以最终选择降低springboot版本，使用swagger2 开发

