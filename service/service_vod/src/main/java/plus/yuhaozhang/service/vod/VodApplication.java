package plus.yuhaozhang.service.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Yuh Z
 * @date 1/28/22
 */
@SpringBootApplication
@ComponentScan(basePackages = {"plus.yuhaozhang"})
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class,args);
    }
}