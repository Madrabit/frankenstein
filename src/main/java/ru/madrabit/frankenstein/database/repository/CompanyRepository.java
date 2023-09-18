package ru.madrabit.frankenstein.database.repository;

import ru.madrabit.frankenstein.bpp.Auditing;
import ru.madrabit.frankenstein.bpp.InjectBean;
import ru.madrabit.frankenstein.bpp.Transaction;
import ru.madrabit.frankenstein.database.entity.Company;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    @InjectBean
    private ConnectionPool connectionPool;

    @PostConstruct
    private void init() {
        System.out.println("Init company repository");
    }
    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method...");
    }
}
