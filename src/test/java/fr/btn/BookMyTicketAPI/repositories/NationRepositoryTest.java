package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.TestDataUtils;
import fr.btn.BookMyTicketAPI.entities.NationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
class NationRepositoryTest {
    private NationRepository underTest;

    @Autowired
    public NationRepositoryTest(NationRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatNationCanBeCreatedAndRetrieved() {
        NationEntity nationEntity = TestDataUtils.createNationEntity("FR", "France");
        underTest.save(nationEntity);

        Optional<NationEntity> result = underTest.findById(nationEntity.getCode());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(nationEntity);
    }

    @Test
    public void testThatMultipleNationsCanBeCreatedAndRetrieved() {
        NationEntity nationEntityA = TestDataUtils.createNationEntity("FR", "France");
        underTest.save(nationEntityA);

        NationEntity nationEntityB = TestDataUtils.createNationEntity("VN", "Vietnam");
        underTest.save(nationEntityB);

        NationEntity nationEntityC = TestDataUtils.createNationEntity("KR", "Korea");
        underTest.save(nationEntityC);

        Iterable<NationEntity> resultSet = underTest.findAll();

        assertThat(resultSet).hasSize(3);
        assertThat(resultSet).contains(nationEntityA, nationEntityB, nationEntityC);
    }

    @Test
    public void testThatPersonCanBeUpdated() {
        NationEntity nationEntity = TestDataUtils.createNationEntity("FR", "Franceeeee");
        underTest.save(nationEntity);

        nationEntity.setName("France");
        underTest.save(nationEntity);

        Optional<NationEntity> updatedNationEntity = underTest.findById(nationEntity.getCode());

        assertThat(updatedNationEntity).isPresent();
        assertThat(updatedNationEntity.get()).isEqualTo(nationEntity);
    }

    @Test
    public void testThatNationCanBeDeleted() {
        NationEntity nationEntity = TestDataUtils.createNationEntity("ZZ", "Test Nation");
        underTest.save(nationEntity);

        underTest.deleteById(nationEntity.getCode());

        Optional<NationEntity> deletedNation = underTest.findById(nationEntity.getCode());

        assertThat(deletedNation).isNotPresent();
    }
}