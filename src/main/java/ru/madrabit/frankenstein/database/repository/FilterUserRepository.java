package ru.madrabit.frankenstein.database.repository;

import ru.madrabit.frankenstein.database.entity.User;
import ru.madrabit.frankenstein.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);
}
