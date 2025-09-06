package com.matcarv.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application class for the MatCarv application.
 */
@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
@EntityScan( basePackages = {"com.matcarv.app.entities"})
@EnableJpaRepositories(basePackages = { "com.matcarv.app.repository"})
@ComponentScan(basePackages = {"com.matcarv.app"})
public class Application {

    /**
     * Main method to run the Spring Boot application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
