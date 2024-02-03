package com.tycode.ecm.ms_shop;

import com.tycode.ecm.shared.domain.Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableScheduling
@ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
        basePackages = "com.tycode.ecm.ms_shop,com.tycode.ecm.shop,com.tycode.ecm.shared"
)
@EnableJpaRepositories(basePackages = "com.tycode.ecm.shop.*")
@EntityScan("com.tycode.ecm.shop.*")
public class ShopApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to ECM-SHOP API...");
    }
}