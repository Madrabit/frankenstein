package ru.madrabit.frankenstein.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.madrabit.frankenstein.config.DatabaseProperties;
import ru.madrabit.frankenstein.dto.CompanyReadDto;
import ru.madrabit.frankenstein.integration.service.annotation.IT;
import ru.madrabit.frankenstein.service.CompanyService;

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
