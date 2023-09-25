package ru.madrabit.frankenstein.integration.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;

@TestConfiguration
public class TestApplicationRunner {
    @SpyBean(name = "pool1")
    private ConnectionPool pool1;
}
