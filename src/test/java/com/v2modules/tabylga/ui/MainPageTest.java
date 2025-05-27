package com.v2modules.tabylga.ui;

import com.v2modules.tabylga.ui.pages.MainPage;
import com.v2modules.tabylga.ui.utils.Singleton;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI Тесты")
public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = Singleton.getDriver();
        String url = "https://tabylga.app/";
        driver.get(url);
        mainPage = new MainPage(driver);
        String info = "Открытый URL: " + driver.getCurrentUrl();
        System.out.println(info);
        Allure.addAttachment("Открытый URL", "text/plain", info);
    }

    @Test
    @DisplayName("Проверка счетчика значений категории 'Бизнес на Tabylga'")
    @Description("Покрывает задачу - https://app.clickup.com/t/86duzkpfy")
    public void testBusinessTabCounts() {
        mainPage.clickBurgerMenuButton();

        String menuCounterText = mainPage.getCounterMenuBusinessTabylga();
        int menuCounter = Integer.parseInt(menuCounterText.replaceAll("[^0-9]", ""));
        Allure.step("Значение счетчика в меню: " + menuCounter);
        Assertions.assertTrue(menuCounter > 0, "Счетчик в меню должен быть больше 0");

        mainPage.clickMenuBusinessTabylgaTab();

        String pageTitle = mainPage.getTitleText();
        Allure.step("Заголовок страницы: " + pageTitle);
        Assertions.assertEquals("Бизнес-профили", pageTitle, "Неверный заголовок страницы");

        String pageCounterText = mainPage.getCounterPageBusinessTabylga();
        int pageCounter = Integer.parseInt(pageCounterText.replaceAll("[^0-9]", ""));
        Allure.step("Значение счетчика на странице: " + pageCounter);
        Assertions.assertTrue(pageCounter > 0, "Счетчик на странице должен быть больше 0");

        // 5. Сверяем, что значения счетчиков равны
        System.out.println("Счетчик профилей в меню: " + menuCounter);
        System.out.println("Счетчик профилей на странице: " + pageCounter);
        Allure.addAttachment("Счетчик профилей в меню", "text/plain", String.valueOf(menuCounter));
        Allure.addAttachment("Счетчик профилей на странице", "text/plain", String.valueOf(pageCounter));

        if (menuCounter == pageCounter) {
            System.out.println("Значения счетчиков совпадают.");
            Allure.addAttachment("Результат проверки", "text/plain", "Значения счетчиков совпадают.");
        }

        Assertions.assertEquals(menuCounter, pageCounter, "Счетчик в выпадающем меню и на странице не совпадают");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
