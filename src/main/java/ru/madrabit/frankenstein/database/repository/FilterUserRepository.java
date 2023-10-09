package ru.madrabit.frankenstein.database.repository;

import ru.madrabit.frankenstein.database.entity.Roles;
import ru.madrabit.frankenstein.database.entity.User;
import ru.madrabit.frankenstein.dto.PersonalInfo;
import ru.madrabit.frankenstein.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Roles role);

    void UpdateCompanyAndRoleNamed(List<User> users);
}
