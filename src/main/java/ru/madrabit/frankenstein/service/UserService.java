package ru.madrabit.frankenstein.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.madrabit.frankenstein.bpp.Transaction;
import ru.madrabit.frankenstein.database.entity.User;
import ru.madrabit.frankenstein.database.repository.CompanyRepository;
import ru.madrabit.frankenstein.database.repository.UserRepository;
import ru.madrabit.frankenstein.dto.UserCreateEditDto;
import ru.madrabit.frankenstein.dto.UserFilter;
import ru.madrabit.frankenstein.dto.UserReadDTO;
import ru.madrabit.frankenstein.mapper.UserCreatEditMapper;
import ru.madrabit.frankenstein.mapper.UserReadMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreatEditMapper creatEditMapper;


    public List<UserReadDTO> findAll(UserFilter filter) {
        return userRepository.findAllByFilter(filter).stream()
                .map(userReadMapper::map)
                .toList();
    }
    public List<UserReadDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDTO create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(creatEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDTO> update(Long id, UserCreateEditDto editDtoUser) {
        return
                userRepository.findById(id)
                        .map(user -> creatEditMapper.map(editDtoUser, user))
                        .map(userRepository::saveAndFlush)
                        .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                }).orElse(false);
    }

}
