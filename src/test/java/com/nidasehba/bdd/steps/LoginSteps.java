package com.nidasehba.bdd.steps;

import com.nidasehba.bdd.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginSteps {

    private LoginPage loginPage;

    private LoginPage page() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        page().open();
    }

    @When("I log in as {string} with password {string}")
    public void iLogInAs(String username, String password) {
        page().login(username, password);
    }

    @When("I log out")
    public void iLogOut() {
        page().logout();
    }

    @Then("I should see the message {string}")
    public void iShouldSeeTheMessage(String message) {
        String flash = page().flashMessage();
        assertTrue(flash.contains(message), "Expected flash to contain '" + message + "' but was: " + flash);
    }

    @Then("I should be on the secure area page")
    public void iShouldBeOnTheSecureAreaPage() {
        assertEquals(page().currentPath(), "/secure");
        assertEquals(page().heading(), "Secure Area");
    }

    @Then("I should still be on the login page")
    public void iShouldStillBeOnTheLoginPage() {
        assertEquals(page().currentPath(), "/login");
    }
}
