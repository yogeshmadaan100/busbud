package com.busbud.autocomplete.csv;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/*
    Reader API
 */
public interface IReader {
    /**
     * read file and convert content into list of pojo
     * @param fileReader input file reader to read the file
     * @param deserializer deserializer to deserialize content into POJO
     * @param <T>
     * @param includeHeaders specify is fist line include headers
     * @return list of generic T objects
     * @throws IOException
     * @throws DeserializeException
     */
    <T> List<T> readContent(Reader fileReader, Deserializer<T> deserializer, boolean includeHeaders) throws IOException, DeserializeException;
}
