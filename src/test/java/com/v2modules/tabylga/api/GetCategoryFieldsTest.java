package com.v2modules.tabylga.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import com.v2modules.tabylga.api.utils.GraphQLClient;
import com.v2modules.tabylga.api.utils.GraphQLFileUtil;
import com.v2modules.tabylga.api.utils.GraphQLQueryBuilder;
import com.v2modules.tabylga.api.utils.ResponseValidator;

import java.util.Map;

@Epic("GraphQL API Тесты")
public class GetCategoryFieldsTest {

    @Test
    @Feature("Поля категории 'Техника для кухни'")
    @Story("Получение полей категории 'Техника для кухни' через запрос getCategoryFields")
    @DisplayName("Проверка запроса getCategoryFields из файлов запроса и переменных для категории 'Техника для кухни'")
    @Description("Тест загружает GraphQL запрос из файла getCategoryFields.graphql и переменные из файла getCategoryFields.variables.json, " +
            "формирует JSON тело запроса, отправляет запрос и валидирует ответ, проверяя, что отсутствуют ошибки типа 'CategoryRead not found' " +
            "и что структура ответа соответствует ожидаемой."+
            "покрывает задачу - https://app.clickup.com/t/86dvpfur1")
    public void testADSCategoryFields() {
        Allure.step("Загрузка GraphQL запроса из файла", () -> {
            // Загружаем текст запроса
            String query = GraphQLFileUtil.loadGraphQLQuery("src/test/resources/graphql/getCategoryFields.graphql");
            Allure.addAttachment("GraphQL запрос", "text/plain", query);
            System.out.println("GraphQL запрос: " + query);

            // Загружаем переменные для запроса из отдельного файла
            Map<String, Object> variables = GraphQLFileUtil.loadGraphQLVariables("src/test/resources/graphql/getCategoryFields.variables.json");
            Allure.addAttachment("Переменные для запроса", "application/json", variables.toString());
            System.out.println("Переменные: " + variables);

            // Формирование JSON тела запроса с использованием загруженного запроса и переменных
            String jsonBody = GraphQLQueryBuilder.buildQuery(query, variables);
            Allure.addAttachment("JSON тело запроса", "text/plain", jsonBody);
            System.out.println("JSON тело запроса: " + jsonBody);

            // Отправка запроса и получение ответа от сервера
            Response response = GraphQLClient.sendRequest(jsonBody);
            ResponseValidator.validateStatusCode(response);

            // Валидация ответа – проверяем отсутствие ошибок и корректность структуры данных
            ResponseValidator.validateCategoryFields(response);
        });
    }
}
