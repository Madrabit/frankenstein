package ru.madrabit.frankenstein;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.madrabit.frankenstein.config.ApplicationConfiguration;
import ru.madrabit.frankenstein.database.entity.Company;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;
import ru.madrabit.frankenstein.database.repository.CrudRepository;
import ru.madrabit.frankenstein.service.CompanyService;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()){
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();
            ConnectionPool connectionPool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool);
            CompanyService companyService = context.getBean("companyService", CompanyService.class);
            System.out.println(companyService.findById(1));

        }
   }

}
