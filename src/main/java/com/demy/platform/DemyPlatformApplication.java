package com.demy.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DemyPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemyPlatformApplication.class, args);
    }

}
