package ru.madrabit.frankenstein.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.madrabit.frankenstein.database.entity.User;
import ru.madrabit.frankenstein.database.repository.UserRepository;
import ru.madrabit.frankenstein.integration.service.annotation.IT;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {


    private final UserRepository userRepository;
    @Test
    void findAllBy() {
        List<User> users = userRepository.findAllBy("%a%", "%ov%");
        assertThat(users).hasSize(3);
        System.out.println(users);
    }
}