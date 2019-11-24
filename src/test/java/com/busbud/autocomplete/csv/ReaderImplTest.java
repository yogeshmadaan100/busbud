package com.busbud.autocomplete.csv;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ReaderImplTest {
    @Test
    public void readContent_validData_success() throws IOException, DeserializeException {
        List<Object> results = new ReaderImpl().readContent(loadFileFromResource("static/sample.csv"), line -> null, true);
        Assert.assertEquals(4, results.size());
    }

    @Test(expected = IOException.class)
    public void readContent_invalidFile_IOException() throws IOException, DeserializeException {
        new ReaderImpl().readContent(loadFileFromResource("sample1.csv"), line -> null, true);
    }

    @Test(expected = DeserializeException.class)
    public void readContent_invalidContent_DeserializeException() throws IOException, DeserializeException {
        new ReaderImpl().readContent(loadFileFromResource("static/sample.csv"), line -> {
            throw new DeserializeException("can't deserialize content");
        }, true);
    }

    private FileReader loadFileFromResource(String resourceName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL resourceURL = classLoader.getResource(resourceName);
        if (resourceURL == null) {
            throw new IOException("Resource not found");
        }
        return new FileReader(new File(resourceURL.getFile()));
    }

}
