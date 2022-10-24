package org.example.employee.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestUtil {

    private static final ObjectMapper MAPPER = createMapper();

    public static <T> T readJsonResource(String resourcePath, Class<T> valueType) throws IOException {
        return MAPPER.readValue(readResource(resourcePath), valueType);
    }

    public static <T> List<T> readJsonResourceToList(String resourcePath, Class<T> valueType) throws IOException {
        JavaType type = MAPPER.getTypeFactory().constructCollectionType(List.class, valueType);
        return MAPPER.readValue(readResource(resourcePath), type);
    }

    public static InputStream readResource(String resourcePath) {
        return TestUtil.class.getClassLoader().getResourceAsStream(resourcePath);
    }

    public static <T> String write(T value) throws JsonProcessingException {
        return MAPPER.writeValueAsString(value);
    }

    private static ObjectMapper createMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
