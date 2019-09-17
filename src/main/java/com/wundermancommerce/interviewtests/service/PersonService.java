package com.wundermancommerce.interviewtests.service;

import com.wundermancommerce.interviewtests.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PersonService {

    List<Person> loadAllPersons();

    Person findPersonByEmail(List<Person> personList, String email);

    Person findPersonByName(Set<Person> personSet, String name);
}
