package com.busbud.autocomplete.csv;

/**
 * Exception when deserializing fails from CsvRecord
 */
public class DeserializeException extends Exception {
    public DeserializeException() {
    }

    public DeserializeException(String message) {
        super(message);
    }
}
