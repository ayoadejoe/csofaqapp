package com.iqjoy.csofaq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class CsofaqApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsofaqApplication.class, args);
    }

}
