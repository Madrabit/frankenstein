package ru.madrabit.frankenstein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.madrabit.frankenstein.config.ApplicationConfiguration;
import ru.madrabit.frankenstein.database.entity.Company;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;
import ru.madrabit.frankenstein.database.repository.CrudRepository;
import ru.madrabit.frankenstein.service.CompanyService;

@SpringBootApplication
public class ApplicationRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println(context);
    }

}
