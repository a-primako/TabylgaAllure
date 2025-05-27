package com.v2modules.tabylga.ui.pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.v2modules.tabylga.ui.utils.Singleton.driver;

public class MainPage {
    private final WebDriverWait wait;

    private final By burgerMenuButton = By.xpath("//div[@class='MuiBox-root mui-style-yh9184']");
    private final By menuBusinessTabylgaTab = By.xpath("//a[@href='/kyrgyzstan/business-profiles']");
    private final By counterMenuBusinessTabylgaTab = By.xpath("//*[@id=\"__next\"]/div/main/div[1]/div/div/div[2]/span[1]/div[2]/div/ul/div/li[18]/div/div[2]/span");
    private final By titleBusinessTabylga = By.xpath("//h2[text()='Бизнес-профили']");
    private final By counterPageBusinessTabylga = By.xpath("//p[contains(text(),'Всего бизнес-профилей')]");

    public MainPage(WebDriver driver) {
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

    public void clickBurgerMenuButton() {
        String stepName = "Нажатие кнопки 'Все' в меню";
        System.out.println(stepName);
        Allure.step(stepName, () -> {
            WebElement element = waitForClickable(burgerMenuButton);
            element.click();
        });
    }

    public void clickMenuBusinessTabylgaTab() {
        String stepName = "Нажатие таба 'Бизнесы на Tabylga' в выпадающем меню";
        System.out.println(stepName);
        Allure.step(stepName, () -> {
            WebElement element = waitForClickable(menuBusinessTabylgaTab);
            element.click();
        });
    }

    public String getCounterMenuBusinessTabylga() {
        String counterPageBusinessTabylgaText = waitForVisibility(counterMenuBusinessTabylgaTab).getText();
        System.out.println("Текст кнопки: " + counterPageBusinessTabylgaText);
        return counterPageBusinessTabylgaText;
    }

    // Получение заголовка страницы
    public String getTitleText() {
        String titleText = waitForVisibility(titleBusinessTabylga).getText();
        System.out.println("Заголовок страницы: " + titleText);
        return titleText;
    }

    // Получение текста-счетчика на странице
    public String getCounterPageBusinessTabylga() {
        String counterText = waitForVisibility(counterPageBusinessTabylga).getText();
        System.out.println("Текст счетчика на странице: " + counterText);
        return counterText;
    }
}