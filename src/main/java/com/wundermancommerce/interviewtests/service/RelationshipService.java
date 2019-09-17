package com.wundermancommerce.interviewtests.service;

import com.wundermancommerce.interviewtests.graph.Relationship;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RelationshipService {

    List<Relationship> loadAll();


}
