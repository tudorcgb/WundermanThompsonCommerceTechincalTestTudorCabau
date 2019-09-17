package com.wundermancommerce.interviewtests.graph.impl;

import com.wundermancommerce.interviewtests.graph.Relationship;
import com.wundermancommerce.interviewtests.entity.Person;
import com.wundermancommerce.interviewtests.enumeration.RelationshipType;
import com.wundermancommerce.interviewtests.graph.RelationshipGraph;
import com.wundermancommerce.interviewtests.service.PersonService;
import com.wundermancommerce.interviewtests.service.RelationshipService;
import org.jgrapht.graph.*;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Class that implements a graph data structure to map Persons and their relationships.
 * */
@Service
public final class RelationshipGraphImpl implements RelationshipGraph {

    private SimpleGraph<Person, Relationship> relationshipGraph;


    private PersonService personService;
    private RelationshipService relationshipService;


    public RelationshipGraphImpl(PersonService personService, RelationshipService relationshipService){
        this.personService = personService;
        this.relationshipService = relationshipService;
        relationshipGraph = createGraph();
    }

    /**
     * Method to initialize relationship graph
     * Function makes use of Persone and Relationship service functions, to initialize graph with Person and Relationship objects.
     * */
    private  SimpleGraph<Person, Relationship> createGraph() {
        SimpleGraph<Person, Relationship> relationshipSimpleGraph = new SimpleGraph<>(Relationship.class);
        populateGraph(relationshipSimpleGraph, personService.loadAllPersons(), relationshipService.loadAll());
        return relationshipSimpleGraph;
    }
    /**
     * Method that populates relationship graph from given Person and Relationships lists.
     * */
    private void populateGraph(SimpleGraph<Person, Relationship> relationshipSimpleGraph, List<Person> personList, List<Relationship> relationshipList){

        for(Person person : personList){
            relationshipSimpleGraph.addVertex(person);
        }
        for(Relationship relationship : relationshipList){
            relationshipSimpleGraph.addEdge(relationship.getFirstPerson(),relationship.getSecondPerson(),relationship);
        }

    }
    @Override
    public int noOfPeopleInGraph(){

        return relationshipGraph.vertexSet().size();
    }

    @Override
    public int noOfRelationshipsInGraph(){

        return relationshipGraph.edgeSet().size();
    }

    @Override
    public int noOfRelationshipsForPerson(Person person){

        return relationshipGraph.degreeOf(person);
    }

    @Override
    public int noOfRelationshipsForPerson(String personName){
        Person person = personService.findPersonByName(relationshipGraph.vertexSet(),personName);
        if(person != null){
            return relationshipGraph.degreeOf(person);
        }
        return 0;
    }

    /**
     * Recursive function that traverses graph and finds the FAMILY type relationships for given person, thus forming a Set of extended family members.
     *
     *  @param person person for which we want the extended family members
     * @param familyMembers extended family members set.
     *
     * */
    private void immediateFamilyMembers(Person person, Set<Person> familyMembers){

        Set<Relationship> familyRelationships = relationshipGraph.edgesOf(person).stream()
                .filter(p->p.getRelationshipType() == RelationshipType.FAMILY)
                .collect(Collectors.toSet());

        for(Relationship relationship : familyRelationships){
            Person nextPerson = person.equals(relationship.getFirstPerson()) ? relationship.getSecondPerson() : relationship.getFirstPerson();

            if(!familyMembers.contains(nextPerson)){
                familyMembers.add(nextPerson);
                immediateFamilyMembers(relationship.getFirstPerson(),familyMembers);
            }

        }

    }
    /**
     * Function that initialises the extended family sets and calls de recursive immediateFamilyMembers method.
     * Function uses ConcurrentHashMap to achieve thread safety
     * @return number of family members for given person
     * */
    @Override
    public int noOfFamilyMembers(Person person){
        Set<Person> synchronizedExtendedFamilyMembers = ConcurrentHashMap.newKeySet();;

        synchronizedExtendedFamilyMembers.add(person);
        immediateFamilyMembers(person,synchronizedExtendedFamilyMembers);

        return synchronizedExtendedFamilyMembers.size();

    }
    /**
     * Function finds the Person associated with the given name then initialises the extended family sets and calls de recursive immediateFamilyMembers method.
     *
     * @return number of family members for given person
     * //TODO NAME IS NOT UNIQUE! unknown behaviour for duplicate names in dataset
     * */
    @Override
    public int noOfFamilyMembers(String personName) {
        Person person = personService.findPersonByName(relationshipGraph.vertexSet(),personName);
        if(person != null){
            return noOfFamilyMembers(person);
        }
        return 0;
    }
}
