package br.com.marcondesmacaneiro.domain.repository;

import br.com.marcondesmacaneiro.domain.model.Pessoa;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcondes
 */
@Service
@Transactional(readOnly = true)
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Optional<Pessoa> findOne(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Pessoa pessoa) {
        repository.delete(pessoa);
    }

    public Optional<Pessoa> findByEmail(String mail) {
        System.out.println("E-mail: " + mail);
        return Optional.ofNullable(repository.findByMail(mail));
    }

}
