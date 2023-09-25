package ru.madrabit.frankenstein.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;
import ru.madrabit.frankenstein.integration.service.annotation.IT;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserServiceTest {

    private final UserService userService;
    private final ConnectionPool pool1;
    @Test
    public void test() {
        System.out.println("UserServiceTest console");
    }

}