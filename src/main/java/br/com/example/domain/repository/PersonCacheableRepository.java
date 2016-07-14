package br.com.example.domain.repository;

import br.com.example.domain.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */

@Repository
@NoRepositoryBean
@CacheConfig(cacheNames = "person")
class PersonCacheableRepository {

    @Autowired
    private PersonRepository repository;

    @Cacheable
    public Person findOne(Long id) {
        return repository.findOne(id);
    }

    @CacheEvict(key = "#person.id")
    public Person save(Person person) {
        return repository.save(person);
    }

    @CacheEvict(key = "#person.id")
    public void delete(Person person) {
        repository.delete(person);
    }

    @CacheEvict
    public void delete(Long id) {
        repository.delete(id);
    }
}
