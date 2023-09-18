package ru.madrabit.frankenstein;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.madrabit.frankenstein.database.repository.CompanyRepository;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;
import ru.madrabit.frankenstein.database.repository.CrudRepository;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");){
            ConnectionPool connectionPool = context.getBean("p1", ConnectionPool.class);
            System.out.println(connectionPool);
            CrudRepository companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));

        }
   }

}
