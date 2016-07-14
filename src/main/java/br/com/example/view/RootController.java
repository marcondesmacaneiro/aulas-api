package br.com.example.view;

import br.com.example.domain.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/", produces = HAL_JSON_VALUE)
public class RootController {

    @Autowired
    private EntityLinks entityLinks;

    @RequestMapping(method = GET)
    ResponseEntity<?> root() {
        ResourceSupport resource = new ResourceSupport();
        resource.add(entityLinks.linkFor(Person.class).withRel("persons"));
        return ok(resource);
    }
}
