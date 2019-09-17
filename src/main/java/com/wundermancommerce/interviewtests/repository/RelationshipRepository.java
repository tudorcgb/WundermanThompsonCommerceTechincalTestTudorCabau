package com.wundermancommerce.interviewtests.repository;

import com.wundermancommerce.interviewtests.entity.RelationshipDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipRepository {

    List<RelationshipDTO> readAll();
}
