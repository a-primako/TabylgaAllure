package com.v2modules.tabylga.ui.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Singleton {
    private static WebDriver driver;

    // Закрытый конструктор для предотвращения создания экземпляра
    private Singleton() {}

    // Синхронный метод получение драйвера
    public static synchronized WebDriver getDriver() {
        if (driver == null) {
            setupChromeDriver();
            configureDriver();
        }
        return driver;
    }

    // Настройка ChromeDriver через WebDriverManager
    private static void setupChromeDriver() {
        // Используем WebDriverManager для загрузки последней подходящей версии ChromeDriver.
        // Если необходима конкретная версия, можно указать через:
        // WebDriverManager.chromedriver().driverVersion("135.0.7049.114").setup();
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--start-maximized",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*"
        );

        // Если требуется запуск в headless-режиме, раскомментируйте следующую строку:
        // options.addArguments("--headless=new");

        driver = new ChromeDriver(options);
    }

    // Настройка таймаутов драйвера
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
