package movie.poster.api.crudposter.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Data
public class MovieSerieEntityDto implements Serializable {
    private UUID id;
    @NotNull(message = "The name cannot be empty")
    @Size(max = 50, message = "the maximum number of characters is 50")
    private String name;
    @Size(max = 100, message = "the max length  is 100")
    private String description;
    @Size(max = 500, message = "the max length  is 500")
    private String imageUrl;
    private double rate;
    private LocalDate releaseDate;
}
