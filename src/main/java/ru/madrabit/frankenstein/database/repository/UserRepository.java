package ru.madrabit.frankenstein.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.madrabit.frankenstein.database.entity.Roles;
import ru.madrabit.frankenstein.database.entity.User;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;
import ru.madrabit.frankenstein.dto.PersonalInfo;
import ru.madrabit.frankenstein.dto.PersonalInfo2;

import javax.management.relation.Role;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>, FilterUserRepository {

    @Query("select u from User u " +
            "where u.firstname like :firstName and u.lastname like :lastName")
    List<User> findAllBy(String firstName, String lastName);

    @Query(nativeQuery = true, value = "SELECT * FROM users u WHERE u.username = :username")
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u set u.role = :role where u.id in (:ids)")
    int updateRole(Roles role, Long... ids);

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

    @EntityGraph(attributePaths = {"company", "company.locales"})
    @Query(value = "select u from User u",
    countQuery = "select count(distinct u.firstname) from User u"
    )
    Page<User> findAllBy(Pageable pageable);

//    List<PersonalInfo> findAllByCompanyId(Integer companyId);

    <T>List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);


    @Query(nativeQuery = true,
    value = "SELECT firstname, lastname, birth_date birthDate FROM users WHERE company_id = :companyId")
    List<PersonalInfo2> findAllByCompanyId(Integer companyId);

}
