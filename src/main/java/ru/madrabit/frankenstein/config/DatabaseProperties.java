package ru.madrabit.frankenstein.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;
import java.util.Map;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "db")
public class DatabaseProperties {
    String username;
    String password;
    String driver;
    String url;
    String host;
    PoolProperties pool;
    List<PoolProperties> pools;
    Map<String, Object> properties;

    @Value
    public static class PoolProperties {
        Integer size;
        Integer timeout;
    }
}
