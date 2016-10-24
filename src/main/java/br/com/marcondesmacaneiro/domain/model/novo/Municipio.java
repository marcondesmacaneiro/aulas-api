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
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.hateoas.core.Relation;

/**
 *
 * @author marcondesmacaneiro 17/10/2016
 */
@Entity
@Table(name = "tb_municipio")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "municipio", collectionRelation = "municipios")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class Municipio implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @SequenceGenerator(name = "gen_municipio_id", sequenceName = "seq_municipio_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_municipio_id")
    private Long id;

    @NotNull
    @Column(name = "codigo_ibge", length = 10, unique = true, updatable = true, insertable = true)
    private int codigoIbge;

    @NotNull
    @Column(name = "descricao", length = 100, insertable = true, updatable = true)
    @Size(min = 1, max = 100)
    private String descricao;

    @NotNull
    @Column(name = "uf", length = 2, insertable = true, updatable = true)
    @Size(min = 2, max = 2)
    private String uf;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Municipio(int codigoIbge, String descricao, String uf) {
        this.codigoIbge = codigoIbge;
        this.descricao = descricao;
        this.uf = uf;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

    public static Municipio of(int codigoIbge, String descricao, String uf) {
        return new Municipio(codigoIbge, descricao, uf);
    }

}
