@wikiOutline
Feature: Wikipedia search functionality

  @wiki1
  Scenario Outline: Wikipedia search functionality
    Given user click on wikipedia page
    When User type "<name>" in the wiki search box
    And User click search button
    Then User see "<name>" in the wiki page title

#    alt+ctrl+l - to Align the Names below
    Examples:
    |name         |
#    |Ruby Tandoh  |
    |John Travolta|
    |Enzo Ferrari|