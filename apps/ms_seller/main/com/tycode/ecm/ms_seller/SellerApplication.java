package com.tycode.ecm.ms_seller;

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
        basePackages = "com.tycode.ecm.ms_seller,com.tycode.ecm.seller,com.tycode.ecm.shared"
)
@EnableJpaRepositories(basePackages = "com.tycode.ecm.seller.*")
@EntityScan("com.tycode.ecm.seller.*")
public class SellerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SellerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to ECM-SELLER API...");
    }
}