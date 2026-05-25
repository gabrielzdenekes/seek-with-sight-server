package com.seek_with_sight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class SeekWithSightApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeekWithSightApplication.class, args);
    }
}
