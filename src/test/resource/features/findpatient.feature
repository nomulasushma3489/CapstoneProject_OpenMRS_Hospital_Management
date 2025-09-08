Feature: Find a patient record
  Background: As an OpenMRS user I want to test login and find a patient record So that the system works as expected

Scenario: Positive Case - Finding a valid patient record
	Given the user is logged into OpenMRS to find a patient record
	When the user clicks on Find Patient Record
	Then the user should be directed to "OpenMRS Electronic Medical Record" page
	When  the user enters patient name and click on details
	|patientrecord| David Watson |
	Then the user should see a record with patient name

	
Scenario: Negative Case - Attempt to find a invalid patient record
	Given the user is logged into OpenMRS to find a patient record
	When the user clicks on Find Patient Record
	Then the user should be directed to "OpenMRS Electronic Medical Record" page
	When  the user enters patient name and click on details
	|patientrecord| johns willath |
	Then the user should see "No matching records found"
	