package ru.madrabit.frankenstein;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.madrabit.frankenstein.config.ApplicationConfiguration;
import ru.madrabit.frankenstein.database.entity.Company;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;
import ru.madrabit.frankenstein.database.repository.CrudRepository;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()){
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();
            ConnectionPool connectionPool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool);
            CrudRepository<Integer, Company> companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));

        }
   }

}
