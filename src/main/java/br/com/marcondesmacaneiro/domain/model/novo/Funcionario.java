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
 * Created by marcondesmacaneiro on 17/10/16.
 */
@Entity
@Table(name = "tb_funcionario")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "funcionario", collectionRelation = "funcionarios")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class Funcionario implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @SequenceGenerator(name = "gen_funcionario_id", sequenceName = "seq_funcionario_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_funcionario_id")
    private Long id;

    @NotNull
    @Column(length = 14, insertable = true, updatable = true)
    @Size(min = 14, max = 14)
    private String cpf;
    
    @NotNull
    @Column(length = 50, insertable = true, updatable = true)
    @Size(min = 5, max = 50)
    private String nome;
    
    @Column(length = 100, insertable = true, updatable = true)
    private String endereco;
    
    @Column(length = 50, insertable = true, updatable = true)
    private String email;
    
    @Column(insertable = true, updatable = true)
    private boolean usuario;
    
    @NotNull
    @Column(length = 20, insertable = true, updatable = true)
    @Size(min = 8, max = 20)
    private String login;
    
    @NotNull
    @Column(length = 100, insertable = true, updatable = true)
    @Size(min = 20, max = 100)
    private String senha;
    
    @Column(insertable = true, updatable = true)
    private Boolean confirmaSenha;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Funcionario(String cpf, String nome, String endereco, String email, boolean usuario) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.usuario = usuario;
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

}
