package com.v2modules.tabylga.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By loginPageButton = By.xpath("//button[contains(text(),'Войти/Зарегистрироваться')]");
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
    }

    public void clickLoginPageButton() {
        driver.findElement(loginPageButton).click();
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isErrorMessageDisplayed(By locator) {
        return !driver.findElements(locator).isEmpty() && driver.findElement(locator).isDisplayed();
    }

    public String getErrorMessage(By locator) {
        return isErrorMessageDisplayed(locator) ? driver.findElement(locator).getText() : "";
    }

    // Методы получения текста ошибок
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