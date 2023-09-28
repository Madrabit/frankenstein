package ru.madrabit.frankenstein.database.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.madrabit.frankenstein.database.entity.Roles;
import ru.madrabit.frankenstein.database.entity.User;
import ru.madrabit.frankenstein.database.querydsl.QPredicates;
import ru.madrabit.frankenstein.dto.PersonalInfo;
import ru.madrabit.frankenstein.dto.UserFilter;

import javax.persistence.EntityManager;
import java.util.List;

import static ru.madrabit.frankenstein.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private static final String FIND_BY_COMPANY_AND_ROLE = """
                SELECT 
                firstname,
                lastname,
                birth_date
                FROM users
                WHERE company_id = ? AND
                role = ?
            """;
    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

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

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Roles role) {
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE, (rs, rowNum) ->
                new PersonalInfo(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birth_date").toLocalDate()
                ), companyId, role.name());
    }
}
