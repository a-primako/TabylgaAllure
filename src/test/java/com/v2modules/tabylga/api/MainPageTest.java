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
        Allure.step("Загрузка GraphQL запроса для получения количества категорий и бизнес-профилей", () -> {
            // Загрузка запроса из файла
        });
        String query = GraphQLFileUtil.loadGraphQLQuery("src/test/resources/graphql/getCategoryAndBusinessProfilesCount.graphql");

        Allure.step("Формирование JSON тела запроса без переменных", () -> { });
        String jsonBody = GraphQLQueryBuilder.buildQuery(query, null);

        Allure.step("Отправка GraphQL запроса", () -> { });
        Response response = GraphQLClient.sendRequest(jsonBody);

        // Прикрепление ответа для отладки
        Allure.addAttachment("Ответ", response.getBody().asPrettyString());

        Allure.step("Валидация кода статуса, категорий и бизнес-профилей", () -> {
            ResponseValidator.validateStatusCode(response);
            ResponseValidator.validateCategories(response);
            ResponseValidator.validateBusinessProfiles(response);
        });
    }

    @Test
    @Feature("Отфильтрованные категории и бизнес-профили")
    @Story("Получение категорий и бизнес-профилей с фильтрацией скрытых категорий")
    @DisplayName("Проверка отфильтрованных категорий и бизнес-профилей с переменными")
    @Description("Данный тест проверяет, что API возвращает корректное количество категорий и бизнес-профилей при фильтрации скрытых категорий.")
    public void getFilteredCategoriesAndBusinessProfilesWithVariablesTest() {
        Allure.step("Загрузка GraphQL запроса для получения категорий и бизнес-профилей с фильтрацией", () -> { });
        String query = GraphQLFileUtil.loadGraphQLQuery("src/test/resources/graphql/getCategoryAndBusinessProfilesCount.graphql");

        Allure.step("Формирование переменных для фильтрации (скрытые категории: false)", () -> { });
        Map<String, Object> variables = Map.of("where", Map.of("hidden", false));

        Allure.step("Формирование JSON тела запроса с переменными", () -> { });
        String jsonBody = GraphQLQueryBuilder.buildQuery(query, variables);

        Allure.step("Отправка GraphQL запроса", () -> { });
        Response response = GraphQLClient.sendRequest(jsonBody);

        // Прикрепление ответа для отладки
        Allure.addAttachment("Ответ", response.getBody().asPrettyString());

        Allure.step("Валидация кода статуса, категорий и бизнес-профилей", () -> {
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
        Allure.step("Загрузка GraphQL запроса для получения валют", () -> { });
        String query = GraphQLFileUtil.loadGraphQLQuery("src/test/resources/graphql/getCurrencies.graphql");

        Allure.step("Формирование JSON тела запроса без переменных", () -> { });
        String jsonBody = GraphQLQueryBuilder.buildQuery(query, null);

        Allure.step("Отправка GraphQL запроса", () -> { });
        Response response = GraphQLClient.sendRequest(jsonBody);

        // Прикрепление ответа для отладки
        Allure.addAttachment("Ответ", response.getBody().asPrettyString());

        Allure.step("Валидация кода статуса и списка валют", () -> {
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
        Allure.step("Загрузка GraphQL запроса для получения языков", () -> { });
        String query = GraphQLFileUtil.loadGraphQLQuery("src/test/resources/graphql/getLanguages.graphql");

        Allure.step("Формирование JSON тела запроса без переменных", () -> { });
        String jsonBody = GraphQLQueryBuilder.buildQuery(query, null);

        Allure.step("Отправка GraphQL запроса", () -> { });
        Response response = GraphQLClient.sendRequest(jsonBody);

        // Прикрепление ответа для отладки
        Allure.addAttachment("Ответ", response.getBody().asPrettyString());

        Allure.step("Валидация кода статуса и списка языков", () -> {
            ResponseValidator.validateStatusCode(response);
            ResponseValidator.validateLanguages(response);
        });
    }
}
