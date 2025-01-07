@tag
Feature: Place the Order in Ecommerce Application

  @ErrorsValidation
  Scenario Outline: Error Validation of Login Page
    Given Launch the Ecommerce Application
    When I want to Login with username <email> and password <password>
    Then Error Message "Incorrect email or password." is displayed

    Examples: 
      | email               | password    | productName     |
      | djaya0110@gmail.com | DJ@12345    | ADIDAS ORIGINAL |
