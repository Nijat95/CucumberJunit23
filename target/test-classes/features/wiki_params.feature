@RunWithParameters
Feature: Wikipedia search functionality

  @wiki1
  Scenario: Wikipedia search functionality
    Given user click on wikipedia page
    When User type "Amber Heard" in the wiki search box
    And User click search button
    Then User see "Amber Heard" in the wiki page title