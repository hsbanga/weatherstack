# Hosted Plugin Readme

## Cucumber BDD API Automation Framework using Restassured

The hosted plugin automation is used to system test the Bigcommerce, Shopify and Perfect Mind Loginradius Cloud APIs, so this automation includes hosted plugin APIs, core APIs and plugin APIs to verify the end to end testing.

This framework has been designed with help of TestNG, Cucumber BDD, Rest Assured tool for API Automation with page object model for UI purposes.

## Prerequisites

Good Level of Understanding of Cucumber and Java and a little bit of TestNG as well.

System Requirement: Java8, Maven and eclipse should be installed.

Eclipse Requirement:TestNG and Cucumber plugin must be installed from eclipse Marketplace.

## Understand the Project structure

Java Directory(src\test\java)- Contains all the Java class files.

Java Directory(src\main\java)- Contains all the POJO class files.

Resources Directory(src\test\resources)- Contains non-java(other) files.

Json Report - inside &#39;target&#39; folder, generate post execution.

XML report - inside target\testng-cucumber-reports folder, generate post execution.

Masterthought&#39;s HTML cucumber Report - inside &#39;ProjectReports&#39; folder, generate post execution.

## Unique Feature Files Steps

Let&#39;s consider a test scenario:-

@Shopify @Getshopifymultipasstoken

Feature: Shopify Login

######################### BASIC TEST CASES ##########################

######################### POSITIVE CASES ############################

@Basic @Positive @HighPriority @Smoke @Sanity 

Scenario: PQ-TC-15504\_Verify the user is able to login in shopify with login url generated in api response

when user doesn&#39;t exists in shopify

Given add &quot;CORRECT&quot; &quot;APIKEY&quot; in &quot;QUERY&quot;

\* add &quot;CORRECT&quot; &quot;APISECRET&quot; in &quot;QUERY&quot;

\* add &quot;CORRECT&quot; &quot;FIRSTNAME&quot; in &quot;BODY&quot;

\* add &quot;CORRECT&quot; &quot;LASTNAME&quot; in &quot;BODY&quot;

\* add &quot;CORRECT&quot; &quot;EMAIL\_TYPE&quot; in &quot;BODY&quot;

\* add &quot;CORRECT&quot; &quot;VALUE&quot; in &quot;BODY&quot;

\* add &quot;CORRECT&quot; &quot;PASSWORD&quot; in &quot;BODY&quot;

\* set the payload for POSTCREATEACCOUNT API

When user calls &quot;POSTCREATEACCOUNT&quot; &quot;COREAPI&quot; with &quot;POST&quot; http request

Then the API call got success with status code 200

\* get &quot;Uid&quot; of the user

Given add &quot;CORRECT&quot; &quot;APIKEY&quot; in &quot;QUERY&quot;

\* add &quot;CORRECT&quot; &quot;APISECRET&quot; in &quot;QUERY&quot;

\* add &quot;CORRECT&quot; &quot;Uid&quot; in &quot;QUERY&quot;

When user calls &quot;GETACCESSTOKEN&quot; &quot;COREAPI&quot; with &quot;GET&quot; http request

Then the API call got success with status code 200

\* get &quot;access\_token&quot; of the user

Given add &quot;CORRECT&quot; &quot;APIKEY&quot; in &quot;QUERY&quot;

\* add &quot;CORRECT&quot; &quot;ACCESS\_TOKEN&quot; in &quot;QUERY&quot;

\* add &quot;CORRECT&quot; &quot;STORE&quot; in &quot;QUERY&quot;

When user calls &quot;GETSHOPIFYMULTIPASSTOKEN&quot; &quot;CLOUDAPI&quot; with &quot;GET&quot; http request

Then the API call got success with status code 200

And &quot;url&quot; in response body is a valid url format

And user will be logged in to Shopify

And close the browser

Given add &quot;CORRECT&quot; &quot;query&quot; in &quot;QUERY&quot;

When user calls &quot;GETCONTACTDETAILS&quot; &quot;SHOPIFYAPI&quot; with &quot;GET&quot; http request

Then the API call got success with status code 200

And verify if &quot;POSTCREATEACCOUNT&quot; &quot;LastName&quot; is equal to &quot;GETCONTACTDETAILS&quot; &quot;customers[0].last\_name&quot;

In the above example we are verifying the shopify multipass API with correct query parameters, so let&#39;s see what all steps we need to write.

1. Firstly, we need to call the Core api known as Create Account API.

1. To call the api first we need to set the parameters, hence we are setting the correct query parameters in the below lines -

Given add &quot;CORRECT&quot; &quot;APIKEY&quot; in &quot;QUERY&quot;

\* add &quot;CORRECT&quot; &quot;APISECRET&quot; in &quot;QUERY&quot;

\* add &quot;CORRECT&quot; &quot;FIRSTNAME&quot; in &quot;BODY&quot;

\* add &quot;CORRECT&quot; &quot;LASTNAME&quot; in &quot;BODY&quot;

\* add &quot;CORRECT&quot; &quot;EMAIL\_TYPE&quot; in &quot;BODY&quot;

\* add &quot;CORRECT&quot; &quot;VALUE&quot; in &quot;BODY&quot;

