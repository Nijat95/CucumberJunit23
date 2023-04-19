@smoke @UI
Feature: Verify user can open google page
  This is testing google page
  # comments
  # Given, When, And, Then
  @TX-859
  Scenario: Google Title verification
    Given I am at google home page
    Then I verify the title is Google
    And I verify the search
  @searchVerification
  Scenario: Google search test
    Given I am at google home page
    When I search for saucelabs
    Then I verify the title is Saucelabs

