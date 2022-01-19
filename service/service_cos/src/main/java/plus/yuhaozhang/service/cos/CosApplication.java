package plus.yuhaozhang.service.cos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Yuh Z
 * @date 1/19/22
 */
@SpringBootApplication
@ComponentScan(basePackages = {"plus.yuhaozhang"})
public class CosApplication {
    public static void main(String[] args) {
        SpringApplication.run(CosApplication.class,args);
    }
}