\* add &quot;CORRECT&quot; &quot;PASSWORD&quot; in &quot;BODY&quot;

  1. **First parameter** can accept values as described below -
    1. **Correct** - The correct is used to send the correct value of parameter like correct API Key
    2. **Incorrect** - The incorrect is used to send the incorrect value of parameter, the format of parameter value is the same it&#39;s just the we replace a few characters from correct value and replace with incorrect value like API Key is of 32 characters so we just replace few characters with random characters.
    3. **Invalid** - The invalid is used to send the invalid value of parameter, like API key is 32 characters long but instead of that we send a random number of random characters.
    4. **Null** - As the name mentioned we send null as the value of parameter.
    5. **Expired** - This parameter is specifically for the access token as for some scenarios we require the access token to be expired.
  2. **Second parameter** can accept the name of the parameter like APIKEY.
  3. **Third parameter** can accept the location of the parameter like Query, Header, Body, Path.

1. \* set the payload for POSTCREATEACCOUNT API

This line will be used to set the payload of the API specifically for the POST APIs . This line is specific to API means it does not accept the parameter.

1. When user calls &quot;POSTCREATEACCOUNT&quot; &quot;COREAPI&quot; with &quot;POST&quot; http request

This line accepts 3 parameters

  1. API Name - The name which is mentioned in APIResources.java file
  2. API Type - The type of API we are calling, parameters as mentioned below
    1. CloudAPI
    2. ShopifyAPI
    3. BigCommerceAPI
    4. CoreApiDomainURL

  1. API Method Type - This parameter accepts API method Types like Get, Post etc…

1. Then the API call got success with status code 200

The line is used to verify the status code of the API response

1. \* get &quot;Uid&quot; of the user

This line is used to get the value of any variable from the response of the API called just above this line. Example - UID, Access Token etc.

1. And &quot;url&quot; in response body is a valid url format

This line is used to verify that the URL in response is in valid format

1. And user will be logged in to Shopify

This line is used to use the URL from the response and call the URL in the browser

1. And close the browser

This line is used to close any open browser.

1. And verify if &quot;POSTCREATEACCOUNT&quot; &quot;LastName&quot; is equal to &quot;GETCONTACTDETAILS&quot; &quot;customers[0].last\_name&quot;

This line is used for assertion where it verifies whether the response of our Cloud API is equal to the response from the plugin API to make sure that the data which we send from our API is received correctly by the plugin.

So this line has 4 parameters,

1. First is the **API name** from where the data is generated or sent to plugin.
2. Second is **Key name** which we need to verify
3. Third is **API Name of Plugin** and
4. Fourth is the **Key name** with whom we want to mach the data

Note: Key Name is case sensitive and you need to pass the key exactly the same way it has been received in the API response)

## Rules of Writing Feature File

1. The parameter name should be in capital letter like APIKEY
2. The API names should be in capital letters like GETSHOPIFYMULTIPASSTOKEN
3. The type of API should be in capital letters like CLOUDAPI
4. The method type of API should be in capital letters like GET
5. The feature file must be divided by the features of API as below -

########## BASIC TEST CASES #############

1. Then the feature file should also be divided by the type of scenario like Negative or positive as below -

########### POSITIVE CASES ##########

## Standards Followed

1. Feature File Name (eg:- getShopifyMultipassTokenAPI)
  1. The feature file name should be in camel case, but first letter should be small
  2. The name should start with the method type(post, get etc...)
  3. Then followed with plugin name like Shopify
  4. Then followed with API name and then keyword &quot;API&quot;
2. Java Class Name - The class name should be in camel case

## Tags Used in Feature Files

1. @Shopify - This tag is used to run all shopify test scenarios.
2. @BigCommerce - This tag is used to run all BigCommerce test scenarios.
3. @PerfectMind - This tag is used to run all PerfectMind test scenarios.
4. @BigCommerceBasic - This tag is used to run BigCommerce Basic test scenarios.
5. @PerfectMindBasic - This tag is used to run PerfectMind Basic test scenarios.
6. @ShopifyBasic - This tag is used to run Shopify Basic test scenarios.
7. @ShopifyQueryHeader - This tag is used to run Shopify Query Header test scenarios.
8. @BigCommerceQueryHeader - This tag is used to run BigCommerce Query Header test scenarios.
9. @PerfectMindQueryHeader - This tag is used to run PerfectMind Query Header test scenarios.
10. @Positive - This tag is used to run all the Positive test scenarios.
11. @Negative - This tag is used to run all the Negative test scenarios.
12. @HighPriority - This tag is used to run all the HighPriority test scenarios.
13. @LowPriority - This tag is used to run all the LowPriority test scenarios.
14. @MediumPriority - This tag is used to run all the MediumPriority test scenarios.
15. @Smoke - This tag is used to run Smoke test scenarios.
16. @Sanity - This tag is used to run Sanity test scenarios.

## CLA runtime Parameters

Environment - env (prod, stag, dev)

Plugin - plugin(shopify, bigcommerce, perfectmind)

Maven Build command Example: &quot;mvn clean test verify -Denv=prod -Dplugin=shopify -Dcucumber.filter.tags=&quot;@Shopify&quot;&quot;