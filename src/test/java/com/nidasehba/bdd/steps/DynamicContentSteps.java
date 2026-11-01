package com.nidasehba.bdd.steps;

import com.nidasehba.bdd.pages.DynamicLoadingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;

public class DynamicContentSteps {

    private DynamicLoadingPage dynamicPage;

    private DynamicLoadingPage page() {
        if (dynamicPage == null) {
            dynamicPage = new DynamicLoadingPage();
        }
        return dynamicPage;
    }

    @Given("I open the dynamic loading example with a hidden element")
    public void openHiddenElementExample() {
        page().open(1);
    }

    @Given("I open the dynamic loading example with a rendered-later element")
    public void openRenderedLaterExample() {
        page().open(2);
    }

    @When("I start the loading")
    public void iStartTheLoading() {
        page().start();
    }

    @Then("the text {string} should appear within {int} seconds")
    public void theTextShouldAppear(String text, int seconds) {
        assertEquals(page().waitForFinishText(seconds), text);
    }
}
