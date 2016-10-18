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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.hateoas.core.Relation;

/**
 * Created by marcondesmacaneiro on 17/10/16.
 */
@Entity
@Table(name = "tb_produto")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "produto", collectionRelation = "produtos")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class Produto implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @SequenceGenerator(name = "gen_produto_id", sequenceName = "seq_produto_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_produto_id")
    private Long id;

    @NotNull
    @Column(length = 50, insertable = true, updatable = true)
    @Size(min = 8, max = 50)
    private String descricao;

    @NotNull
    @Min(0)
    @Max(10000)
    private double valorCusto;

    @NotNull
    @Column(insertable = true, updatable = true)
    @Min(0)
    private int estoque;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Produto(int id, String descricao, double valorCusto, int estoque, Categoria categoria) {
        this.descricao = descricao;
        this.valorCusto = valorCusto;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

}
