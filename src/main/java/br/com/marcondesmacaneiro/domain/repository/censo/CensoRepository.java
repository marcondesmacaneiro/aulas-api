package br.com.marcondesmacaneiro.domain.repository.censo;

import br.com.marcondesmacaneiro.domain.model.censo.Censo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "censos", itemResourceRel = "censo", path = "censo")
interface CensoRepository extends JpaSpecificationExecutor<Censo>, JpaRepository<Censo, Long> {

    List<Censo> findByColetor(@Param("coletor") Integer coletor);

}
