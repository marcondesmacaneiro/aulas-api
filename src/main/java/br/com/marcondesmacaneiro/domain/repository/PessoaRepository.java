package br.com.marcondesmacaneiro.domain.repository;

import br.com.marcondesmacaneiro.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@RepositoryRestResource(collectionResourceRel = "pessoas", itemResourceRel = "pessoa" , path = "pessoa")
interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
    Pessoa findByMail(String mail);
    
}
