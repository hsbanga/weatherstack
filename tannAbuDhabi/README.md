Cucucmber BDD UI Automation Framework
=====================================

This framwork has been design with help of TestNG, Cucumber BDD tool with page object model design for web based applications.

Prerequisites
-------------
```
Good Level of Understanding of Cucumber and Java and little bit of TestNG as well.
System Requiriment: Java8, Maven and eclipse should be installed.
Eclipse Requiriment:TestNG and Cucucmber plugin must be installed from eclipse Marketplace.
```

Understand the Project structure
--------------------------------
```
Java Directory(src\test\java)- Contains all the Java class files.
Resources Directory(src\test\resources)- Contains non-java(other) files.
Json Report - inside 'target' folder, generats post execution.
XML report - inside target\testng-cucumber-reports folder, generats post execution.
Masterthought's HTML cucumber Report - inside 'ProjectReports' folder, generats post execution.
```

CLA runtime Parameters
----------------------
```
BROWSER - default: chrome
TEST_ENV - default:dev
IMPLICIT_WAIT - default:0
TAKE_ALL_SCREENSHOTS - default:false

Maven Build command Example: "mvn verify -DBROWSER=chrome -DTEST_ENV=stag"
```