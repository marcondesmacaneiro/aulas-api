package br.com.example.domain.repository;

import br.com.example.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
interface PersonRepository extends JpaRepository<Person, Long> {
}
