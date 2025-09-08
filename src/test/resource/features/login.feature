Feature: Login Functionality
  Background: As an OpenEMR user I want to test login functionality so that the system works as expected

Scenario: Positive - Login with valid credentials
	Given the user is on the OpenMRS login page
	When the user enters username "admin" password "Admin123" selects session "Registration Desk"
	And clicks on Login button
	Then the user should be directed to "Home" page
	

Scenario: Negative - Login with invalid credentials
	Given the user is on the OpenMRS login page
	When the user enters username "invaliduser" password "invalidpassword" selects session "Registration Desk"  
	And clicks on Login button
	Then the user should be directed to "Login" page
	And an error message "Invalid username/password. Please try again." should be displayed
	
