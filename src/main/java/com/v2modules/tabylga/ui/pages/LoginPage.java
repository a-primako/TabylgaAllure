package com.v2modules.tabylga.ui.pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriverWait wait;

    // Локаторы. Для кнопки используем Unicode-эскейпы, чтобы избежать проблем с кодировкой:
    private final By loginPageButton = By.xpath("//button[contains(text(),'\u0412\u043E\u0439\u0442\u0438/\u0417\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043E\u0432\u0430\u0442\u044C\u0441\u044F')]");
    private final By usernameField = By.xpath("//input[@name='login']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//button[contains(@class, 'MuiButton-containedPrimary')]");
    private final By errorMessageEmpty = By.xpath("//p[contains(text(),'Поле обязательно для заполнения')]");
    private final By errorMessageLoginPhoneNumber = By.xpath("//p[contains(text(),'Телефон в формате:996000000000')]");
    private final By errorMessageLoginEmail = By.xpath("//p[contains(text(),'Поле должно быть действительным адресом электронной почты.')]");
    private final By errorMessagePassword = By.xpath("//p[contains(text(),'Слишком коротко (минимум 5 символов)')]");
    private final By errorMessageAccountNotFound = By.xpath("//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Account not found.')]");
    private final By errorMessageInvalidAccountCredentials = By.xpath("//div[contains(text(), 'Invalid account credentials')]");
    private final By isUserLoggedIn = By.xpath("//a[contains(@class, 'MuiLink-root') and @href='/account/wallet']");


    public LoginPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Ожидание видимости элемента
    private WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Ожидание, когда элемент будет кликабельным
    private WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void clickLoginPageButton() {
        String stepName = "Нажатие кнопки 'Войти/Зарегистрироваться'";
        System.out.println(stepName);
        Allure.step(stepName, () -> {
            WebElement element = waitForClickable(loginPageButton);
            String buttonText = element.getText();
            System.out.println("Текст кнопки: " + buttonText);
            Allure.addAttachment("Текст кнопки", "text/plain", buttonText);
            element.click();
        });
    }

    public void enterUsername(String username) {
        String stepName = "Ввод имени пользователя: " + username;
        System.out.println(stepName);
        Allure.step(stepName, () -> {
            WebElement input = waitForVisibility(usernameField);
            input.sendKeys(username);
            Allure.addAttachment("Введенное имя пользователя", "text/plain", username);
        });
    }

    public void enterPassword(String password) {
        String stepName = "Ввод пароля";
        System.out.println(stepName);
        Allure.step(stepName, () -> {
            WebElement input = waitForVisibility(passwordField);
            input.sendKeys(password);
            Allure.addAttachment("Введенный пароль", "text/plain", password);
        });
    }

    public void clickLoginButton() {
        String stepName = "Нажатие кнопки 'Войти'";
        System.out.println(stepName);
        Allure.step(stepName, () -> {
            WebElement element = waitForClickable(loginButton);
            String buttonText = element.getText();
            System.out.println("Текст кнопки: " + buttonText);
            Allure.addAttachment("Текст кнопки", "text/plain", buttonText);
            element.click();
        });
    }

    public String getErrorMessage(By locator) {
        String stepName = "Получение текста ошибки для локатора: " + locator.toString();
        System.out.println(stepName);
        return Allure.step(stepName, () -> {
            try {
                String errorText = waitForVisibility(locator).getText();
                System.out.println("Текст ошибки: " + errorText);
                Allure.addAttachment("Текст ошибки", "text/plain", errorText);
                return errorText;
            } catch (Exception e) {
                String errorInfo = "Ошибка получения текста: " + e.getMessage();
                System.out.println(errorInfo);
                Allure.addAttachment("Ошибка", "text/plain", errorInfo);
                return "";
            }
        });
    }

    public boolean isUserLoggedIn() {
        String stepName = "Проверка, вошел ли пользователь в систему";
        System.out.println(stepName);
        return Allure.step(stepName, () -> {
            try {
                boolean isLoggedIn = wait.until(ExpectedConditions.visibilityOfElementLocated(isUserLoggedIn)).isDisplayed();
                Allure.addAttachment("Статус входа", "text/plain", "Пользователь авторизован: " + isLoggedIn);
                return isLoggedIn;
            } catch (Exception e) {
                String errorInfo = "Ошибка проверки авторизации: " + e.getMessage();
                System.out.println(errorInfo);
                Allure.addAttachment("Ошибка", "text/plain", errorInfo);
                return false;
            }
        });
    }

    public String getErrorMessageEmpty() {
        return getErrorMessage(errorMessageEmpty);
    }

    public String getErrorMessageLoginPhoneNumber() {
        return getErrorMessage(errorMessageLoginPhoneNumber);
    }

    public String getErrorMessageLoginEmail() {
        return getErrorMessage(errorMessageLoginEmail);
    }

    public String getErrorMessagePassword() {
        return getErrorMessage(errorMessagePassword);
    }

    public String getErrorMessageAccountNotFound() {
        return getErrorMessage(errorMessageAccountNotFound);
    }

    public String getErrorMessageInvalidAccountCredentials() {
        return getErrorMessage(errorMessageInvalidAccountCredentials);
    }
}
