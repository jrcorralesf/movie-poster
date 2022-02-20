package movie.poster.api.crudposter.repositories;

import movie.poster.api.crudposter.entities.MovieSerieEntity;
import movie.poster.api.crudposter.entities.dto.MovieSerieEntityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieSerieEntityRepository extends JpaRepository<MovieSerieEntity, UUID> {

    Page<MovieSerieEntityDto> findAllProjectedBy (Pageable pageable);
}