package com.wundermancommerce.interviewtests.entity;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * POJO class for working with relationships from CSV file.
 * */
@Entity
@JsonPropertyOrder({"name","email","age"})
public class Person {

    private String name;
    @Id
    private String email;
    private int age;

    public Person() {
    }

    public Person(String name, String email,int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        Person person = (Person) obj;
        return person.getEmail().equals(this.email) && person.getName().equals(this.name);
    }

    @Override
    public int hashCode() {
        return 17*email.hashCode()*name.hashCode();
    }
}
