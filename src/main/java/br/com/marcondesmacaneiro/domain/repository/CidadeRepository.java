package br.com.marcondesmacaneiro.domain.repository;

import br.com.marcondesmacaneiro.domain.model.Cidade;
import br.com.marcondesmacaneiro.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "cidades", itemResourceRel = "cidade" , path = "cidade")
interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
