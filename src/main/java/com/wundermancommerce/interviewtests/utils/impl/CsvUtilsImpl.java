package com.wundermancommerce.interviewtests.utils.impl;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.wundermancommerce.interviewtests.utils.CsvUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvUtilsImpl implements CsvUtils {

    /**
     * Generic function to read CSV file objects.
     *
     * @param clazz represents a POJO class into which the read row is mapped.
     * @param stream is a file input stream.
     * @return method returns
     */
    @Override
    public <T> List<T> readList(Class<T> clazz, InputStream stream) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(clazz)
                .withoutHeader()
                .withColumnReordering(false)
                .withColumnSeparator(',');
        ObjectReader reader = mapper.readerFor(clazz).with(schema);
        return reader.<T>readValues(stream).readAll();
    }
}
