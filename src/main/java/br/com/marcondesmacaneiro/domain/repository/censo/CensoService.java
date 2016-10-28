package br.com.marcondesmacaneiro.domain.repository.censo;

import br.com.marcondesmacaneiro.domain.model.censo.Censo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcondes
 */
@Service
@Transactional(readOnly = true)
public class CensoService {

    @Autowired
    private CensoRepository repository;

    public List<Censo> findAll() {
        return repository.findAll();
    }

    public Optional<Censo> findOne(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    public List<Censo> findAll(Integer id) {
        return repository.findAll(Specifications.where(Censo.coletorEqualsTo(id)));
    }

    

}
