package com.copeinca.apicopeincaprov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ApicopeincaprovApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApicopeincaprovApplication.class, args);
    }

}
