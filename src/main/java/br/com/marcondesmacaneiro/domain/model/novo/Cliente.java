package br.com.marcondesmacaneiro.domain.model.novo;

import br.com.marcondesmacaneiro.domain.model.novo.Municipio;
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
@Table(name = "tb_municipio")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Relation(value = "municipio", collectionRelation = "municipios")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class Cliente implements Serializable, Persistable<Long>, Identifiable<Long> {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @JsonIgnore
    @SequenceGenerator(name = "gen_usuario_id", sequenceName = "seq_usuario_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_usuario_id")
    private Long id;
    
    private String nome;
    private String cpf;
    private String email;
    private String bairro;
    private String rua;
    private String numero;
    private String telefone;
    private Municipio municipio;
    private String dataNascimento;
    
    private Cliente(String nome, String cpf, String email, String bairro, String rua, String numero, String telefone, Municipio municipio, String dataNascimento) {
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
