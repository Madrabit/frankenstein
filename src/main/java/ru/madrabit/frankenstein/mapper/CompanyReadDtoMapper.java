package ru.madrabit.frankenstein.mapper;

import org.springframework.stereotype.Component;
import ru.madrabit.frankenstein.database.entity.Company;
import ru.madrabit.frankenstein.dto.CompanyReadDto;

import java.util.Map;
@Component
public class CompanyReadDtoMapper implements Mapper<Company, CompanyReadDto> {
    @Override
    public CompanyReadDto map(Company object) {
        return new CompanyReadDto(
                object.getId(),
                object.getName());
    }
}
