package com.wundermancommerce.interviewtests.repository.impl;

import com.wundermancommerce.interviewtests.entity.Person;
import com.wundermancommerce.interviewtests.repository.PersonRepository;
import com.wundermancommerce.interviewtests.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class that handles Person class IO operations.
 * */
public class PersonRepositoryImpl implements PersonRepository {

    private static final String PERSONS_FILE_PATH = "src\\test\\resources\\people.csv";
    private CsvUtils csvUtils;

    @Autowired
    public PersonRepositoryImpl(CsvUtils csvUtils) {
        this.csvUtils = csvUtils;
    }

    /**
     * Reads all Person objects from static file path PERSON_FILE_PATH
     *
     * @return List of person objects.
     * */
    @Override
    public List<Person> readAll() {
        File file = new File(PERSONS_FILE_PATH);
        List<Person> personList;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            personList = csvUtils.readList(Person.class,fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
            personList = new ArrayList<Person>();
        }

        return personList;
    }
}
