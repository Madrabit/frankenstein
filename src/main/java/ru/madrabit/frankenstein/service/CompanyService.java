package ru.madrabit.frankenstein.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.madrabit.frankenstein.database.entity.Company;
import ru.madrabit.frankenstein.database.repository.CrudRepository;
import ru.madrabit.frankenstein.dto.CompanyReadDto;
import ru.madrabit.frankenstein.listener.entity.AccessType;
import ru.madrabit.frankenstein.listener.entity.EntityEvent;

import java.util.Optional;

@Service
public class CompanyService {
    private final CrudRepository<Integer, Company> crudRepository;
    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    public CompanyService(UserService userService, CrudRepository crudRepository, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.crudRepository = crudRepository;
        this.publisher = publisher;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return crudRepository.findById(id)
                .map(
                        company -> {
                            publisher.publishEvent(new EntityEvent(company, AccessType.READ));
                            return new CompanyReadDto(company.id());
                        });
    }
}
