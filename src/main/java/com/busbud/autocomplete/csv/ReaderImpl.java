package com.busbud.autocomplete.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

// Implementation for IReader
public class ReaderImpl implements IReader {
    public <T> List<T> readContent(Reader fileReader, Deserializer<T> deserializer, boolean includeHeaders) throws IOException, DeserializeException {
        CSVFormat csvFormat = CSVFormat.TDF.withTrim().withQuote(null);
        if (includeHeaders)
            csvFormat = csvFormat.withFirstRecordAsHeader();
        CSVParser csvParser = new CSVParser(fileReader, csvFormat);

        List<T> contents = new ArrayList<T>();
        for (CSVRecord record : csvParser.getRecords()) {
            contents.add(deserializer.deserialize(record));
        }
        return contents;
    }
}
