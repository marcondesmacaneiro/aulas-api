package br.com.example.domain.model;

import br.com.example.domain.vo.Phone;
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
import java.util.Objects;
import org.springframework.hateoas.core.Relation;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */

@Entity
@Table(name = "tb_person")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "person", collectionRelation = "persons")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "name", "mail", "phone"})
public class Person implements Serializable, Persistable<Long>, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "gen_person_id", sequenceName = "seq_person_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_person_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @Email(message = "Invalid mail address!")
    @Size(min = 1, max = 100)
    @Column(length = 100)
    private String mail;

    @NotNull
    @Column(nullable = false, unique = true, length = Phone.MAX_LENGHT)
    private Phone phone;

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedTime;

    private Person(String name, Phone phone) {
        this.name = name;
        this.phone = phone;
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

    public static Person of(String name, Phone phone) {
        return new Person(name, phone);
    }
}
