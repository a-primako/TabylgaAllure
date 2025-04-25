package com.v2modules.tabylga.api.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GraphQLFileUtil {
    public static String loadGraphQLQuery(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки GraphQL-запроса из файла: " + filePath, e);
        }
    }
}
