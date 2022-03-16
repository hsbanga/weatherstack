# Author: Harjinder Singh Banga
@WeatherStack
Feature: Verify Weather Stack API

  @Smoke @Positive
  Scenario: Verify the weather stack API is responding ok
  
 
    Given add "CORRECT" "access_key" in "QUERY"
    * add "London" as "query" in "QUERY"
    When user calls "WEATHERSTACK" "BaseURI" with "GET" http request
    Then the API call got success with status code 200
    * verify "WEATHERSTACK" response key "request.language" and value "en"
    * verify "WEATHERSTACK" response key "request.type" and value "City"
    * verify "WEATHERSTACK" response key "location.name" and value "London"
   
   @Smoke @Positive
  Scenario: Verify the weather stack API is responding ok
  
 
    Given add "CORRECT" "access_key" in "QUERY"
    * add "Texas" as "query" in "QUERY"
    When user calls "WEATHERSTACK" "BaseURI" with "GET" http request
    Then the API call got success with status code 200
    * verify "WEATHERSTACK" response key "request.language" and value "en"
    * verify "WEATHERSTACK" response key "request.type" and value "City"
    * verify "WEATHERSTACK" response key "location.region" and value "Texas"
    
    
    @Smoke @Negative
  Scenario: Verify the weather stack API is responding with error message on incorrect access key
  
   
    Given add "incorrect" "access_key" in "QUERY"
    * add "London" as "query" in "QUERY"
    When user calls "WEATHERSTACK" "BaseURI" with "GET" http request
    Then the API call got success with status code 200
		And "error.code" is "101" in response
    And "error.type" is "invalid_access_key" in response
    And "error.info" is "You have not supplied a valid API Access Key. [Technical Support: support@apilayer.com]" in response
   
   
    @Smoke @Negative
  Scenario: Verify the weather stack API is responding with error message on incorrect access key
  
   
    Given add "null" "access_key" in "QUERY"
    * add "London" as "query" in "QUERY"
    When user calls "WEATHERSTACK" "BaseURI" with "GET" http request
    Then the API call got success with status code 200
		And "error.code" is "101" in response
    And "error.type" is "missing_access_key" in response
    And "error.info" is "You have not supplied an API Access Key. [Required format: access_key=YOUR_ACCESS_KEY]" in response
    
    @Smoke @Negative
  Scenario: Verify the weather stack API is responding with error message on incorrect access key
  
   
    Given add "correct" "access_key" in "QUERY"
    * add "" as "query" in "QUERY"
    When user calls "WEATHERSTACK" "BaseURI" with "GET" http request
    Then the API call got success with status code 200
		And "error.code" is "601" in response
    And "error.type" is "missing_query" in response
    And "error.info" is "Please specify a valid location identifier using the query parameter." in response
    
    