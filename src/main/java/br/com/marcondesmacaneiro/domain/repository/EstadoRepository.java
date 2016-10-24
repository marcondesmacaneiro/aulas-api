package br.com.marcondesmacaneiro.domain.repository;

import br.com.marcondesmacaneiro.domain.model.Estado;
import br.com.marcondesmacaneiro.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@RepositoryRestResource(collectionResourceRel = "estados", itemResourceRel = "estado" , path = "estado")
interface EstadoRepository extends JpaRepository<Estado, Long> {
}
