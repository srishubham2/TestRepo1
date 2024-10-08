# Test Automation Repository to automate 1 GUI and 1 API Scenarios
This repository contains automated test scenarios developed using Selenium and RestAssured to test web and API functionalities. The goal is to provide robust and scalable test cases to ensure proper functionality of web elements and API resources -

**Goals : **
1.	Automate any web login flow using Selenium
2.	Automate any POST API


## Test Scenarios
### Scenario 1: Wikipedia Login Test - UI Automation
This scenario tests the login functionality on the Wikipedia website. It automates the following steps:

Navigating to the Wikipedia login page.
Entering a valid username and password.
Verifying that the login is successful or fails with appropriate error handling.

* Note : Please check the `LoginTest.java` to check the action steps and assertion of UI Test Case.


### Scenario 2: Post API Resource Creation and Verification = API Automation
This scenario tests the creation of a resource using a POST API request. It covers the following steps:

Sending a POST request to create a new resource : User.
Verifying the response status code, headers, and body.
Ensuring the response data (e.g., name, job) matches the expected values.

* Note : Please check the `DemoAPITest.java` to check the action steps and assertion of API Test Case.


### Prerequisites
Before running the tests, ensure the following are installed:

* Java (JDK 8 or higher)
* Maven
* IDE (Eg: IntelliJ / Eclipse)


Thanks, 

**Shubham Srivastav**
