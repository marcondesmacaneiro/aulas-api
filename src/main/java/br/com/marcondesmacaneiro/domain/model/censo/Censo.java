package br.com.marcondesmacaneiro.domain.model.censo;

import br.com.marcondesmacaneiro.domain.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.core.Relation;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@Entity
@Table(name = "tb_censo")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "censo", collectionRelation = "censos")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "coletor", "dados"})
public class Censo implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_censo_id", sequenceName = "seq_censo_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_censo_id")
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Min(1000)
    @Max(100000)
    private Integer coletor;

    @NotNull
    @Size(min = 1, max = 400)
    @Column(nullable = false, length = 400)
    private String dados;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Censo(Integer coletor, String dados) {
        this.coletor = coletor;
        this.dados = dados;
    }

    @JsonIgnore
    @Override
    public Long getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

    public static Censo of(Integer coletor, String dados) {
        return new Censo(coletor, dados);
    }

    public static Specification<Censo> coletorEqualsTo(Integer coletor) {
        return (root, query, builder) -> builder.equal(root.get(Censo_.coletor), coletor);
    }
}
