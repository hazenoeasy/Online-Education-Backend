//package generator;
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//
//import java.util.Collections;
//
///**
// * @author Yuh Z
// * @date 1/17/22
// */
//public class CodeGeneratorNew {
//    static final String URL = "jdbc:mysql://localhost:3306/education";
//    public static void main(String[] args) {
//        String projectPath = System.getProperty("user.dir");//获取项目路径
//        FastAutoGenerator.create(URL, "root", "ZYHzyh1217")
//                //全局配置
//                .globalConfig(builder -> {
//                    builder.author("Yuh Z")
//                            .outputDir("/Users/apple/Projects/Online-Education-Backend/service/service_edu/src/main/java")//输出路径
//                            .enableSwagger()//开启swagger3
//                            .fileOverride()//覆盖文件
//                            .disableOpenDir();//不打开文件夹
//                })
//                //包名配置
//                .packageConfig(builder -> {
//                    builder.parent("plus.yuhaozhang.service")
//                            .moduleName("edu")
//                            .service("service")
//                            .serviceImpl("service.impl")
//                            .controller("controller")
//                            .entity("entity")
//                            .mapper("mapper")
//                            //自定义输出路径，mapper.xml生成到resources目录下
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper"));
//                })
//                //策略配置
//                .strategyConfig(builder -> {
//                    builder.addInclude("edu_teacher")
//                            //.addTablePrefix("edu_")//表前缀
//                            .serviceBuilder().formatServiceFileName("%sService")//去掉Service的 "I" 前缀
//                            .controllerBuilder().enableRestStyle()//restful开启
//                            .enableHyphenStyle()//url改变 例如：index_id_1
//                            .entityBuilder().enableLombok();//开启lombok
//                })
//                //执行
//                .execute();
//    }
//}
