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
 * Created by marcondesmacaneiro on 10/10/16.
 */
@Entity
@Table(name = "tb_cidade")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "cidade", collectionRelation = "cidades")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class Cidade implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_cidade_id", sequenceName = "seq_cidade_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_cidade_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String nome;
    
    @ManyToOne(optional = false)
    private Estado estado;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Cidade(String nome) {
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

    public static Cidade of(String logradouro) {
        return new Cidade(logradouro);
    }
}
