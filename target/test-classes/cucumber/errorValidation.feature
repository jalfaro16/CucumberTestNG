
@tag
Feature: Error validation
  I want to use this template for my feature file

  
  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecomerce Page
    When Logged in with username <name> and <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
    | name 					     	  |	password   |	
		| rahulshetty@gmail.com | Iamng@000  |
