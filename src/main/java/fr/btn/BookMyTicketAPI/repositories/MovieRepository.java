package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.entities.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Long>, PagingAndSortingRepository<MovieEntity, Long> {
    Iterable<MovieEntity> findMoviesByGenresId(Long idGenre);
}
