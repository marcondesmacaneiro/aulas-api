package br.com.marcondesmacaneiro.view;

import br.com.marcondesmacaneiro.domain.exception.EntityAreadyExistException;
import br.com.marcondesmacaneiro.domain.model.Pessoa;
import br.com.marcondesmacaneiro.domain.repository.PessoaService;
import br.com.marcondesmacaneiro.domain.vo.Phone;
import java.util.List;
import static java.util.Objects.nonNull;
import java.util.function.Consumer;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcondes
 */
@RestController
@RequestMapping("/api/pessoa")
public class PessoaRestController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaRestController.class);

    @Autowired
    private PessoaService service;

    @Autowired
    private PagedResourcesAssembler<Pessoa> pagedResourcesAssembler;

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Pessoa>> findAll() {
        return ok(service.findAll());
    }

    @RequestMapping(method = GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Pessoa> findOne(@PathVariable Long id) {

        Pessoa pessoa = service.findOne(id)
                .orElseThrow(EntityAreadyExistException.entityAreadyExist("A Pessoa não existe!"));;

        return ok(pessoa);
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    @CrossOrigin
        public ResponseEntity<Void> gravar(@Valid @RequestBody Pessoa pessoa) {

//        Pessoa existentPessoa = service.findByEmail(pessoa.getMail())
//                .orElseThrow(EntityAreadyExistException.entityAreadyExist("Pessoa já existe!"));

        pessoa = service.save(pessoa);

        return noContent().build();
    }

    @RequestMapping(method = PATCH, value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Void> edit(@PathVariable Long id,
            @Valid @RequestBody PessoaPatchInput input,
            HttpServletRequest request) {

        Pessoa pessoa = service.findOne(id)
                .orElseThrow(EntityAreadyExistException.entityAreadyExist("Pessoa não existe!"));

        input.accept(pessoa);

        service.save(pessoa);

        return noContent().build();
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    @CrossOrigin
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        Pessoa pessoa = service.findOne(id)
                .orElseThrow(EntityAreadyExistException.entityAreadyExist("Pessoa não existe!"));

        service.delete(pessoa);

        return noContent().build();
    }

    static @Data
    class PessoaPatchInput implements Consumer<Pessoa> {

        @NotNull
        @Size(min = 1, max = 100)
        private String name;

        @Email(message = "Invalid mail address!")
        @Size(min = 1, max = 100)
        private String mail;

        @NotNull
        private Phone phone;

        @Override
        public void accept(Pessoa pessoa) {
            if (nonNull(name)) {
                pessoa.setName(name);
            }
            if (nonNull(mail)) {
                pessoa.setMail(mail);
            }
            if (nonNull(phone)) {
                pessoa.setPhone(phone);
            }
        }
    }

}
