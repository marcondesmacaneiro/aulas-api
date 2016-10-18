package br.com.marcondesmacaneiro.domain.model.novo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.hateoas.core.Relation;

/**
 * Created by marcondesmacaneiro on 17/10/16.
 */
@Entity
@Table(name = "tb_categoria")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "categoria", collectionRelation = "categorias")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "descricao"})
public class Categoria implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @SequenceGenerator(name = "gen_categoria_id", sequenceName = "seq_categoria_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_categoria_id")
    private Long id;

    @NotNull
    @Column(length = 30, insertable = true, updatable = true)
    private String descricao;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

}
