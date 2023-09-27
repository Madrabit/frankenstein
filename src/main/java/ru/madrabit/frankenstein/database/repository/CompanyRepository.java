package ru.madrabit.frankenstein.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import ru.madrabit.frankenstein.database.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {


    Optional<Company> findByName(String name);


    Optional<List<Company>> findAllByNameContainingIgnoreCase(String fragment);

}
