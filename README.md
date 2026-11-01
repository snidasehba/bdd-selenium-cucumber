# BDD UI Tests with Selenium and Cucumber

Behaviour-driven UI tests written in **Gherkin** and automated with **Java 17**, **Selenium 4**,
**Cucumber** and **TestNG**. Scenarios run in parallel, locally in Chrome or in **GitLab CI** against a
Selenium standalone Chrome container.

The application under test is [The Internet](https://the-internet.herokuapp.com), a public practice
site for UI automation.

## Scenarios

| Feature | What it checks |
|---|---|
| `login.feature` | Successful login and logout; invalid username, invalid password and empty username (Scenario Outline) |
| `dynamic_content.feature` | Elements that appear or are added after a delay, handled with explicit waits |
| `form_controls.feature` | Dropdown selection, checkboxes, JavaScript alert and confirm dialogs (accept and dismiss) |

Tags: `@smoke` for the quick subset, `@regression` for everything.

## Structure

```
src/test/resources/features/        Gherkin feature files (the living documentation)
src/test/java/com/nidasehba/bdd/
  driver/DriverFactory.java         local Chrome or remote Selenium (SELENIUM_REMOTE_URL), one driver per thread
  pages/                            page objects; BasePage wraps explicit waits
  steps/                            step definitions + Hooks (screenshot on failure, driver lifecycle)
  runner/CucumberRunner.java        TestNG runner, scenarios in parallel
.gitlab-ci.yml                      Maven job with a selenium/standalone-chrome service
```

## Run it

```bash
mvn test                                         # all scenarios, headless Chrome
mvn test -Dcucumber.filter.tags="@smoke"         # smoke only
mvn test -Dheadless=false                        # watch the browser
```

The HTML report is written to `target/cucumber-report.html`, with a screenshot attached to any failed scenario.

## Design choices

- **Business-readable scenarios**: the feature files describe behaviour; Selenium details stay in page objects.
- **No `Thread.sleep`**: every interaction goes through `WebDriverWait`, which keeps the suite fast and stable.
- **Thread-safe driver**: a `ThreadLocal` driver per scenario makes parallel execution safe.
- **Same code locally and in CI**: setting `SELENIUM_REMOTE_URL` switches from local Chrome to a remote grid.
