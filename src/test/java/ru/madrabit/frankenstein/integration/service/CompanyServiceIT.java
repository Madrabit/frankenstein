package ru.madrabit.frankenstein.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.madrabit.frankenstein.ApplicationRunner;
import ru.madrabit.frankenstein.config.DatabaseProperties;
import ru.madrabit.frankenstein.database.entity.Company;
import ru.madrabit.frankenstein.database.repository.CrudRepository;
import ru.madrabit.frankenstein.dto.CompanyReadDto;
import ru.madrabit.frankenstein.integration.service.annotation.IT;
import ru.madrabit.frankenstein.listener.entity.EntityEvent;
import ru.madrabit.frankenstein.service.CompanyService;
import ru.madrabit.frankenstein.service.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@IT
@RequiredArgsConstructor
public class CompanyServiceIT {
    public static final Integer COMPANY_ID = 1;


    private final CompanyService companyService;

    private final DatabaseProperties databaseProperties;


    @Test
    void findById() {
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);
        assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));



    }
}
