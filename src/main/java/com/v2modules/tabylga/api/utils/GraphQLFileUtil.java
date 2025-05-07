package com.v2modules.tabylga.api.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GraphQLFileUtil {
    public static String loadGraphQLQuery(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки GraphQL-запроса из файла: " + filePath, e);
        }
    }
    /**
     * Читает файл с переменными и преобразует JSON содержимое в Map.
     *
     * @param filePath путь к файлу с переменными (например, ADS_CATEGORY_FIELDS.variables.json)
     * @return Map с переменными для GraphQL запроса
     */
    public static Map<String, Object> loadGraphQLVariables(String filePath) {
        try {
            // Читаем все байты из файла
            byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
            // Создаем ObjectMapper для обработки JSON
            ObjectMapper mapper = new ObjectMapper();
            // Преобразуем JSON-данные в Map
            return mapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            // Если произошла ошибка, выбрасываем RuntimeException с описанием проблемы
            throw new RuntimeException("Ошибка при загрузке переменных GraphQL из файла: " + filePath, e);
        }
    }
}
