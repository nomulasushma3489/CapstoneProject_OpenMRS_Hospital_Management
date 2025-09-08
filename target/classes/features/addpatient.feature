Feature: Register a patient
  Background: As an OpenMRS user I want to test login and register a patient So that the system works as expected

 Scenario: Positive Testcase- Registration of patient with all details
Given the user is logged into OpenMRS to register a patient
When the user clicks on Register a Patient
Then the user should be directed to "OpenMRS Electronic Medical Record" page 
And the user enters new patient details:
  | givenname  | David    |
  | familyname | Warner   |     
  | gender     | Male     |
  | years      | 23       |
  | months     |  10      |
  | address    |Mumbai    |
  |phonenumber |8467834593|
And the user clicks on submit button
Then the user should see "Patient ID" text 	

Scenario: Negative Testcase- Attempt to register patient with missing fields
	Given the user is logged into OpenMRS to register a patient
	When the user clicks on Register a Patient
	Then the user should be directed to "OpenMRS Electronic Medical Record" page 
	And the user enters few patient details:
      | givenname  |          |
      | familyname |  Doe     |
    And I should see a validation error for "Given Name"

  	