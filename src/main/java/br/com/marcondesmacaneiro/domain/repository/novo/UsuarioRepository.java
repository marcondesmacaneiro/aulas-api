package br.com.marcondesmacaneiro.domain.repository.novo;

import br.com.marcondesmacaneiro.domain.model.novo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by marcondesmacaneiro on 18/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "usuarios", itemResourceRel = "usuario" , path = "usuario")
interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
