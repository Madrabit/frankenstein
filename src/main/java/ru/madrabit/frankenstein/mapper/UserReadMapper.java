package ru.madrabit.frankenstein.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.madrabit.frankenstein.database.entity.User;
import ru.madrabit.frankenstein.dto.CompanyReadDto;
import ru.madrabit.frankenstein.dto.UserReadDTO;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDTO> {
    private final CompanyReadDtoMapper companyReadDtoMapper;
    @Override
    public UserReadDTO map(User object) {
        CompanyReadDto companyReadDto = Optional.ofNullable(object.getCompany())
                .map(companyReadDtoMapper::map)
                .orElse(null);

        return new UserReadDTO(
                object.getId(),
                object.getUsername(),
                object.getLastname(),
                object.getBirthDate(),
                object.getRole(),
                companyReadDto);
    }
}
