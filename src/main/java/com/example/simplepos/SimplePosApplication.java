package com.example.simplepos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // Enable JPA Auditing
public class SimplePosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimplePosApplication.class, args);
    }

}
