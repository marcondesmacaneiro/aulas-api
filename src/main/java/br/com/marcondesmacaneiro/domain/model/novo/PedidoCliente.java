package br.com.marcondesmacaneiro.domain.model.novo;

import br.com.marcondesmacaneiro.domain.model.novo.Produto;
import java.io.Serializable;
import java.util.Objects;
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
 * Created by marcondesmacaneiro on 17/10/16.
 */
@Entity
@Table(name = "tb_pedido_cliente")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "pedido", collectionRelation = "pedidos")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class PedidoCliente implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @SequenceGenerator(name = "gen_pedido_id", sequenceName = "seq_pedido_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_pedido_id")
    private Long id;

    private Cliente cliente;
    private Produto produto;
    private int status;
    private double quantidade;
    private double precoVenda;

    private PedidoCliente(Cliente cliente, Produto produto, int status, double quantidade, double precoVenda) {
        this.cliente = cliente;
        this.produto = produto;
        this.status = status;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

}
