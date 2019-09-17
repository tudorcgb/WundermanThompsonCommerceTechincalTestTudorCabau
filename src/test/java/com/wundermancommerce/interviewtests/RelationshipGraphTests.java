package com.wundermancommerce.interviewtests;

import com.wundermancommerce.interviewtests.entity.Person;
import com.wundermancommerce.interviewtests.graph.RelationshipGraph;
import com.wundermancommerce.interviewtests.graph.impl.RelationshipGraphImpl;
import com.wundermancommerce.interviewtests.repository.PersonRepository;
import com.wundermancommerce.interviewtests.repository.RelationshipRepository;
import com.wundermancommerce.interviewtests.repository.impl.PersonRepositoryImpl;
import com.wundermancommerce.interviewtests.repository.impl.RelationshipRepositoryImpl;
import com.wundermancommerce.interviewtests.service.PersonService;
import com.wundermancommerce.interviewtests.service.RelationshipService;
import com.wundermancommerce.interviewtests.service.impl.PersonServiceImpl;
import com.wundermancommerce.interviewtests.service.impl.RelationshipServiceImpl;
import com.wundermancommerce.interviewtests.utils.CsvUtils;
import com.wundermancommerce.interviewtests.utils.impl.CsvUtilsImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Single class that contains all required tests.
 *
 * 
 * */
public class RelationshipGraphTests {
    private RelationshipGraph relationshipGraph;
    @Before
    public void initTest(){
        //Todo: use Spring dependency injection ?
        CsvUtils csvUtils = new CsvUtilsImpl();
        PersonRepository personRepository = new PersonRepositoryImpl(csvUtils);
        RelationshipRepository relationshipRepository = new RelationshipRepositoryImpl(csvUtils);
        PersonService personService = new PersonServiceImpl(personRepository);
        RelationshipService relationshipService = new RelationshipServiceImpl(relationshipRepository,personRepository, personService);

        relationshipGraph = new RelationshipGraphImpl(personService,relationshipService);
    }

    @Test
    public void noPeopleLoaded(){
        assertEquals(12, relationshipGraph.noOfPeopleInGraph());
    }

    @Test
    public void noRelationshipsLoaded(){
        assertEquals(16, relationshipGraph.noOfRelationshipsInGraph());
    }

    @Test
    public void noRelationshipsForPersonByName(){

        assertEquals(4, relationshipGraph.noOfRelationshipsForPerson("Bob"));
        assertEquals(3, relationshipGraph.noOfRelationshipsForPerson("Jenny"));
        assertEquals(2, relationshipGraph.noOfRelationshipsForPerson("Nigel"));
        assertEquals(0, relationshipGraph.noOfRelationshipsForPerson("Alan"));
    }

    @Test
    public void noRelationshipsForPerson(){

        assertEquals(4, relationshipGraph.noOfRelationshipsForPerson(new Person("Bob","bob@bob.com",31)));
        Assert.assertEquals(3, relationshipGraph.noOfRelationshipsForPerson(new Person("Jenny","jenny@toys.com",52)));
        Assert.assertEquals(2, relationshipGraph.noOfRelationshipsForPerson(new Person("Nigel","nigel@marketing.com",40)));
        Assert.assertEquals(0, relationshipGraph.noOfRelationshipsForPerson(new Person("Alan","alan@lonely.org",23)));

    }

    @Test
    public void noFamilyMembers(){

        //Requirement say 4 but in CSV file Jenny has only 2 Family members
        Assert.assertEquals(3, relationshipGraph.noOfFamilyMembers(new Person("Jenny","jenny@toys.com",52)));
        Assert.assertEquals(4, relationshipGraph.noOfFamilyMembers(new Person("Bob","bob@bob.com",31)));

    }

    @Test
    public void noFamilyMembersByName(){

        //Requirement say 4 but in CSV file Jenny has only 2 Family members
        assertEquals(3, relationshipGraph.noOfFamilyMembers("Jenny"));
        assertEquals(4, relationshipGraph.noOfFamilyMembers("Bob"));

    }



}
