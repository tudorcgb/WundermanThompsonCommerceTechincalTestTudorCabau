package com.wundermancommerce.interviewtests.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.wundermancommerce.interviewtests.enumeration.RelationshipType;

import javax.persistence.Entity;


/**
 * POJO class for working with relationships from CSV file.
 * */
@Entity
@JsonPropertyOrder({"firstPerson","relationshipType","secondPerson"})
public class RelationshipDTO {

    private String firstPerson;
    private String secondPerson;
    private RelationshipType relationshipType;

    public RelationshipDTO() {
    }

    public RelationshipDTO(String firstPerson, String secondPerson, RelationshipType relationshipType) {
        this.firstPerson = firstPerson;
        this.secondPerson = secondPerson;
        this.relationshipType = relationshipType;
    }

    public String getFirstPerson() {
        return firstPerson;
    }

    public void setFirstPerson(String firstPerson) {
        this.firstPerson = firstPerson;
    }

    public String getSecondPerson() {
        return secondPerson;
    }

    public void setSecondPerson(String secondPerson) {
        this.secondPerson = secondPerson;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }
}
