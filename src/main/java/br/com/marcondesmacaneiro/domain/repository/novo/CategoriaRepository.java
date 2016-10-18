package br.com.marcondesmacaneiro.domain.repository.novo;

import br.com.marcondesmacaneiro.domain.model.novo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by marcondesmacaneiro on 18/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "categorias", itemResourceRel = "categoria" , path = "categoria")
@CrossOrigin
interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
