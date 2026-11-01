package com.nidasehba.bdd.steps;

import com.nidasehba.bdd.pages.FormControlsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FormControlSteps {

    private FormControlsPage formPage;

    private FormControlsPage page() {
        if (formPage == null) {
            formPage = new FormControlsPage();
        }
        return formPage;
    }

    @Given("I open the dropdown page")
    public void openDropdownPage() {
        page().openDropdownPage();
    }

    @Given("I open the checkboxes page")
    public void openCheckboxesPage() {
        page().openCheckboxesPage();
    }

    @Given("I open the JavaScript alerts page")
    public void openAlertsPage() {
        page().openAlertsPage();
    }

    @When("I select {string} from the dropdown")
    public void selectFromDropdown(String option) {
        page().selectOption(option);
    }

    @Then("{string} should be the selected option")
    public void shouldBeSelected(String option) {
        assertEquals(page().selectedOption(), option);
    }

    @When("I check checkbox {int}")
    public void checkCheckbox(int index) {
        page().setCheckbox(index, true);
    }

    @When("I uncheck checkbox {int}")
    public void uncheckCheckbox(int index) {
        page().setCheckbox(index, false);
    }

    @Then("checkbox {int} should be checked")
    public void checkboxShouldBeChecked(int index) {
        assertTrue(page().isChecked(index));
    }

    @Then("checkbox {int} should be unchecked")
    public void checkboxShouldBeUnchecked(int index) {
        assertFalse(page().isChecked(index));
    }

    @When("I trigger the {string} and {word} it")
    public void triggerDialog(String dialog, String action) {
        page().triggerDialog(dialog, "accept".equals(action));
    }

    @Then("the result should read {string}")
    public void resultShouldRead(String expected) {
        assertEquals(page().result(), expected);
    }
}
