package com.nidasehba.bdd.driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * One WebDriver per scenario thread. Runs Chrome locally (Selenium Manager fetches the driver), or on a
 * Selenium Grid / standalone container when SELENIUM_REMOTE_URL is set, as in CI.
 */
public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver driver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not started; is the Hooks class on the glue path?");
        }
        return driver;
    }

    public static void start() {
        ChromeOptions options = new ChromeOptions();
        if (!"false".equals(System.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1366,900");
        // Return once the DOM is ready instead of waiting for slow third-party assets; waits handle the rest.
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        String remote = System.getenv("SELENIUM_REMOTE_URL");
        WebDriver driver;
        if (remote != null && !remote.isBlank()) {
            try {
                driver = new RemoteWebDriver(new URL(remote), options);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Bad SELENIUM_REMOTE_URL: " + remote, e);
            }
        } else {
            driver = new ChromeDriver(options);
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        DRIVER.set(driver);
    }

    public static void quit() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

    public static String baseUrl() {
        return System.getProperty("baseUrl", "https://the-internet.herokuapp.com");
    }
}
