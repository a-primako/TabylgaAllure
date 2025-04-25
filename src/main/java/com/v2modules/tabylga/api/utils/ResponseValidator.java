package com.v2modules.tabylga.api.utils;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseValidator {

    public static void validateStatusCode(Response response) {
        int statusCode = response.getStatusCode();
        System.out.println("HTTP Status Code: " + statusCode);
        assertEquals(200, statusCode, "Некорректный статус-код!");
    }

    //В дальнейшем нужно проверить сравнение из БД по количеству категорий
    public static void validateCategories(Response response) {
        List<Map<String, Object>> categories = response.jsonPath().getList("data.getCategories");
        System.out.println("Categories Count: " + categories.size());

        // Проверка общего количества категорий.
        // Поскольку категории постоянно добавляются, используем минимальное ожидаемое значение.
        int minimumExpectedCategories = 1608;
        assertTrue(categories.size() >= minimumExpectedCategories,
                "Ожидалось минимум " + minimumExpectedCategories + " категорий, но получено " + categories.size());
        assertFalse(categories.isEmpty(), "Список Категорий пуст");

        // Проверка первой категории
        Map<String, Object> firstCategory = categories.get(0);
        System.out.println("First Category ID: " + firstCategory.get("id"));
        System.out.println("First Category Name: " + firstCategory.get("name"));

        assertNotNull(firstCategory.get("id"), "ID первой категории не должен быть null");
        assertNotNull(firstCategory.get("name"), "Название первой категории не должно быть null");

        // Проверка и вывод последней найденной категории
        Map<String, Object> lastCategory = categories.get(categories.size() - 1);
        System.out.println("Last Category ID: " + lastCategory.get("id"));
        System.out.println("Last Category Name: " + lastCategory.get("name"));

        assertNotNull(lastCategory.get("id"), "ID последней категории не должен быть null");
        assertNotNull(lastCategory.get("name"), "Название последней категории не должно быть null");


        // Проверка вложенной структуры icon
        Map<String, Object> icon = (Map<String, Object>) firstCategory.get("icon");
        if (icon != null) {
            System.out.println("Icon URL: " + icon.get("url"));
            assertNotNull(icon.get("url"));
        }
    }

//В дальнейшем нужно проверить сравнение из БД по количеству страниц бизнес-профилей
    public static void validateBusinessProfiles(Response response) {
        Map<String, Object> businessProfiles = response.jsonPath().getMap("data.getBusinessProfilesList");
        System.out.println("Total Business Profiles: " + businessProfiles.get("totalItems"));
        System.out.println("Total Pages: " + businessProfiles.get("totalPages"));

        assertTrue((Integer) businessProfiles.get("totalItems") > 0);
        assertTrue((Integer) businessProfiles.get("totalPages") > 0);
    }

    public static void validateCurrencies(Response response) {
        List<Map<String, Object>> currencies = response.jsonPath().getList("data.getCurrencies");
        System.out.println("Currencies Count: " + currencies.size());
        assertFalse(currencies.isEmpty(), "Список валют пуст");

        // Проверка первой валюты (USD)
        Map<String, Object> firstCurrency = currencies.get(0);
        System.out.println("First Currency ID: " + firstCurrency.get("id"));
        System.out.println("First Currency Code: " + firstCurrency.get("code"));
        System.out.println("First Currency Name: " + firstCurrency.get("name"));
        System.out.println("Is Main Currency: " + firstCurrency.get("isMain"));

        assertEquals("usd", firstCurrency.get("id"));
        assertEquals("usd", firstCurrency.get("code"));
        assertEquals("USD", firstCurrency.get("name"));
        assertEquals(false, firstCurrency.get("isMain"));

        // Проверка второй валюты (EUR)
        Map<String, Object> secondCurrency = currencies.get(1);
        System.out.println("Second Currency ID: " + secondCurrency.get("id"));
        System.out.println("Second Currency Code: " + secondCurrency.get("code"));
        System.out.println("Second Currency Name: " + secondCurrency.get("name"));
        System.out.println("Is Main Currency: " + secondCurrency.get("isMain"));

        assertEquals("eur", secondCurrency.get("id"));
        assertEquals("eur", secondCurrency.get("code"));
        assertEquals("EUR", secondCurrency.get("name"));
        assertEquals(false, secondCurrency.get("isMain"));

        // Проверка третьей валюты (KGS)
        Map<String, Object> thirdCurrency = currencies.get(2);
        System.out.println("Third Currency ID: " + thirdCurrency.get("id"));
        System.out.println("Third Currency Code: " + thirdCurrency.get("code"));
        System.out.println("Third Currency Name: " + thirdCurrency.get("name"));
        System.out.println("Is Main Currency: " + thirdCurrency.get("isMain"));

        assertEquals("kgs", thirdCurrency.get("id"));
        assertEquals("kgs", thirdCurrency.get("code"));
        assertEquals("KGS", thirdCurrency.get("name"));
        assertEquals(true, thirdCurrency.get("isMain"));

        // Проверка общего количества валют
        assertEquals(3, currencies.size(), "Ожидалось 3 валюты, но получено " + currencies.size());
    }

    public static void validateLanguages(Response response) {
        List<Map<String, Object>> languages = response.jsonPath().getList("data.getLanguages");
        System.out.println("Languages Count: " + languages.size());
        assertFalse(languages.isEmpty(), "Список языков пуст");

        // Проверка первого языка (Русский)
        Map<String, Object> firstLanguage = languages.get(0);
        System.out.println("First Language ID: " + firstLanguage.get("id"));
        System.out.println("First Language Name: " + firstLanguage.get("name"));

        assertEquals("ru", firstLanguage.get("id"));
        assertEquals("Русский", firstLanguage.get("name"));

        // Проверка второго языка (Кыргызский)
        Map<String, Object> secondLanguage = languages.get(1);
        System.out.println("Second Language ID: " + secondLanguage.get("id"));
        System.out.println("Second Language Name: " + secondLanguage.get("name"));

        assertEquals("ky", secondLanguage.get("id"));
        assertEquals("Кыргыз тили", secondLanguage.get("name"));

        // Проверка третьего языка (Английский)
        Map<String, Object> thirdLanguage = languages.get(2);
        System.out.println("Third Language ID: " + thirdLanguage.get("id"));
        System.out.println("Third Language Name: " + thirdLanguage.get("name"));

        assertEquals("en", thirdLanguage.get("id"));
        assertEquals("English", thirdLanguage.get("name"));

        // Проверка общего количества языков
        assertEquals(3, languages.size(), "Ожидалось 3 языка, но получено " + languages.size());
    }
}
