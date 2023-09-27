package ru.madrabit.frankenstein.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Test
    void checkPageable() {
        PageRequest pageRequest = PageRequest.of(1, 2, Sort.by("id"));
        List<User> result = userRepository.findAllBy(pageRequest);
    }

    @Test
    void checkSort() {
        Sort.TypedSort<User> sortedBy = Sort.sort(User.class);
        Sort sort = sortedBy.by(User::getFirstname).and(sortedBy.by(User::getLastname));
        List<User> allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort);
        assertThat(allUsers).hasSize(3);
    }


}