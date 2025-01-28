
@tag
Feature: Purchase the order from Ecomerce website
I want to use this template for my future file


Background:
Given I landed on Ecomerce Page

@Regression
Scenario Outline: Positive Test of Submitting the order

	Given Logged in with username <name> and <password>
	When I add a <productName> to Cart
	And Checkout <productName> and submit the order
	Then "THANKYOU FOR THE ORDER." mesagge is displayed on conf page
	
	Examples:
	| name 						      |	password    |	 productName |
	| rahulshetty@gmail.com | Iamking@000 |  IPHONE 13 PRO  |
