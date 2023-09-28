package ru.madrabit.frankenstein.database.repository;

import lombok.RequiredArgsConstructor;
import ru.madrabit.frankenstein.database.entity.User;
import ru.madrabit.frankenstein.dto.UserFilter;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        criteria.select(user);
        List<Predicate> predicates = new ArrayList<>();
        if (filter.firstname() != null) {
            predicates.add(cb.like(user.get("firstname"), filter.firstname()));
        }
        if (filter.lastname() != null) {
            predicates.add(cb.like(user.get("lastname"), filter.lastname()));
        }
        if (filter.birthDate() != null) {
            predicates.add(cb.lessThan(user.get("birthDate"), filter.birthDate()));
        }
        criteria.where(predicates.toArray(Predicate[]::new));
        return entityManager.createQuery(criteria).getResultList();
    }
}
