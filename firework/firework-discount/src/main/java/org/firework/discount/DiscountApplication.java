package org.firework.discount;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("org.firework.crud.mapper")
@ComponentScan(value = {"org.firework"})
@EnableDubbo
@ImportResource(value = "classpath:spring-dubbo-provider.xml")
public class DiscountApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscountApplication.class, args);
    }
}
