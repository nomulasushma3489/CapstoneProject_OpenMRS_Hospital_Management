Feature: Register a patient
  Background: As an OpenMRS user I want to test login and register a patient So that the system works as expected

Scenario: Positive - Creation of service type
	Given the user is logged into OpenMRS to add service type
	When the user clicks on Appointment Scheduling
	Then the user should be directed to "OpenMRS Electronic Medical Record" page 
	When the user clicks on Manage Service Types
	And clicks on new service type
	And enters details:
	| name     | Pulmunology |
	| duration |  10           |
	And clicks on save button
	Then the user should be directed to "Manage Service Types"


Scenario: Negative - creation of duplicate service type
	Given the user is logged into OpenMRS to add service type
	When the user clicks on Appointment Scheduling
	Then the user should be directed to "OpenMRS Electronic Medical Record" page 
	When the user clicks on Manage Service Types
	And clicks on new service type
	And enters details:
	| name     | Pulmunology |
	| duration |  10           |
	And clicks on save button
	Then user should see a message "Name is duplicated"


