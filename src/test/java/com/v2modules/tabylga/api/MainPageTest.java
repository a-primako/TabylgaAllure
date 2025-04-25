package com.v2modules.tabylga.api;

import com.v2modules.tabylga.api.utils.GraphQLFileUtil;
import com.v2modules.tabylga.api.utils.GraphQLQueryBuilder;
import com.v2modules.tabylga.api.utils.GraphQLClient;
import com.v2modules.tabylga.api.utils.ResponseValidator;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Epic("GraphQL API Тесты")
public class MainPageTest {

    @Test
    @Feature("Категории и бизнес-профили")
    @Story("Получение количества категорий и бизнес-профилей")
    @DisplayName("Проверка количества категорий и бизнес-профилей")
    @Description("Данный тест проверяет, что API возвращает корректное количество категорий и бизнес-профилей.")
    public void getCategoryAndBusinessProfilesCountTest() {
        Allure.step("Загрузка GraphQL запроса для категорий и бизнес-профилей", () -> {
            String info = "Загружается запрос из файла: src/test/resources/graphql/getCategoryAndBusinessProfilesCount.graphql";
            System.out.println(info);
            Allure.addAttachment("Информация о запросе", "text/plain", info);
        });
        String query = GraphQLFileUtil.loadGraphQLQuery("src/test/resources/graphql/getCategoryAndBusinessProfilesCount.graphql");
        Allure.addAttachment("GraphQL запрос", "text/plain", query);
        System.out.println("GraphQL запрос: " + query);

        Allure.step("Формирование JSON тела запроса без переменных", () -> {
            String info = "Формируется JSON тело запроса без переменных";
            System.out.println(info);
            Allure.addAttachment("Информация", "text/plain", info);
        });
        String jsonBody = GraphQLQueryBuilder.buildQuery(query, null);
        Allure.addAttachment("JSON Тело запроса", "text/plain", jsonBody);
        System.out.println("JSON Тело запроса: " + jsonBody);

        Allure.step("Отправка GraphQL запроса и получение ответа", () -> {
            String info = "Отправка запроса...";
            System.out.println(info);
            Allure.addAttachment("Отправка запроса", "text/plain", info);
        });
        Response response = GraphQLClient.sendRequest(jsonBody);
        String responsePretty = response.getBody().asPrettyString();
        Allure.addAttachment("Ответ", "text/plain", responsePretty);
        System.out.println("Ответ: " + responsePretty);

        Allure.step("Валидация кода статуса, категорий и бизнес-профилей", () -> {
            String info = "Проводится валидация: статус, категории и бизнес-профили";
            System.out.println(info);
            Allure.addAttachment("Валидация", "text/plain", info);
            ResponseValidator.validateStatusCode(response);
            ResponseValidator.validateCategories(response);
            ResponseValidator.validateBusinessProfiles(response);
        });
    }

    @Test
    @Feature("Отфильтрованные категории и бизнес-профили")
    @Story("Получение категорий и бизнес-профилей с фильтрацией скрытых категорий")
    @DisplayName("Проверка отфильтрованных категорий и бизнес-профилей с переменными")
    @Description("Данный тест проверяет, что API корректно возвращает категории и бизнес-профили при фильтрации скрытых категорий.")
    public void getFilteredCategoriesAndBusinessProfilesWithVariablesTest() {
        Allure.step("Загрузка GraphQL запроса для категорий и бизнес-профилей с фильтрацией", () -> {
            String info = "Загружается запрос из файла: src/test/resources/graphql/getCategoryAndBusinessProfilesCount.graphql";
            System.out.println(info);
            Allure.addAttachment("Информация о запросе", "text/plain", info);
        });
        String query = GraphQLFileUtil.loadGraphQLQuery("src/test/resources/graphql/getCategoryAndBusinessProfilesCount.graphql");
        Allure.addAttachment("GraphQL запрос", "text/plain", query);
        System.out.println("GraphQL запрос: " + query);

        Allure.step("Формирование переменных для фильтрации (скрытые категории: false)", () -> {
            String info = "Формируются переменные: {\"where\":{\"hidden\": false}}";
            System.out.println(info);
            Allure.addAttachment("Переменные", "text/plain", info);
        });
        Map<String, Object> variables = Map.of("where", Map.of("hidden", false));

        Allure.step("Формирование JSON тела запроса с переменными", () -> {
            String info = "Формируется JSON тело запроса с переменными";
            System.out.println(info);
            Allure.addAttachment("Информация", "text/plain", info);
        });
        String jsonBody = GraphQLQueryBuilder.buildQuery(query, variables);
        Allure.addAttachment("JSON Тело запроса с переменными", "text/plain", jsonBody);
        System.out.println("JSON Тело запроса: " + jsonBody);

        Allure.step("Отправка GraphQL запроса и получение ответа", () -> {
            String info = "Отправка запроса...";
            System.out.println(info);
            Allure.addAttachment("Отправка запроса", "text/plain", info);
        });
        Response response = GraphQLClient.sendRequest(jsonBody);
        String responsePretty = response.getBody().asPrettyString();
        Allure.addAttachment("Ответ", "text/plain", responsePretty);
        System.out.println("Ответ: " + responsePretty);

        Allure.step("Валидация кода статуса, категорий и бизнес-профилей", () -> {
            String info = "Проводится валидация: статус, категории и бизнес-профили";
            System.out.println(info);
            Allure.addAttachment("Валидация", "text/plain", info);
            ResponseValidator.validateStatusCode(response);
            ResponseValidator.validateCategories(response);
            ResponseValidator.validateBusinessProfiles(response);
        });
    }

