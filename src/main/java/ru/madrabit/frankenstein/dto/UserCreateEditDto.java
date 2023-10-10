package ru.madrabit.frankenstein.dto;

import lombok.Value;
import lombok.experimental.FieldNameConstants;
import ru.madrabit.frankenstein.database.entity.Roles;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@FieldNameConstants
public class UserCreateEditDto {
    @Email
    String username;
    @NotNull
    @Size(min = 3, max = 64)
    String firstname;
    @NotNull
    String lastname;
    LocalDate birthDate;
    Roles role;
    Integer companyId;
}
