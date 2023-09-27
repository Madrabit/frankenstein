package ru.madrabit.frankenstein.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.madrabit.frankenstein.database.repository.CompanyRepository;
import ru.madrabit.frankenstein.database.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;


}
