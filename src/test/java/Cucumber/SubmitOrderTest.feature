
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page 

  @tag2
  Scenario Outline: Positive Test of Submitting the order
    Given I logged in with username <name> and password <password>
    When I add the product <productName> to cart
    And checkout <productName> and submit order
    Then "THANK YOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  	  				| password 			|	productName |
      |dsolatre@yahoo.com | Dsolatre16		| ZARA COAT 3 |
