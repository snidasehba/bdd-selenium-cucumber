@regression
Feature: Content that appears after a delay
  Elements that load asynchronously must be handled with explicit waits, not fixed sleeps.

  @smoke
  Scenario: Hidden element becomes visible after loading
    Given I open the dynamic loading example with a hidden element
    When I start the loading
    Then the text "Hello World!" should appear within 10 seconds

  Scenario: Element is added to the page after loading
    Given I open the dynamic loading example with a rendered-later element
    When I start the loading
    Then the text "Hello World!" should appear within 10 seconds
