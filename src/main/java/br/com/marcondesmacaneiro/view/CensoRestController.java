package br.com.marcondesmacaneiro.view;

import br.com.marcondesmacaneiro.domain.exception.EntityAreadyExistException;
import br.com.marcondesmacaneiro.domain.model.Pessoa;
import br.com.marcondesmacaneiro.domain.model.censo.Censo;
import br.com.marcondesmacaneiro.domain.repository.censo.CensoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcondes
 */
@RestController
@RequestMapping("/api/censo")
public class CensoRestController {

    private static final Logger logger = LoggerFactory.getLogger(CensoRestController.class);

    @Autowired
    private CensoService service;

    @Autowired
    private PagedResourcesAssembler<Censo> pagedResourcesAssembler;


    @RequestMapping(method = GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Censo>> findAll(@PathVariable Integer id) {

        List<Censo> lista = service.findAll(id);

        return ok(lista);
    }

    

}
