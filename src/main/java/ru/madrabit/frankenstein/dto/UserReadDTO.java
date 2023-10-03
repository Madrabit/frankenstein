package ru.madrabit.frankenstein.dto;

import lombok.Value;
import ru.madrabit.frankenstein.database.entity.Roles;

import java.time.LocalDate;

@Value
public class UserReadDTO {
    Long id;
    String username;
    String lastname;
    LocalDate birthDate;
    Roles role;
    CompanyReadDto companyReadDto;
}
