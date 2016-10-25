package br.com.marcondesmacaneiro.domain.repository;

import br.com.marcondesmacaneiro.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@RepositoryRestResource(collectionResourceRel = "enderecos", itemResourceRel = "endereco" , path = "endereco")
interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
