package com.v2modules.tabylga.ui;

import com.v2modules.tabylga.ui.pages.LoginPage;
import com.v2modules.tabylga.ui.utils.Singleton;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI Тесты")
@Feature("Авторизация/Логин")
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        Allure.step("Инициализация драйвера и открытие страницы", () -> {
            driver = Singleton.getDriver();
            driver.get("https://tabylga.app/");
            loginPage = new LoginPage(driver);
        });
    }

    @Test
    @Story("Проверка ошибки при пустых учётных данных")
    @DisplayName("Пустые учётные данные")
    @Description("Тест проверяет, что при попытке авторизации без ввода данных отображается корректное сообщение об ошибке")
    public void testEmptyCredentials() {
        Allure.step("Нажатие кнопки для перехода на страницу логина", () ->
                loginPage.clickLoginPageButton()
        );
        Allure.step("Нажатие кнопки входа без ввода данных", () ->
                loginPage.clickLoginButton()
        );
        String actualMessage = loginPage.getErrorMessageEmpty();
        String expectedMessage = "Поле обязательно для заполнения";
        Allure.step("Получение и проверка сообщения об ошибке", () -> {
            Allure.addAttachment("Фактическое сообщение", actualMessage);
            Allure.addAttachment("Ожидаемое сообщение", expectedMessage);
            assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
        });
    }

    @Test
    @Story("Проверка авторизации с неверным форматом номера телефона")
    @DisplayName("Неверный номер телефона")
    @Description("Тест проверяет вывод корректного сообщения об ошибке при вводе номера телефона в неправильном формате")
    public void testLoginWithInvalidLoginPhone() {
        Allure.step("Переход на страницу логина", () ->
                loginPage.clickLoginPageButton()
        );
        Allure.step("Ввод номера телефона", () ->
                loginPage.enterUsername("9965550000")
        );
        Allure.step("Ввод неверного пароля", () ->
                loginPage.enterPassword("invalid_password")
        );
        Allure.step("Нажатие кнопки входа", () ->
                loginPage.clickLoginButton()
        );
        String actualMessage = loginPage.getErrorMessageLoginPhoneNumber();
        String expectedMessage = "Телефон в формате:996000000000";
        Allure.step("Проверка сообщения об ошибке", () -> {
            Allure.addAttachment("Фактическое сообщение", actualMessage);
            Allure.addAttachment("Ожидаемое сообщение", expectedMessage);
            assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
        });
    }

    @Test
    @Story("Проверка авторизации с неверным форматом email")
    @DisplayName("Неверный email")
    @Description("Тест проверяет вывод корректного сообщения об ошибке при вводе некорректного email")
    public void testLoginWithInvalidLoginEmail() {
        Allure.step("Переход на страницу логина", () ->
                loginPage.clickLoginPageButton()
        );
        Allure.step("Ввод некорректного email", () ->
                loginPage.enterUsername("test")
        );
        Allure.step("Ввод неверного пароля", () ->
                loginPage.enterPassword("invalid_password")
        );
        Allure.step("Нажатие кнопки входа", () ->
                loginPage.clickLoginButton()
        );
        String actualMessage = loginPage.getErrorMessageLoginEmail();
        String expectedMessage = "Поле должно быть действительным адресом электронной почты.";
        Allure.step("Проверка сообщения об ошибке", () -> {
            Allure.addAttachment("Фактическое сообщение", actualMessage);
            Allure.addAttachment("Ожидаемое сообщение", expectedMessage);
            assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
        });
    }

    @Test
    @Story("Проверка авторизации с некорректной длиной пароля")
    @DisplayName("Слишком короткий пароль")
    @Description("Тест проверяет, что при вводе слишком короткого пароля выводится корректное сообщение об ошибке")
    public void testLoginWithInvalidPassword() {
        Allure.step("Переход на страницу логина", () ->
                loginPage.clickLoginPageButton()
        );
        Allure.step("Ввод номера телефона", () ->
                loginPage.enterUsername("996555444444")
        );
        Allure.step("Ввод слишком короткого пароля", () ->
                loginPage.enterPassword("inv")
        );
        Allure.step("Нажатие кнопки входа", () ->
                loginPage.clickLoginButton()
        );
        String actualMessage = loginPage.getErrorMessagePassword();
        String expectedMessage = "Слишком коротко (минимум 5 символов)";
        Allure.step("Проверка сообщения об ошибке", () -> {
            Allure.addAttachment("Фактическое сообщение", actualMessage);
            Allure.addAttachment("Ожидаемое сообщение", expectedMessage);
            assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
        });
    }

    @Test
    @Story("Проверка авторизации с неверными данными для входа")
    @DisplayName("Аккаунт не найден")
    @Description("Тест проверяет вывод корректного сообщения, если аккаунт не найден при неправильном сочетании логина и пароля")
    public void testLoginWithInvalidLoginAndPassword() {
        Allure.step("Переход на страницу логина", () ->
                loginPage.clickLoginPageButton()
        );
        Allure.step("Ввод неверного логина", () ->
                loginPage.enterUsername("996555444489")
        );
        Allure.step("Ввод неверного пароля", () ->
                loginPage.enterPassword("invalid_password")
        );
        Allure.step("Нажатие кнопки входа", () ->
                loginPage.clickLoginButton()
        );
        String actualMessage = loginPage.getErrorMessageAccountNotFound();
        String expectedMessage = "Account not found.";
        Allure.step("Проверка сообщения об ошибке", () -> {
            Allure.addAttachment("Фактическое сообщение", actualMessage);
            Allure.addAttachment("Ожидаемое сообщение", expectedMessage);
            assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
        });
    }

    @Test
    @Story("Проверка авторизации с корректным логином и неверным паролем")
    @DisplayName("Неверные учетные данные")
    @Description("Тест проверяет, что при вводе корректного логина и неверного пароля выводится сообщение об ошибке")
    public void testLoginWithValidLoginAndInvalidPassword() {
        Allure.step("Переход на страницу логина", () ->
                loginPage.clickLoginPageButton()
        );
        Allure.step("Ввод корректного логина", () ->
                loginPage.enterUsername("996555444444")
        );
        Allure.step("Ввод неверного пароля", () ->
                loginPage.enterPassword("invalid_password")
        );
        Allure.step("Нажатие кнопки входа", () ->
                loginPage.clickLoginButton()
        );
        String actualMessage = loginPage.getErrorMessageInvalidAccountCredentials();
        String expectedMessage = "Invalid account credentials";
        Allure.step("Проверка сообщения об ошибке", () -> {
            Allure.addAttachment("Фактическое сообщение", actualMessage);
            Allure.addAttachment("Ожидаемое сообщение", expectedMessage);
            assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
        });
    }

    @AfterEach
    public void tearDown() {
        Allure.step("Завершение работы и закрытие драйвера", () ->
                Singleton.quitDriver()
        );
    }
}
