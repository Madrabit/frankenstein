package ru.madrabit.frankenstein.dto;

import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;
import ru.madrabit.frankenstein.database.entity.Roles;
import ru.madrabit.frankenstein.validation.group.CreateAction;
import ru.madrabit.frankenstein.validation.UserInfo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo(groups = CreateAction.class)
public class UserCreateEditDto {
    @Email
    String username;
    @NotBlank(groups = CreateAction.class)
    String rawPassword;
    @Size(min = 3, max = 64)
    String firstname;
    String lastname;
    LocalDate birthDate;
    Roles role;
    Integer companyId;
    MultipartFile image;
}

