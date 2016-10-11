package br.com.marcondesmacaneiro.domain.model;

import br.com.marcondesmacaneiro.domain.vo.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Email;
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
import java.util.List;
import java.util.Objects;
import org.springframework.hateoas.core.Relation;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@Entity
@Table(name = "tb_endereco")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "endereco", collectionRelation = "enderecos")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "logradouro"})
public class Endereco implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_endereco_id", sequenceName = "seq_endereco_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_endereco_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String logradouro;
    
    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Endereco(String logradouro) {
        this.logradouro = logradouro;
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

    public static Endereco of(String logradouro) {
        return new Endereco(logradouro);
    }
}
