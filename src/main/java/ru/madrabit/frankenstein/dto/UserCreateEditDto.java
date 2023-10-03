package ru.madrabit.frankenstein.dto;

import lombok.Value;
import ru.madrabit.frankenstein.database.entity.Roles;

import java.time.LocalDate;

@Value
public class UserCreateEditDto {
    String username;
    String firstname;
    String lastname;
    LocalDate birthDate;
    Roles role;
    Integer companyId;
}
