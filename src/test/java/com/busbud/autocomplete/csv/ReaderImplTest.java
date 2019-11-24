package com.busbud.autocomplete.csv;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.List;
import java.util.Objects;

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

    private Reader loadFileFromResource(String resourceName) throws IOException {
        return new InputStreamReader(Objects.requireNonNull(new ClassPathResource(resourceName).getInputStream()));
    }

}
