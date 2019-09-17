package com.wundermancommerce.interviewtests.service.impl;

import com.wundermancommerce.interviewtests.entity.Person;
import com.wundermancommerce.interviewtests.repository.PersonRepository;
import com.wundermancommerce.interviewtests.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * Service class for manipulating Person objects
 *
 * @see Person
 * */
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Returns a list of persons read from CSV file.
     * */
    @Override
    public List<Person> loadAllPersons() {
        return personRepository.readAll();
    }


    /**
     * Searches given persons list for person with given email
     * */
    @Override
    public Person findPersonByEmail(List<Person> personList, String email){

        Optional<Person> optionalPerson = personList.stream()
                .filter(p -> p.getEmail().equals(email)).findFirst();

        return optionalPerson.orElse(null);
    }
    /**
     * Searches given persons list for person with given name
     * */
    @Override
    public Person findPersonByName(Set<Person> personSet, String name) {
        Optional<Person> optionalPerson = personSet.stream()
                .filter(p -> p.getName().equals(name)).findFirst();
        return optionalPerson.orElse(null);
    }
}
