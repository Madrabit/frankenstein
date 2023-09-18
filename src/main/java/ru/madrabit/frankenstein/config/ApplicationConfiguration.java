package ru.madrabit.frankenstein.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;
import ru.madrabit.frankenstein.database.repository.UserRepository;
import ru.madrabit.frankenstein.web.config.WebConfiguration;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.madrabit.frankenstein")
@Import(WebConfiguration.class)
public class ApplicationConfiguration {

    @Bean("pool2")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String username) {
        return new ConnectionPool(username, 20);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("test-username", 25);
    }


    @Bean
    public UserRepository userRepository(ConnectionPool pool2) {
        return new UserRepository(pool2);
    }

    @Bean
    public UserRepository userRepository3() {
        return new UserRepository(pool3());
    }
}

