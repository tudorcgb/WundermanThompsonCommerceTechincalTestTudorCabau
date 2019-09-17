package com.wundermancommerce.interviewtests.utils;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * Utility class for CSV files
 * */
@Service
public interface CsvUtils {

    /**
     * Reads CSV rows into a list.
     * */
    <T> List<T> readList(Class<T> clazz, InputStream stream) throws IOException;
}
