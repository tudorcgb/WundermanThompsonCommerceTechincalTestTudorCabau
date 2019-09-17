package com.wundermancommerce.interviewtests.graph;

import com.wundermancommerce.interviewtests.entity.Person;

import java.util.Set;

public interface RelationshipGraph {


    /**
     * Method to calculate number of People in relationship Graph
     * */
    int noOfPeopleInGraph();

    /**
     * Method to calculate number relationships Graph
     * */
    int noOfRelationshipsInGraph();

    /**
     * Method to calculate number of relationship for a given person
     * */
    int noOfRelationshipsForPerson(Person person);

    /**
     * Method to calculate number of relationships for a given person's name
     * */
    int noOfRelationshipsForPerson(String personName);

    /**
     * Method to calculate number of relationships of FAMILY type for given person
     * */
    int noOfFamilyMembers(Person person);

    /**
     * Method to calculate number of relationships of FAMILY type for given person's name
     * */
    int noOfFamilyMembers(String personName);
}
