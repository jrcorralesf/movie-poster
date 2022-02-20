package movie.poster.api.crudposter.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted_at IS NULL")
@Table(name = "MOVIE_SERIE", schema = "public")
@SQLDelete(sql = "UPDATE MOVIE_SERIE SET deleted_at=now() WHERE id=?")
public class MovieSerieEntity extends AuditEntity{

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    @NotNull(message = "The name cannot be empty")
    @Size(max = 50, message = "the maximum number of characters is 50")
    private String name;

    @Column(name = "description", columnDefinition = "VARCHAR(100)")
    @Size(max = 100, message = "the max length  is 100")
    private String description;

    @Column(name = "url_image", columnDefinition = "VARCHAR(500)")
    @Size(max = 500, message = "the max length  is 500")
    private String imageUrl;

    @Column(name = "rate_value", columnDefinition = "Decimal(2,1)")
    private double rate;

    @Column(name = "release_date")
        private LocalDate releaseDate;
}
