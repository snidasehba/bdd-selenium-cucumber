package com.nidasehba.bdd.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final By USERNAME = By.id("username");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    private static final By FLASH = By.id("flash");
    private static final By LOGOUT = By.cssSelector("a[href='/logout']");
    private static final By SECURE_AREA_HEADING = By.cssSelector("h2");

    public void open() {
        open("/login");
    }

    public void login(String username, String password) {
        type(USERNAME, username);
        type(PASSWORD, password);
        click(LOGIN_BUTTON);
    }

    public void logout() {
        click(LOGOUT);
    }

    public String flashMessage() {
        return visible(FLASH).getText();
    }

    public String heading() {
        return visible(SECURE_AREA_HEADING).getText();
    }
}
