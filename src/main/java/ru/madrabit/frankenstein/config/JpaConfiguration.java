package ru.madrabit.frankenstein.config;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import ru.madrabit.frankenstein.config.conditional.JpaConditional;

import javax.annotation.PostConstruct;

@Conditional(JpaConditional.class)
@Configuration
public class JpaConfiguration {

    @PostConstruct
    public void init() {
        System.out.println("Jpa Configuration is enabled");
    }
}
