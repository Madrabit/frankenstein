package ru.madrabit.frankenstein.database.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import ru.madrabit.frankenstein.bpp.Auditing;
import ru.madrabit.frankenstein.bpp.InjectBean;
import ru.madrabit.frankenstein.bpp.Transaction;
import ru.madrabit.frankenstein.database.entity.Company;
import ru.madrabit.frankenstein.database.pool.ConnectionPool;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    //    @Qualifier("pool1")
    private ConnectionPool pool1;
    @Autowired
    private List<ConnectionPool> pools;
    @Value("${db.pool.size}")
    private Integer poolSize;

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
