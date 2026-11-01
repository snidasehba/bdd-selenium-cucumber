package com.nidasehba.bdd.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/** Dropdown, checkbox and JavaScript-dialog pages of the practice site. */
public class FormControlsPage extends BasePage {

    private static final By DROPDOWN = By.id("dropdown");
    private static final By CHECKBOXES = By.cssSelector("#checkboxes input[type='checkbox']");
    private static final By RESULT = By.id("result");

    public void openDropdownPage() {
        open("/dropdown");
    }

    public void openCheckboxesPage() {
        open("/checkboxes");
    }

    public void openAlertsPage() {
        open("/javascript_alerts");
    }

    public void selectOption(String option) {
        new Select(visible(DROPDOWN)).selectByVisibleText(option);
    }

    public String selectedOption() {
        return new Select(visible(DROPDOWN)).getFirstSelectedOption().getText();
    }

    public void setCheckbox(int index, boolean checked) {
        WebElement box = checkbox(index);
        if (box.isSelected() != checked) {
            box.click();
        }
    }

    public boolean isChecked(int index) {
        return checkbox(index).isSelected();
    }

    public void triggerDialog(String dialog, boolean accept) {
        click(By.xpath("//button[text()='Click for " + dialog + "']"));
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }

    public String result() {
        return visible(RESULT).getText();
    }

    private WebElement checkbox(int oneBasedIndex) {
        visible(CHECKBOXES);
        List<WebElement> boxes = driver.findElements(CHECKBOXES);
        return boxes.get(oneBasedIndex - 1);
    }
}
