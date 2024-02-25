package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.TestDataUtils;
import fr.btn.BookMyTicketAPI.domain.entities.PersonEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonRepositoryIntegrationTests {
    private PersonRepository underTest;

    @Autowired
    public PersonRepositoryIntegrationTests(PersonRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatPersonCanBeCreatedAndRetrieved() {
        PersonEntity personEntity = TestDataUtils.createPersonEntity(1L, "Johnny", "Depp");
        underTest.save(personEntity);

        Optional<PersonEntity> result = underTest.findById(personEntity.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(personEntity);
    }

    @Test
    public void testThatMultiplePeopleCanBeCreatedAndRetrieved() {
        PersonEntity personEntityA = TestDataUtils.createPersonEntity(1L, "Johnny", "Depp");
        underTest.save(personEntityA);

        PersonEntity personEntityB = TestDataUtils.createPersonEntity(2L, "Dakota", "Johnson");
        underTest.save(personEntityB);

        PersonEntity personEntityC = TestDataUtils.createPersonEntity(3L, "Jean", "Reno");
        underTest.save(personEntityC);

        Iterable<PersonEntity> resultSet = underTest.findAll();

        assertThat(resultSet).hasSize(3);
        assertThat(resultSet).contains(personEntityA, personEntityB, personEntityC);
    }

    @Test
    public void testThatPersonCanBeUpdated() {
        PersonEntity personEntity = TestDataUtils.createPersonEntity(1L, "Johnnyyyyy", "Depp");
        underTest.save(personEntity);

        personEntity.setFirstname("Johnny");
        underTest.save(personEntity);

        Optional<PersonEntity> updatedPersonEntity = underTest.findById(personEntity.getId());

        assertThat(updatedPersonEntity).isPresent();
        assertThat(updatedPersonEntity.get()).isEqualTo(personEntity);
    }

    @Test
    public void testThatPersonCanBeDeleted() {
        PersonEntity personEntity = TestDataUtils.createPersonEntity(1L, "Johnny", "Depp");
        underTest.save(personEntity);

        underTest.deleteById(personEntity.getId());

        Optional<PersonEntity> deletedPerson = underTest.findById(personEntity.getId());

        assertThat(deletedPerson).isNotPresent();
    }

    @Test
    public void testThatPersonCanBeRetrievedByFirstnameAndLastname() {
        PersonEntity personEntityA = TestDataUtils.createPersonEntity(1L, "Johnny", "Depp");
        underTest.save(personEntityA);
        PersonEntity personEntityB = TestDataUtils.createPersonEntity(2L, "Johnny", "NotDepp");
        underTest.save(personEntityB);

        Optional<PersonEntity> result = underTest.findByFirstnameAndLastname("Johnny", "Depp");

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(personEntityA);

    }

}
