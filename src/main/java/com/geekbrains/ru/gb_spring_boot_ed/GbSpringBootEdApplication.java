package com.geekbrains.ru.gb_spring_boot_ed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class GbSpringBootEdApplication {

    public static void main(String[] args) {
        SpringApplication.run(GbSpringBootEdApplication.class, args);
    }

}
