package ru.madrabit.frankenstein.database.pool;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component("pool1")
public class ConnectionPool  {
    @Value("${db.username}")
    private final   String username;
    @Value("${db.pool.size}")
    private final   Integer poolSize;
//    private final List<Object> args;
//
//
//
//    public void setProperties(Map<String, Object> properties) {
//        this.properties = properties;
//    }
//
//    private Map<String, Object> properties;


    @PostConstruct
    private void init() {
        System.out.println("Init connection pool");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Close connection pool");
    }


}
