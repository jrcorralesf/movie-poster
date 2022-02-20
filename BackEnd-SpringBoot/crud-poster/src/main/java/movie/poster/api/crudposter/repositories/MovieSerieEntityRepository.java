package movie.poster.api.crudposter.repositories;

import movie.poster.api.crudposter.entities.MovieSerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieSerieEntityRepository extends JpaRepository<MovieSerieEntity, UUID> {
}