package com.v2modules.tabylga.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Обновлённый локатор с использованием Unicode-эскейпов для русских символов "Войти/Зарегистрироваться"
    private final By loginPageButton = By.xpath("//button[contains(text(),'\u0412\u043E\u0439\u0442\u0438/\u0417\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043E\u0432\u0430\u0442\u044C\u0441\u044F')]");
    private final By usernameField = By.xpath("//input[@name='login']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//button[contains(@class, 'MuiButton-containedPrimary')]");
    private final By errorMessageEmpty = By.xpath("//p[contains(text(),'Поле обязательно для заполнения')]");
    private final By errorMessageLoginPhoneNumber = By.xpath("//p[contains(text(),'Телефон в формате:996000000000')]");
    private final By errorMessageLoginEmail = By.xpath("//p[contains(text(),'Поле должно быть действительным адресом электронной почты.')]");
    private final By errorMessagePassword = By.xpath("//p[contains(text(),'Слишком коротко (минимум 5 символов)')]");
    private final By errorMessageAccountNotFound = By.xpath("//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Account not found.')]");
    private final By errorMessageInvalidAccountCredentials = By.xpath("//div[contains(@class, 'MuiAlert-message') and text()='Invalid account credentials']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Устанавливаем время ожидания в 15 секунд
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void clickLoginPageButton() {
        System.out.println("Нажатие кнопки 'Войти/Зарегистрироваться'...");
        waitForClickable(loginPageButton).click();
    }

    public void enterUsername(String username) {
        System.out.println("Ввод имени пользователя: " + username);
        waitForVisibility(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        System.out.println("Ввод пароля.");
        waitForVisibility(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        System.out.println("Нажатие кнопки 'Войти'...");
        waitForClickable(loginButton).click();
    }

    // Утилитный метод для получения текста ошибки
    public String getErrorMessage(By locator) {
        try {
            return waitForVisibility(locator).getText();
        } catch (Exception e) {
            System.out.println("Не удалось получить текст для локатора " + locator.toString() + ". Ошибка: " + e.getMessage());
            return "";
        }
    }

    // Методы получения сообщений об ошибках
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
