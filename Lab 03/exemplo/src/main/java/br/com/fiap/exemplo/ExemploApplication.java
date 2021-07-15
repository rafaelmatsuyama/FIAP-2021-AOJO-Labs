package br.com.fiap.exemplo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ExemploApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExemploApplication.class, args);
    }

}
