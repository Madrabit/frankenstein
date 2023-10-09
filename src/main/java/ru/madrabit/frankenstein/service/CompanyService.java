package ru.madrabit.frankenstein.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.madrabit.frankenstein.database.repository.CompanyRepository;
import ru.madrabit.frankenstein.dto.CompanyReadDto;
import ru.madrabit.frankenstein.listener.entity.AccessType;
import ru.madrabit.frankenstein.listener.entity.EntityEvent;
import ru.madrabit.frankenstein.mapper.CompanyReadDtoMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher publisher;
    private final CompanyReadDtoMapper companyReadDtoMapper;

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(
                        company -> {
                            publisher.publishEvent(new EntityEvent(company, AccessType.READ));
                            return new CompanyReadDto(company.getId(), null);
                        });
    }

    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll().stream()
                .map(companyReadDtoMapper::map)
                .toList();
    }
}
