package ru.madrabit.frankenstein.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.support.TransactionTemplate;
import ru.madrabit.frankenstein.database.entity.Company;
import ru.madrabit.frankenstein.database.repository.CompanyRepository;
import ru.madrabit.frankenstein.integration.IntegrationTestBase;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
//@Commit
class CompanyRepositoryTest extends IntegrationTestBase {

    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;
    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(tx -> {
            try {
                Company company = entityManager.find(Company.class, 1);
                assertNotNull(company);
                assertThat(company.getLocales()).hasSize(2);
            } catch (Exception e) {
                // Свой эксепшен, иначе будет выбрасываться транзакционный неизвестный
                throw new RuntimeException(e);
            }
        });

    }

    @Test
    void checkFindByQueries() {
    entityManager.createNamedQuery("findByName", Company.class)
            .setParameter("name", "apple").getResultList();

        companyRepository.findAllByNameContainingIgnoreCase("a");
    }

    @Test
    void save() {
        Company company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                )).build();
        entityManager.persist(company);
        assertNotNull(company);
    }

    @Test
    @Disabled
    void delete() {
        Optional<Company> company = companyRepository.findById(13);
        assertTrue(company.isPresent());
        company.ifPresent(company1 -> companyRepository.delete(company1));
        entityManager.flush();
    }
}