package com.ljmu.project.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode convertStringToJson(String jsonString) {
        try {
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            log.info("Unable to Parse the JSON :: {}", e.getCause().getMessage());
            return null;
        }
    }

    public static <T> T convertJsonNodeToObject(JsonNode jsonNode, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(jsonNode, clazz);
        } catch (Exception e) {
            log.info("Unable to Parse the JSON :: {}", e.getCause().getMessage());
            return null;
        }
    }
}
