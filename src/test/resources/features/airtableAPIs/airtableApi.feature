Feature: Airtable API Tests
@api
  Scenario: Verify Status code for get endpoint
    When user hit an GET endpoint
    Then user will receive status "200"
@RetrieveRecord
  Scenario: Verify Status code for a single record endpoint
    When user hit Retrieve Record
    Then user will receive status "200"
    And user verify the first name
@apiPostMethod
  Scenario: Verify Post EndPoint
  When a user creates a record
  Then user will receive status "200"
  When a user is saving the recordID
  And a user updates email
  Then user will receive status "200"
  When user deletes the recordID
  Then user will receive status "200"

  @apiNegativeTestExample
  Scenario: Create a request with a wrong request body
    When a user tries to create a record with invalid payload
    Then user will receive status code : 422
