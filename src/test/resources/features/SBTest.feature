
Feature: Verify smart bear orders page
  @smartBearTest
  Scenario Outline: User should be able to place order with different data sets
    Given User is logged into SmartBear Tester account and on Order page
    And User selects "<product>" from product dropdown
    And User enters "<quantity>" to quantity input box
    And User enters "<customerName>" to costumer name input box
    And User enters "<streetName>" to street name input box
    And User enters "<city>" to city input box
    And User enters "<state>" to state input box
    And User enters "<zip>" to zip input box
    And User selects "<cardType>" as card type from radio buttons
    And User enters "<cardNumber>" to card number input box
    And User enters "<expirationDate>" to expiration date into input box
    And User clicks process button
    Then User verifies "<expectedName>" is in the table


    Examples:
      | product     | quantity | customerName | streetName    | city     | state | zip   | cardType | cardNumber       | expirationDate | expectedName |
      |ham          | 2        |Nijat         |123 Addresss st|Chicago   |IL     |71123  |Visa      |1234565789        |09/13/27        |Nijat         |