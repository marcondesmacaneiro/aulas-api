package br.com.example.view;

import br.com.example.domain.model.Person;
import br.com.example.domain.repository.PersonService;
import br.com.example.domain.vo.Phone;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.net.URI;
import java.util.function.Consumer;

import static br.com.example.Application.getBean;
import static br.com.example.domain.exception.EntityNotFoundException.entityNotFoundException;
import static br.com.example.util.Preconditions.checkArgument;
import static java.util.Objects.nonNull;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@ExposesResourceFor(Person.class)
@RequestMapping(value = "/person")
public class PersonController
{
    @Autowired
    private PersonService service;

    @Autowired
    private PagedResourcesAssembler<Person> pagedResourcesAssembler;

    @Autowired
    private ResourceAssembler<Person, Resource<Person>> assembler;

    @RequestMapping(method = GET, produces = HAL_JSON_VALUE)
    public ResponseEntity<PagedResources<Resource<Person>>> findAll(Pageable pageable)
    {
        return ok(pagedResourcesAssembler.toResource(service.findAll(pageable), assembler));
    }

    @RequestMapping(method = GET, value = "/{id}", produces = HAL_JSON_VALUE)
    public ResponseEntity<Resource<Person>> findOne(@PathVariable Long id)
    {
        Person person = service.findOne(id)
                .orElseThrow(entityNotFoundException("Person not found!"));
        return ok(assembler.toResource(person));
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@Valid @RequestBody Person person)
    {
        checkArgument(person.isNew(), "Person must to be a new entity! use PATCH method to update a person.");
        person = service.save(person);
        return created(linkTo(methodOn(getClass()).findOne(person.getId())).toUri()).build();
    }

    @RequestMapping(method = PATCH, value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editPartial(@PathVariable Long id,
                                            @Valid @RequestBody PersonPatchInput input)
    {
        Person person = service.findOne(id)
                .orElseThrow(entityNotFoundException("Person not found!"));
        input.accept(person);
        service.save(person);
        return noContent().build();
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        Person person = service.findOne(id)
                .orElseThrow(entityNotFoundException("Person not found!"));
        service.delete(person);
        return noContent().build();
    }

    static Long extractPersonId(String uri) {
        String id = (new UriTemplate("/person/{id}")).match(uri).get("id");
        checkArgument(id.matches("\\d+"), "Person id must be a valid number!");
        return Long.valueOf(id);
    }

    static Long extractPersonId(URI uri) {
        return extractPersonId(uri.toASCIIString());
    }

    static Person extractPerson(URI uri) {
        return getBean(PersonService.class).findOne(extractPersonId(uri))
                .orElseThrow(entityNotFoundException("Person not found!"));
    }

    @Component
    private static class PeopleResourceAssembler implements ResourceAssembler<Person, Resource<Person>>
    {
        @Override
        public Resource<Person> toResource(Person entity) {
            return new Resource<>(entity,
                    linkTo(methodOn(PersonController.class).findOne(entity.getId())).withSelfRel());
        }
    }

    static @Data class PersonPatchInput implements Consumer<Person>
    {
        @Size(min = 1, max = 100)
        private String name;

        @Email
        @Size(min = 1, max = 100)
        private String mail;

        private Phone phone;

        @Override
        public void accept(Person person) {
            if (nonNull(name)) {
                person.setName(name);
            }
            if (nonNull(mail)) {
                person.setMail(mail);
            }
            if (nonNull(phone)) {
                person.setPhone(phone);
            }
        }
    }
}
