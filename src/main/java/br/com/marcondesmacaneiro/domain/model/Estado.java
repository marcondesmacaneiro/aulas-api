package br.com.marcondesmacaneiro.domain.model;

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
import org.springframework.hateoas.core.Relation;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Entity
@Table(name = "tb_estado")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "estado", collectionRelation = "estados")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "sigla", "nome"})
public class Estado implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_estado_id", sequenceName = "seq_estado_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_estado_id")
    private Long id;

    @NotNull
    @Size(min = 2, max = 2)
    @Column(nullable = false, length = 2, name = "uf")
    private String sigla;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Estado(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
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

    public static Estado of(String sigla, String nome) {
        return new Estado(sigla, nome);
    }
}
