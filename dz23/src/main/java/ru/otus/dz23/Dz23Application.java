package ru.otus.dz23;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableMongoRepositories
public class Dz23Application {

    public static void main(String[] args) {
        SpringApplication.run(Dz23Application.class, args);
    }
}
