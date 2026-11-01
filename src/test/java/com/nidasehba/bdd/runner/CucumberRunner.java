package com.nidasehba.bdd.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Runs every feature file through TestNG, scenarios in parallel (thread count set in the pom).
 * Filter by tag with -Dcucumber.filter.tags="@smoke".
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.nidasehba.bdd.steps",
        plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"}
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
