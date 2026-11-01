package com.nidasehba.bdd.steps;

import com.nidasehba.bdd.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void startBrowser() {
        DriverFactory.start();
    }

    /** Failed scenarios get a screenshot embedded in the Cucumber HTML report. */
    @After
    public void stopBrowser(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] png = ((TakesScreenshot) DriverFactory.driver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(png, "image/png", scenario.getName());
            }
        } finally {
            DriverFactory.quit();
        }
    }
}