    @Test
    @Feature("Получение валют")
    @Story("Получение списка поддерживаемых валют")
    @DisplayName("Проверка списка поддерживаемых валют")
    @Description("Тест проверяет, что API корректно возвращает список поддерживаемых валют.")
    public void getCurrenciesTest() {
        Allure.step("Загрузка GraphQL запроса для получения валют", () -> {
            String info = "Загружается запрос из файла: src/test/resources/graphql/getCurrencies.graphql";
            System.out.println(info);
            Allure.addAttachment("Информация о запросе", "text/plain", info);
        });
        String query = GraphQLFileUtil.loadGraphQLQuery("src/test/resources/graphql/getCurrencies.graphql");
        Allure.addAttachment("GraphQL запрос", "text/plain", query);
        System.out.println("GraphQL запрос: " + query);

        Allure.step("Формирование JSON тела запроса без переменных", () -> {
            String info = "Формируется JSON тело запроса без переменных";
            System.out.println(info);
            Allure.addAttachment("Информация", "text/plain", info);
        });
        String jsonBody = GraphQLQueryBuilder.buildQuery(query, null);
        Allure.addAttachment("JSON Тело запроса", "text/plain", jsonBody);
        System.out.println("JSON Тело запроса: " + jsonBody);

        Allure.step("Отправка GraphQL запроса и получение ответа", () -> {
            String info = "Отправка запроса...";
            System.out.println(info);
            Allure.addAttachment("Отправка запроса", "text/plain", info);
        });
        Response response = GraphQLClient.sendRequest(jsonBody);
        String responsePretty = response.getBody().asPrettyString();
        Allure.addAttachment("Ответ", "text/plain", responsePretty);
        System.out.println("Ответ: " + responsePretty);

        Allure.step("Валидация кода статуса и списка валют", () -> {
            String info = "Проводится валидация: статус и список валют";
            System.out.println(info);
            Allure.addAttachment("Валидация", "text/plain", info);
            ResponseValidator.validateStatusCode(response);
            ResponseValidator.validateCurrencies(response);
        });
    }

    @Test
    @Feature("Получение языков")
    @Story("Получение списка поддерживаемых языков")
    @DisplayName("Проверка списка поддерживаемых языков")
    @Description("Данный тест проверяет, что API корректно возвращает список поддерживаемых языков.")
    public void getLanguagesTest() {
        Allure.step("Загрузка GraphQL запроса для получения языков", () -> {
            String info = "Загружается запрос из файла: src/test/resources/graphql/getLanguages.graphql";
            System.out.println(info);
            Allure.addAttachment("Информация о запросе", "text/plain", info);
        });
        String query = GraphQLFileUtil.loadGraphQLQuery("src/test/resources/graphql/getLanguages.graphql");
        Allure.addAttachment("GraphQL запрос", "text/plain", query);
        System.out.println("GraphQL запрос: " + query);

        Allure.step("Формирование JSON тела запроса без переменных", () -> {
            String info = "Формируется JSON тело запроса без переменных";
            System.out.println(info);
            Allure.addAttachment("Информация", "text/plain", info);
        });
        String jsonBody = GraphQLQueryBuilder.buildQuery(query, null);
        Allure.addAttachment("JSON Тело запроса", "text/plain", jsonBody);
        System.out.println("JSON Тело запроса: " + jsonBody);

        Allure.step("Отправка GraphQL запроса и получение ответа", () -> {
            String info = "Отправка запроса...";
            System.out.println(info);
            Allure.addAttachment("Отправка запроса", "text/plain", info);
        });
        Response response = GraphQLClient.sendRequest(jsonBody);
        String responsePretty = response.getBody().asPrettyString();
        Allure.addAttachment("Ответ", "text/plain", responsePretty);
        System.out.println("Ответ: " + responsePretty);

        Allure.step("Валидация кода статуса и списка языков", () -> {
            String info = "Проводится валидация: статус и список языков";
            System.out.println(info);
            Allure.addAttachment("Валидация", "text/plain", info);
            ResponseValidator.validateStatusCode(response);
            ResponseValidator.validateLanguages(response);
        });
    }
}
