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
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.core.Relation;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Entity
@Table(name = "tb_pessoa")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "pessoa", collectionRelation = "pessoas")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "name", "mail", "phone"})
@RestResource(exported = true)
public class Pessoa implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_pessoa_id", sequenceName = "seq_pessoa_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_pessoa_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull
    @Email(message = "Invalid mail address!")
    @Size(min = 1, max = 100)
    @Column(length = 100)
    private String mail;

    @Column(nullable = false, unique = true, length = Phone.MAX_LENGHT)
    private Phone phone;

    @ManyToMany
    @JoinTable(name = "tb_pessoa_endereco",
            joinColumns = {
                @JoinColumn(name = "pessoa_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "endereco_id")})
    private List<Endereco> endereco;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Pessoa(String name, String mail, Phone phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public Long getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

    public static Pessoa of(String name, String mail, Phone phone) {
        return new Pessoa(name, mail, phone);
    }
}
