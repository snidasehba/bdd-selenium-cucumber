@regression
Feature: Common form controls

  Scenario: Selecting an option from a dropdown
    Given I open the dropdown page
    When I select "Option 2" from the dropdown
    Then "Option 2" should be the selected option

  Scenario: Toggling checkboxes
    Given I open the checkboxes page
    When I check checkbox 1
    And I uncheck checkbox 2
    Then checkbox 1 should be checked
    And checkbox 2 should be unchecked

  Scenario Outline: Handling JavaScript dialogs
    Given I open the JavaScript alerts page
    When I trigger the "<dialog>" and <action> it
    Then the result should read "<result>"

    Examples:
      | dialog     | action  | result                            |
      | JS Alert   | accept  | You successfully clicked an alert |
      | JS Confirm | accept  | You clicked: Ok                   |
      | JS Confirm | dismiss | You clicked: Cancel               |
