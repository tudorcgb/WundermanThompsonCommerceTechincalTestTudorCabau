package com.wundermancommerce.interviewtests.repository.impl;

import com.wundermancommerce.interviewtests.entity.RelationshipDTO;
import com.wundermancommerce.interviewtests.repository.RelationshipRepository;
import com.wundermancommerce.interviewtests.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class that handles RelationshipDTO class IO operations.
 *
 * @see RelationshipDTO
 * */
public class RelationshipRepositoryImpl implements RelationshipRepository {

    private static final String RELATIONSHIPS_FILE_PATH = "src\\test\\resources\\relationships.csv";

    private final CsvUtils csvUtils;

    @Autowired
    public RelationshipRepositoryImpl(CsvUtils csvUtils) {
        this.csvUtils = csvUtils;
    }

    /**
     * Reads all RelationshipDTO objects from static file path RELATIONSHIPS_FILE_PATH
     *
     * @return List of RelationshipDTO objects.
     * */
    @Override
    public List<RelationshipDTO> readAll() {
        File file = new File(RELATIONSHIPS_FILE_PATH);
        List<RelationshipDTO> relationships;
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            relationships = csvUtils.readList(RelationshipDTO.class,fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            relationships = new ArrayList<RelationshipDTO>();
        }


        return relationships;
    }
}
