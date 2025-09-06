package com.matcarv.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

/**
 * Classe principal da aplicação MatCarv.
 * Responsável por inicializar o contexto Spring Boot.
 *
 * @author Weslley Matos
 */
@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
@EntityScan( basePackages = {"com.matcarv.app.entities"})
@EnableJpaRepositories(basePackages = { "com.matcarv.app.repository"})
@ComponentScan(basePackages = {"com.matcarv.app"})
public class Application {

    /**
     * Método principal para inicializar a aplicação Spring Boot.
     *
     * @param args argumentos de linha de comando
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
