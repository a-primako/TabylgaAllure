package com.v2modules.tabylga.ui;

import com.v2modules.tabylga.ui.pages.LoginPage;
import com.v2modules.tabylga.ui.utils.Singleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = Singleton.getDriver();
        driver.get("https://tabylga.app/");
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Проверка ошибки при пустых учетных данных")
    public void testEmptyCredentials() {
        // Переход на форму входа
        loginPage.clickLoginPageButton();
        // Нажатие кнопки логина без ввода данных
        loginPage.clickLoginButton();

        // Получаем текст ошибки, ожидаем "Поле обязательно для заполнения"
        String actualMessage = loginPage.getErrorMessageEmpty();
        String expectedMessage = "Поле обязательно для заполнения";
        System.out.println("Полученное сообщение: " + actualMessage);
        System.out.println("Ожидаемое сообщение: " + expectedMessage);

        assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Проверка ошибки при неверном формате телефона")
    public void testLoginWithInvalidLoginPhone() {
        loginPage.clickLoginPageButton();
        loginPage.enterUsername("9965550000");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();

        String actualMessage = loginPage.getErrorMessageLoginPhoneNumber();
        String expectedMessage = "Телефон в формате:996000000000";
        System.out.println("Полученное сообщение: " + actualMessage);
        System.out.println("Ожидаемое сообщение: " + expectedMessage);

        assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Проверка ошибки при неверном формате email")
    public void testLoginWithInvalidLoginEmail() {
        loginPage.clickLoginPageButton();
        loginPage.enterUsername("test"); // Здесь ввод некорректного email
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();

        String actualMessage = loginPage.getErrorMessageLoginEmail();
        String expectedMessage = "Поле должно быть действительным адресом электронной почты.";
        System.out.println("Полученное сообщение: " + actualMessage);
        System.out.println("Ожидаемое сообщение: " + expectedMessage);

        assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Проверка ошибки при слишком коротком пароле")
    public void testLoginWithInvalidPassword() {
        loginPage.clickLoginPageButton();
        loginPage.enterUsername("996555444444");
        loginPage.enterPassword("inv"); // Слишком короткий пароль
        loginPage.clickLoginButton();

        String actualMessage = loginPage.getErrorMessagePassword();
        String expectedMessage = "Слишком коротко (минимум 5 символов)";
        System.out.println("Полученное сообщение: " + actualMessage);
        System.out.println("Ожидаемое сообщение: " + expectedMessage);

        assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Проверка ошибки при неверном сочетании логина и пароля (аккаунт не найден)")
    public void testLoginWithInvalidLoginAndPassword() {
        loginPage.clickLoginPageButton();
        loginPage.enterUsername("996555444489");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();

        String actualMessage = loginPage.getErrorMessageAccountNotFound();
        String expectedMessage = "Account not found.";
        System.out.println("Полученное сообщение: " + actualMessage);
        System.out.println("Ожидаемое сообщение: " + expectedMessage);

        assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Проверка ошибки при корректном логине и неверном пароле")
    public void testLoginWithValidLoginAndInvalidPassword() {
        loginPage.clickLoginPageButton();
        loginPage.enterUsername("996555444444");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();

        String actualMessage = loginPage.getErrorMessageInvalidAccountCredentials();
        String expectedMessage = "Invalid account credentials";
        System.out.println("Полученное сообщение: " + actualMessage);
        System.out.println("Ожидаемое сообщение: " + expectedMessage);

        assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
    }

    @AfterEach
    public void tearDown() {
        Singleton.quitDriver();
    }
}
