package com.v2modules.tabylga.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    // Элементы страницы с аннотациями @FindBy
    @FindBy(xpath = "//button[contains(text(),'Войти/Зарегистрироваться')]")
    private WebElement loginPageButton;
    @FindBy(xpath = "//input[@name='login']")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[contains(@class, 'MuiButton-containedPrimary')]")
    private WebElement loginButton;
    @FindBy(xpath = "//p[contains(text(),'Поле обязательно для заполнения')]")
    private WebElement errorMessageEmpty;
    @FindBy(xpath = "//p[contains(text(),'Телефон в формате:996000000000')]")
    private WebElement errorMessageLoginPhoneNumber;
    @FindBy(xpath = "//p[contains(text(),'Поле должно быть действительным адресом электронной почты.')]")
    private WebElement errorMessageLoginEmail;
    @FindBy(xpath = "//p[contains(text(),'Слишком коротко (минимум 5 символов)')]")
    private WebElement errorMessagePassword;
    @FindBy(xpath = "//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Account not found.')]")
    private WebElement errorMessageAccountNotFound;
    @FindBy(xpath = "//div[contains(@class, 'MuiAlert-message') and text()='Invalid account credentials']")
    private WebElement errorMessageInvalidAccountCredentials;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this); // Инициализация элементов
    }

    public void clickLoginPageButton() {
        loginPageButton.click();
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    // Универсальный метод проверки видимости элемента
    private boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    // Универсальный метод получения текста ошибки
    private String getErrorMessage(WebElement errorElement) {
        return isElementDisplayed(errorElement) ? errorElement.getText() : "";
    }

    // Методы получения конкретных ошибок
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