package br.com.example.domain.repository;

import org.springframework.stereotype.Service;

import br.com.example.domain.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Service
@Transactional(readOnly = true)
public class PersonService {

    @Autowired
    private PersonCacheableRepository cacheableRepository;

    @Autowired
    private PersonRepository repository;

    public Page<Person> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Person> findOne(Long id) {
        return Optional.ofNullable(cacheableRepository.findOne(id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Person save(Person person) {
        return cacheableRepository.save(person);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Person person) {
        cacheableRepository.delete(person);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Long id) {
        cacheableRepository.delete(id);
    }
}
