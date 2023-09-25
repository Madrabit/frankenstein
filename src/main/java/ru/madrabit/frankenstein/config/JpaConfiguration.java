package ru.madrabit.frankenstein.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import ru.madrabit.frankenstein.config.conditional.JpaConditional;

import javax.annotation.PostConstruct;

@Slf4j
@Conditional(JpaConditional.class)
@Configuration
public class JpaConfiguration {

//    @Bean
//    @ConfigurationProperties("db")
//    public DatabaseProperties databaseProperties() {
//        return new DatabaseProperties();
//    }
    @PostConstruct
    public void init() {
        log.info("Jpa Configuration is enabled");
    }
}
