package com.v2modules.tabylga.api.utils;

import io.restassured.response.Response;
import io.qameta.allure.Allure;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseValidator {

    public static void validateStatusCode(Response response) {
        Allure.step("Проверка HTTP статус-кода", () -> {
            int statusCode = response.getStatusCode();
            String message = "HTTP Status Code: " + statusCode;
            System.out.println(message);
            Allure.addAttachment("HTTP статус-код", "text/plain", String.valueOf(statusCode));
            assertEquals(200, statusCode, "Некорректный статус-код!");
        });
    }

    public static void validateCategories(Response response) {
        Allure.step("Проверка категорий", () -> {
            List<Map<String, Object>> categories = response.jsonPath().getList("data.getCategories");
            String countMessage = "Categories Count: " + categories.size();
            System.out.println(countMessage);
            Allure.addAttachment("Количество категорий", "text/plain", String.valueOf(categories.size()));

            int minimumExpectedCategories = 1608;
            assertTrue(categories.size() >= minimumExpectedCategories,
                    "Ожидалось минимум " + minimumExpectedCategories + " категорий, но получено " + categories.size());
            assertFalse(categories.isEmpty(), "Список Категорий пуст");

            // Проверка первой категории
            Map<String, Object> firstCategory = categories.get(0);
            String firstCategoryInfo = "First Category ID: " + firstCategory.get("id") + "\nFirst Category Name: " + firstCategory.get("name");
            System.out.println(firstCategoryInfo);
            Allure.addAttachment("Первая категория", "text/plain", firstCategoryInfo);
            assertNotNull(firstCategory.get("id"), "ID первой категории не должен быть null");
            assertNotNull(firstCategory.get("name"), "Название первой категории не должно быть null");

            // Проверка последней категории
            Map<String, Object> lastCategory = categories.get(categories.size() - 1);
            String lastCategoryInfo = "Last Category ID: " + lastCategory.get("id") + "\nLast Category Name: " + lastCategory.get("name");
            System.out.println(lastCategoryInfo);
            Allure.addAttachment("Последняя категория", "text/plain", lastCategoryInfo);
            assertNotNull(lastCategory.get("id"), "ID последней категории не должен быть null");
            assertNotNull(lastCategory.get("name"), "Название последней категории не должно быть null");

            // Проверка вложенной структуры icon (если присутствует)
            Map<String, Object> icon = (Map<String, Object>) firstCategory.get("icon");
            if (icon != null) {
                String iconInfo = "Icon URL: " + icon.get("url");
                System.out.println(iconInfo);
                Allure.addAttachment("Иконка", "text/plain", iconInfo);
                assertNotNull(icon.get("url"), "URL иконки не должен быть null");
            }
        });
    }

    public static void validateBusinessProfiles(Response response) {
        Allure.step("Проверка бизнес-профилей", () -> {
            Map<String, Object> businessProfiles = response.jsonPath().getMap("data.getBusinessProfilesList");
            String bpInfo = "Total Business Profiles: " + businessProfiles.get("totalItems") +
                    "\nTotal Pages: " + businessProfiles.get("totalPages");
            System.out.println(bpInfo);
            Allure.addAttachment("Бизнес-профили", "text/plain", bpInfo);
            assertTrue((Integer) businessProfiles.get("totalItems") > 0, "Количество бизнес-профилей должно быть больше 0");
            assertTrue((Integer) businessProfiles.get("totalPages") > 0, "Количество страниц должно быть больше 0");
        });
    }

    public static void validateCurrencies(Response response) {
        Allure.step("Проверка валют", () -> {
            List<Map<String, Object>> currencies = response.jsonPath().getList("data.getCurrencies");
            String countMessage = "Currencies Count: " + currencies.size();
            System.out.println(countMessage);
            Allure.addAttachment("Количество валют", "text/plain", String.valueOf(currencies.size()));
            assertFalse(currencies.isEmpty(), "Список валют пуст");

            // Проверка первой валюты (USD)
            Map<String, Object> firstCurrency = currencies.get(0);
            String firstCurrencyInfo = "First Currency ID: " + firstCurrency.get("id") +
                    "\nFirst Currency Code: " + firstCurrency.get("code") +
                    "\nFirst Currency Name: " + firstCurrency.get("name") +
                    "\nIs Main Currency: " + firstCurrency.get("isMain");
            System.out.println(firstCurrencyInfo);
            Allure.addAttachment("Первая валюта", "text/plain", firstCurrencyInfo);
            assertEquals("usd", firstCurrency.get("id"), "Неверное значение ID первой валюты");
            assertEquals("usd", firstCurrency.get("code"), "Неверное значение кода первой валюты");
            assertEquals("USD", firstCurrency.get("name"), "Неверное значение названия первой валюты");
            assertEquals(false, firstCurrency.get("isMain"), "Неверное значение isMain первой валюты");

            // Проверка второй валюты (EUR)
            Map<String, Object> secondCurrency = currencies.get(1);
            String secondCurrencyInfo = "Second Currency ID: " + secondCurrency.get("id") +
                    "\nSecond Currency Code: " + secondCurrency.get("code") +
                    "\nSecond Currency Name: " + secondCurrency.get("name") +
                    "\nIs Main Currency: " + secondCurrency.get("isMain");
            System.out.println(secondCurrencyInfo);
            Allure.addAttachment("Вторая валюта", "text/plain", secondCurrencyInfo);
            assertEquals("eur", secondCurrency.get("id"), "Неверное значение ID второй валюты");
            assertEquals("eur", secondCurrency.get("code"), "Неверное значение кода второй валюты");
            assertEquals("EUR", secondCurrency.get("name"), "Неверное значение названия второй валюты");
            assertEquals(false, secondCurrency.get("isMain"), "Неверное значение isMain второй валюты");

            // Проверка третьей валюты (KGS)
            Map<String, Object> thirdCurrency = currencies.get(2);
            String thirdCurrencyInfo = "Third Currency ID: " + thirdCurrency.get("id") +
                    "\nThird Currency Code: " + thirdCurrency.get("code") +
                    "\nThird Currency Name: " + thirdCurrency.get("name") +
                    "\nIs Main Currency: " + thirdCurrency.get("isMain");
            System.out.println(thirdCurrencyInfo);
            Allure.addAttachment("Третья валюта", "text/plain", thirdCurrencyInfo);
            assertEquals("kgs", thirdCurrency.get("id"), "Неверное значение ID третьей валюты");
            assertEquals("kgs", thirdCurrency.get("code"), "Неверное значение кода третьей валюты");
            assertEquals("KGS", thirdCurrency.get("name"), "Неверное значение названия третьей валюты");
            assertEquals(true, thirdCurrency.get("isMain"), "Неверное значение isMain третьей валюты");

            // Проверка общего количества валют
            assertEquals(3, currencies.size(), "Ожидалось 3 валюты, но получено " + currencies.size());
        });
    }

    public static void validateLanguages(Response response) {
        Allure.step("Проверка языков", () -> {
            List<Map<String, Object>> languages = response.jsonPath().getList("data.getLanguages");
            String countMessage = "Languages Count: " + languages.size();
            System.out.println(countMessage);
            Allure.addAttachment("Количество языков", "text/plain", String.valueOf(languages.size()));
            assertFalse(languages.isEmpty(), "Список языков пуст");

            // Проверка первого языка (Русский)
            Map<String, Object> firstLanguage = languages.get(0);
            String firstLanguageInfo = "First Language ID: " + firstLanguage.get("id") +
                    "\nFirst Language Name: " + firstLanguage.get("name");
            System.out.println(firstLanguageInfo);
            Allure.addAttachment("Первый язык", "text/plain", firstLanguageInfo);
            assertEquals("ru", firstLanguage.get("id"), "Неверное значение ID первого языка");
            assertEquals("Русский", firstLanguage.get("name"), "Неверное значение названия первого языка");

            // Проверка второго языка (Кыргызский)
            Map<String, Object> secondLanguage = languages.get(1);
            String secondLanguageInfo = "Second Language ID: " + secondLanguage.get("id") +
                    "\nSecond Language Name: " + secondLanguage.get("name");
            System.out.println(secondLanguageInfo);
            Allure.addAttachment("Второй язык", "text/plain", secondLanguageInfo);
            assertEquals("ky", secondLanguage.get("id"), "Неверное значение ID второго языка");
            assertEquals("Кыргыз тили", secondLanguage.get("name"), "Неверное значение названия второго языка");

            // Проверка третьего языка (Английский)
            Map<String, Object> thirdLanguage = languages.get(2);
            String thirdLanguageInfo = "Third Language ID: " + thirdLanguage.get("id") +
                    "\nThird Language Name: " + thirdLanguage.get("name");
            System.out.println(thirdLanguageInfo);
            Allure.addAttachment("Третий язык", "text/plain", thirdLanguageInfo);
            assertEquals("en", thirdLanguage.get("id"), "Неверное значение ID третьего языка");
            assertEquals("English", thirdLanguage.get("name"), "Неверное значение названия третьего языка");

            // Проверка общего количества языков
            assertEquals(3, languages.size(), "Ожидалось 3 языка, но получено " + languages.size());
        });
    }
}
