package ru.madrabit.frankenstein.database.pool;

public class CompanyRepository {
    private final ConnectionPool connectionPool;

    public CompanyRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public static CompanyRepository of(ConnectionPool pool) {
        return new CompanyRepository(pool);
    }
}
