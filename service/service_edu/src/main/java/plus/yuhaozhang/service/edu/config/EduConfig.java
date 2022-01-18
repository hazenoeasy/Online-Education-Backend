package plus.yuhaozhang.service.edu.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yuh Z
 * @date 1/17/22
 */
@Configuration
@MapperScan("plus.yuhaozhang.service.edu.mapper")
public class EduConfig {

    /**
     * 逻辑删除插件
     */
    //新版本 通过注释实现
    //@Bean
    //public ISqlInjector sqlInjector() {
    //    return new LogicSqlInjector();
    //}

    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}