package ru.madrabit.frankenstein.database.repository;

import org.springframework.stereotype.Repository;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;

@Repository
public class UserRepository {
    private final ConnectionPool connectionPool;

    public UserRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
