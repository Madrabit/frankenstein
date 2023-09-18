package ru.madrabit.frankenstein;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.madrabit.frankenstein.database.pool.CompanyRepository;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");){
            ConnectionPool connectionPool = context.getBean("p1", ConnectionPool.class);
            System.out.println(connectionPool);
            CompanyRepository companyRepository = context.getBean("companyRepository", CompanyRepository.class);
            System.out.println(companyRepository);
        }
    }

}
