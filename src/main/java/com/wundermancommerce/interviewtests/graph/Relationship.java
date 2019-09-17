package com.wundermancommerce.interviewtests.graph;

import com.wundermancommerce.interviewtests.entity.Person;
import com.wundermancommerce.interviewtests.entity.RelationshipDTO;
import com.wundermancommerce.interviewtests.enumeration.RelationshipType;
import org.jgrapht.graph.DefaultEdge;


/**
 * Relationship class that contains the persons involved in the relationships as Person objects, as opposed to simple Strings used by RelationshipDTO
 * Class is represents Edges in RelationshipGraph
 *
 * @see RelationshipDTO
 * @see com.wundermancommerce.interviewtests.graph.RelationshipGraph
 * */
public class Relationship extends DefaultEdge{

    private Person firstPerson;
    private Person secondPerson;
    private RelationshipType relationshipType;

    public Relationship() {
    }

    public Relationship(Person firstPerson, Person secondPerson, RelationshipType relationshipType) {
        this.firstPerson = firstPerson;
        this.secondPerson = secondPerson;
        this.relationshipType = relationshipType;

    }

    public Person getFirstPerson() {
        return firstPerson;
    }

    public void setFirstPerson(Person firstPerson) {
        this.firstPerson = firstPerson;
    }

    public Person getSecondPerson() {
        return secondPerson;
    }

    public void setSecondPerson(Person secondPerson) {
        this.secondPerson = secondPerson;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }
}
