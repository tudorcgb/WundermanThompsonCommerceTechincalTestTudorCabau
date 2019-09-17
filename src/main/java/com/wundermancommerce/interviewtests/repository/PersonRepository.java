package com.wundermancommerce.interviewtests.repository;

import com.wundermancommerce.interviewtests.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository {

    List<Person> readAll();
}
