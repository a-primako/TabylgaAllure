package com.v2modules.tabylga.ui.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Singleton {
    private static WebDriver driver;

    private Singleton() {
    }

    public static synchronized WebDriver getDriver() {
        if (driver == null) {
            try {
                setupChromeDriver();
                configureDriver();
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize WebDriver", e);
            }
        }
        return driver;
    }

    private static void setupChromeDriver() {
        // Указываем путь к ChromeDriver вручную
        System.setProperty("webdriver.chrome.driver", "C:\\jenkins\\webdriver_cache\\chromedriver\\win64\\135.0.7049.84\\chromedriver.exe");

        WebDriverManager.chromedriver()
                .forceDownload()
                .avoidBrowserDetection()
                .setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--start-maximized",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*"
        );

        driver = new ChromeDriver(options);
    }

    private static void configureDriver() {
        if (driver != null) {
            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(30))
                    .pageLoadTimeout(Duration.ofSeconds(60));
        }
    }

    public static synchronized void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
