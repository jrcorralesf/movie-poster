package movie.poster.api.crudposter.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
          name = "UUID",
          strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "created_by", updatable = false, nullable = false)
  @CreatedBy
  private String createdBy;

  @Column(name = "modified_by", nullable = false)
  @LastModifiedBy
  private String modifiedBy;

  @Column(name = "created_at", updatable = false, nullable = false)
  @CreatedDate
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(name = "modified_at", nullable = false)
  @LastModifiedDate
  private LocalDateTime modifiedAt = LocalDateTime.now();

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  @Override
  public final int hashCode() {
    return id.hashCode();
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj)  return true;
    if ( obj == null || getClass() != obj.getClass() )  return false;
    AuditEntity other = (AuditEntity) obj;
    return Objects.equals(id, other.getId());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getName()).append(" [");
    sb.append("id=").append(id).append("]");
    return sb.toString();
  }

}
