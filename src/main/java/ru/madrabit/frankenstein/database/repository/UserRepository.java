package ru.madrabit.frankenstein.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.madrabit.frankenstein.database.entity.User;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "where u.firstname like :firstName and u.lastname like :lastName")
    List<User> findAllBy(String firstName, String lastName);

    @Query(nativeQuery = true, value = "SELECT * FROM users u WHERE u.username = :username")
    List<User> findAllByUsername(String username);
}
