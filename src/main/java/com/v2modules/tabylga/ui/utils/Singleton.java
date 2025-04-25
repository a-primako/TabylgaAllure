package com.v2modules.tabylga.ui.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Singleton {
    private static WebDriver driver;

    // Закрытый конструктор для предотвращения создания экземпляров
    private Singleton() {}

    // Синхронный метод получения драйвера
    public static synchronized WebDriver getDriver() {
        if (driver == null) {
            setupChromeDriver();
            configureDriver();
        }
        return driver;
    }

    private static void setupChromeDriver() {
        // Автоматическая установка нужной версии ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Настройка опций для Chrome
        ChromeOptions options = new ChromeOptions();
        // Режим без окна браузера (headless)
        options.addArguments("--headless=new");
        // Другие параметры для стабильной работы, особенно в CI
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    private static void configureDriver() {
        if (driver != null) {
            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(30))
                    .pageLoadTimeout(Duration.ofSeconds(60));
        }
    }

    // Синхронный метод закрытия драйвера
    public static synchronized void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
