@tag
Feature: Place the Order in Ecommerce Application

  Background:
  Given Launch the Ecommerce Application

  @Functional
  Scenario Outline: Functional Testing of Placing an Order
    Given I want to Login with username <email> and password <password>
    When I added the product <productName> to the cart
    And Checkout <productName> and submit the Order
    Then "Thankyou for the order." message is displayed in the confirmation page

    Examples: 
      | email               | password    | productName     |
      | djaya0110@gmail.com | Dj@12345    | ADIDAS ORIGINAL |
      | shetty@gmail.com    | Iamking@000 | QWERTY          |
