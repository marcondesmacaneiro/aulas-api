package br.com.marcondesmacaneiro.domain.model.novo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.hateoas.core.Relation;

/**
 * Created by marcondesmacaneiro on 17/10/16.
 */
@Entity
@Table(name = "tb_pedido")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "pedido", collectionRelation = "pedidos")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class Pedido implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @SequenceGenerator(name = "gen_pedido_id", sequenceName = "seq_pedido_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_pedido_id")
    private Long id;

    @ManyToOne(optional = false)
    private Cliente cliente;
    
    @ManyToMany
    @JoinTable(name = "tb_pedido_produto",
            joinColumns = {
                @JoinColumn(name = "pedido_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "produto_id")})
    private List<Produto> produtos;

    
    private int status;
    private double valorTotal;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Pedido(Cliente cliente, int status, double quantidade, double valorTotal) {
        this.cliente = cliente;
        this.status = status;
        this.valorTotal = valorTotal;
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

}
