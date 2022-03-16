#Author: Harjinder Singh Banga
#Keywords Summary : Abu Dhabi
@abu
Feature: Verify the abuDhabi

  @abuDhabi
  Scenario: Verify Start and Manage business page
    Given launch the url
    When click on the category "Start & Manage a Business"
    Then verify "Start & Manage a Business" page is loaded
    When click on the "Step-by-Step Business Guide" from "Tool" Category
    Then verify "User guide" page is loaded
    And verify "Question No. 1" with all options and its content
    When Select "Get a Licience now option"
    And click on "Next" button
    Then verify "Question No. 2" with all options and its content
    And verify "Previous" answers
    Then click on "No, I want to open a branch for my existing business" option
    And click on "Next" button
    Then verify "Question No. 3" with all options and its content
    And verify "Previous" answers
    Then click on "Abu Dhabi" option
    And click on "Next" button
    Then verify "Login" page is loaded
