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
import java.util.Date;
import java.util.Objects;
import org.springframework.hateoas.core.Relation;

/**
 * Created by marcondesmacaneiro on 17/10/16.
 */
@Entity
@Table(name = "tb_cliente")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "cliente", collectionRelation = "clientes")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class Cliente implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @SequenceGenerator(name = "gen_cliente_id", sequenceName = "seq_cliente_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_cliente_id")
    private Long id;

    @NotNull
    @Column(length = 50, insertable = true, updatable = true)
    @Size(min = 5, max = 50)
    private String nome;

    @NotNull
    @Column(length = 14, insertable = true, updatable = true)
    @Size(min = 14, max = 14)
    private String cpf;

    @Column(length = 50, insertable = true, updatable = true)
    @Size(max = 50)
    private String email;

    @Column(length = 50, insertable = true, updatable = true)
    private String bairro;

    @Column(length = 50, insertable = true, updatable = true)
    private String rua;

    @Column(length = 10, insertable = true, updatable = true)
    private String numero;

    @Column(length = 20, insertable = true, updatable = true)
    private String telefone;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @ManyToOne(optional = false)
    private Municipio municipio;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Cliente(String nome, String cpf, String email, String bairro, String rua, String numero, String telefone, Municipio municipio, Date dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.telefone = telefone;
        this.municipio = municipio;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

}
