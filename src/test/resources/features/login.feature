@regression
Feature: Login to the secure area
  As a registered user
  I want to log in with my credentials
  So that I can reach the secure area

  Background:
    Given I am on the login page

  @smoke
  Scenario: Successful login and logout
    When I log in as "tomsmith" with password "SuperSecretPassword!"
    Then I should see the message "You logged into a secure area!"
    And I should be on the secure area page
    When I log out
    Then I should see the message "You logged out of the secure area!"

  Scenario Outline: Login is rejected with invalid credentials
    When I log in as "<username>" with password "<password>"
    Then I should see the message "<message>"
    And I should still be on the login page

    Examples:
      | username  | password             | message                   |
      | wronguser | SuperSecretPassword! | Your username is invalid! |
      | tomsmith  | wrongpassword        | Your password is invalid! |
      |           | SuperSecretPassword! | Your username is invalid! |
