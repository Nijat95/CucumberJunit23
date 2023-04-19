@wikiAllTests
Feature: Wikipedia search functionality

  @wiki1
  Scenario: Wikipedia search functionality
    Given user click on wikipedia page
    When User type Steve Jobs in the wiki search box
    And User click search button
    Then User see Steve Jobs in the wiki page title

  @wiki2
  Scenario: Wikipedia Search Functionality page header
    Given user click on wikipedia page
    When User type Steve Jobs in the wiki search box
    And User click search button
    Then User see Steve Jobs in the main header

  @wiki3
  Scenario: Wikipedia Search Functionality image header
    Given user click on wikipedia page
    When User type Steve Jobs in the wiki search box
    And User click search button
    Then User see Steve Jobs in the image header