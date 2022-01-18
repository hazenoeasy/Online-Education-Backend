package plus.yuhaozhang.service.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Yuh Z
 * @date 1/17/22
 */
@SpringBootApplication
@ComponentScan(basePackages = {"plus.yuhaozhang"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
