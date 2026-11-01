package com.nidasehba.bdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicLoadingPage extends BasePage {

    private static final By START = By.cssSelector("#start button");
    private static final By FINISH_TEXT = By.cssSelector("#finish h4");

    /** Example 1 hides the element until loading ends; example 2 adds it to the DOM afterwards. */
    public void open(int example) {
        open("/dynamic_loading/" + example);
    }

    public void start() {
        click(START);
    }

    public String waitForFinishText(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(FINISH_TEXT))
                .getText();
    }
}
