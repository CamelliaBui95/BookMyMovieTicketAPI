package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.TestDataUtils;
import fr.btn.BookMyTicketAPI.entities.ProdCrewEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProdCrewRepositoryTest {
    private ProdCrewRepository underTest;

    @Autowired
    public ProdCrewRepositoryTest(ProdCrewRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatProdCrewCanBeCreatedAndRetrieved() {
        ProdCrewEntity prodCrewEntity = TestDataUtils.createProdCrewEntity(1L, "Johnny Depp");
        underTest.save(prodCrewEntity);

        Optional<ProdCrewEntity> result = underTest.findById(prodCrewEntity.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(prodCrewEntity);
    }

    @Test
    public void testThatMultipleProdCrewCanBeCreatedAndRetrieved() {
        ProdCrewEntity prodCrewEntityA = TestDataUtils.createProdCrewEntity(1L, "Johnny Depp");
        underTest.save(prodCrewEntityA);

        ProdCrewEntity prodCrewEntityB = TestDataUtils.createProdCrewEntity(2L, "Dakota Johnson");
        underTest.save(prodCrewEntityB);

        ProdCrewEntity prodCrewEntityC = TestDataUtils.createProdCrewEntity(3L, "Jean Reno");
        underTest.save(prodCrewEntityC);

        Iterable<ProdCrewEntity> resultSet = underTest.findAll();

        assertThat(resultSet).hasSize(3);
        assertThat(resultSet).contains(prodCrewEntityA, prodCrewEntityB, prodCrewEntityC);
    }

    @Test
    public void testThatProdCrewCanBeUpdated() {
        ProdCrewEntity prodCrewEntity = TestDataUtils.createProdCrewEntity(1L, "Johnnyyyyyy Depp");
        underTest.save(prodCrewEntity);

        prodCrewEntity.setName("Johnny Depp");
        underTest.save(prodCrewEntity);

        Optional<ProdCrewEntity> updated = underTest.findById(prodCrewEntity.getId());

        assertThat(updated).isPresent();
        assertThat(updated.get()).isEqualTo(prodCrewEntity);
    }

    @Test
    public void testThatProdCrewCanBeDeleted() {
        ProdCrewEntity prodCrewEntity = TestDataUtils.createProdCrewEntity(1L, "Johnny Depp");
        underTest.save(prodCrewEntity);

        underTest.deleteById(prodCrewEntity.getId());

        Optional<ProdCrewEntity> deleted = underTest.findById(prodCrewEntity.getId());

        assertThat(deleted).isNotPresent();
    }

    @Test
    public void testThatProdCrewCanBeRetrievedByName() {
        ProdCrewEntity prodCrewEntityA = TestDataUtils.createProdCrewEntity(1L, "Johnny Depp");
        underTest.save(prodCrewEntityA);
        ProdCrewEntity prodCrewEntityB = TestDataUtils.createProdCrewEntity(2L, "Johnny NotDepp");
        underTest.save(prodCrewEntityB);

        Optional<ProdCrewEntity> result = underTest.findByName("Johnny Depp");

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(prodCrewEntityA);

    }
}