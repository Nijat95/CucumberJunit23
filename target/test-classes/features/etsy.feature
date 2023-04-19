Feature: Verify search box
  @etsy
  Scenario: Verify search box
    Given I am on etsy homepage
    When I search for book
    And I click on search box
    Then I see the title contains book