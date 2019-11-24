package com.busbud.autocomplete.csv;

import org.apache.commons.csv.CSVRecord;

/**
 * Deserialize objects.
 *
 * @param <T> the type of the objects.
 */
public interface Deserializer<T> {
    /**
     * Deserialize an object starting from csv line. The order of the strings is the same as found in the csv.
     *
     * @param line the csv line.
     * @return An object.
     * @throws DeserializeException if anything goes wrong.
     */
    T deserialize(CSVRecord line) throws DeserializeException;
}