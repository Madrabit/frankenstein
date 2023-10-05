package ru.madrabit.frankenstein.dto;

import lombok.Value;
import lombok.experimental.FieldNameConstants;
import ru.madrabit.frankenstein.database.entity.Roles;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class UserCreateEditDto {
    String username;
    String firstname;
    String lastname;
    LocalDate birthDate;
    Roles role;
    Integer companyId;
}
