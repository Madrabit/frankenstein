package ru.madrabit.frankenstein.database.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import ru.madrabit.frankenstein.database.entity.User;
import ru.madrabit.frankenstein.database.querydsl.QPredicates;
import ru.madrabit.frankenstein.dto.UserFilter;

import javax.persistence.EntityManager;
import java.util.List;

import static ru.madrabit.frankenstein.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }
}
