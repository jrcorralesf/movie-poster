package movie.poster.api.crudposter.services;

import movie.poster.api.crudposter.entities.MovieSerieEntity;
import movie.poster.api.crudposter.entities.dto.MovieSerieEntityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface MovieSerieService {
    MovieSerieEntity saveOnDb(MovieSerieEntityDto dataInDto);
    MovieSerieEntity updateOnDb(MovieSerieEntityDto dataInDto);
    void sofDelete (UUID idToDelete);
    Page<MovieSerieEntityDto> listPage(Pageable pageable);
}
