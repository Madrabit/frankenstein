package ru.madrabit.frankenstein.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import ru.madrabit.frankenstein.database.entity.Roles;
import ru.madrabit.frankenstein.dto.UserCreateEditDto;
import ru.madrabit.frankenstein.dto.UserReadDTO;
import ru.madrabit.frankenstein.integration.IntegrationTestBase;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@RequiredArgsConstructor
class UserServiceTest extends IntegrationTestBase {

    private final UserService userService;
    private final Long USER_1 = 1L;
    private final Integer COMPANY_1 = 1;

    @Test
    public void findAll() {
        List<UserReadDTO> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    public void findById() {
        Optional<UserReadDTO> user = userService.findById(USER_1);
        assertThat(user.isPresent());
        user.ifPresent(userReadDTO -> assertEquals("ivan@gmail.com", userReadDTO.getUsername()));
    }

    @Test
    public void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "Test",
                "Test",
                "Test",
                LocalDate.now(),
                Roles.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );
        UserReadDTO actualResult = userService.create(userDto);
        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
        assertSame(userDto.getRole(), actualResult.getRole());
    }

    @Test
    public void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "Test",
                "Test",
                "Test",
                LocalDate.now(),
                Roles.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );
        Optional<UserReadDTO> actualResult = userService.update(USER_1, userDto);
        assertThat(actualResult.isPresent());
        actualResult.ifPresent(user ->  {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
            assertSame(userDto.getRole(), user.getRole());
        });
    }

    @Test
    public void delete() {
       assertFalse(userService.delete(-123142L));
       assertTrue(userService.delete(USER_1));
    }

}