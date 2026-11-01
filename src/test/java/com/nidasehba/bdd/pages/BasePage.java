package com.nidasehba.bdd.pages;

import com.nidasehba.bdd.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/** Shared helpers: every interaction waits for the element instead of relying on fixed sleeps. */
public abstract class BasePage {

    protected final WebDriver driver = DriverFactory.driver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    protected void open(String path) {
        driver.get(DriverFactory.baseUrl() + path);
    }

    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement field = visible(locator);
        field.clear();
        field.sendKeys(text);
    }

    public String currentPath() {
        return driver.getCurrentUrl().replace(DriverFactory.baseUrl(), "");
    }
}
