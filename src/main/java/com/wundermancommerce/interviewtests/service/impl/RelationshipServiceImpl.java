package com.wundermancommerce.interviewtests.service.impl;

import com.wundermancommerce.interviewtests.entity.Person;
import com.wundermancommerce.interviewtests.graph.Relationship;
import com.wundermancommerce.interviewtests.entity.RelationshipDTO;
import com.wundermancommerce.interviewtests.repository.PersonRepository;
import com.wundermancommerce.interviewtests.repository.RelationshipRepository;
import com.wundermancommerce.interviewtests.service.PersonService;
import com.wundermancommerce.interviewtests.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for manipulating Relationship objects.
 * @see Relationship
 * */
public class RelationshipServiceImpl implements RelationshipService {

    private RelationshipRepository relationshipRepository;
    private PersonRepository personRepository;
    private PersonService personService;

    @Autowired
    public RelationshipServiceImpl(RelationshipRepository relationshipRepository, PersonRepository personRepository, PersonService personService) {
        this.relationshipRepository = relationshipRepository;
        this.personRepository = personRepository;
        this.personService = personService;
    }

    /**
     * Method that returns a list of Relationship which contains 2 Person Objects.
     * For each relationship read from CSV file, the associated Person object is found, by making the assumption that email addresses are unique.
     * @see Relationship
     * */
    @Override
    public List<Relationship> loadAll() {
        List<RelationshipDTO> relationshipDTOS = relationshipRepository.readAll();
        List<Person> personList = personRepository.readAll();

        List<Relationship> relationshipList = new ArrayList<>();
        for(RelationshipDTO relationship : relationshipDTOS){
            Person firstPerson = personService.findPersonByEmail(personList,relationship.getFirstPerson());
            Person secondPerson = personService.findPersonByEmail(personList,relationship.getSecondPerson());
            if(firstPerson != null && secondPerson != null) {
                relationshipList.add(new Relationship(firstPerson, secondPerson, relationship.getRelationshipType()));
            }
        }

        return relationshipList;
    }


}
