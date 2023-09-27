package ru.madrabit.frankenstein.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.madrabit.frankenstein.database.entity.Roles;
import ru.madrabit.frankenstein.database.entity.User;
import ru.madrabit.frankenstein.database.repository.UserRepository;
import ru.madrabit.frankenstein.integration.service.annotation.IT;

import java.time.LocalDate;
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

    @Test
    void checkUpdate() {
        User ivan = userRepository.getById(1L);
        assertSame(Roles.ADMIN, ivan.getRole());
        ivan.setBirthDate(LocalDate.now());

        int resultCount = userRepository.updateRole(Roles.USER, 1L, 5L);
        assertSame(2, resultCount);

        User theSameIvan = userRepository.getById(1L);
        assertSame(Roles.USER, theSameIvan.getRole());


    }
}