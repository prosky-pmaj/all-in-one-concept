Ghost TestSuite - Example of Java Selenium TestFramawork
=======================

This is an example to demonstrate a TestFramework for UI testing. Written in Java with help of Selenium 3 WebDriver, Maven and TestNG.

## Introduction

This software have been written to show how should properly designed framework look like. To demonstrate good practices, structure and techniques used for test automation.

In directory `./doc` you can find all documents demonstrating **test planing** and **test execution** phrase as well as all documents that should be created during this phrases. The approach is to provide absolute minimum amount of documentation to cover all necessary information and make base for well organized test project. Only MS Word and Excel have been used as in practice this is the only documenting standard across most of the companies around word. [This folder usually would not be part of code repository but for purposes of easy demonstration it have been placed here]

## Design Explanation

### Features
Features that TestFramework should have in order to be easy and fast to use, clear and easy to understand, maintainable and scalable.

* General
  * easy to install & run
    - we don't want new employees to spend hours trying to run the test as well as we want developers to be able to quickly run tests for their purposes
    - achieved by Maven, one command to install all dependencies and run tests
      - can run from command line - useful for Jenkins integration and for developers
      - can run from IDE - useful during test development, easy to analyze test runs and results 
  * configurable test run 
    - possible to run one test, all tests in one script, group of tests, all tests with particular attribute, all test without particular attribute, all tests
    - achieved by SureFire and FailSafe Maven plugins and TestNG
      - all configuration in pom.xml file - makes easy to adjust configuration when everything is in one file
      - can be configured through command line parameters - useful to overwrite configuration in particular cases
  * reporting 
    - possible e.g. XML in JUnit, MySQL, Excel, console output, possible to track status during run
    - achieved by FailSafe plugin in XML, JUnit XML, HTML, txt format
    - trackind status during test run have to be done
  * logging and error handling 
    - makes easy to debug tests scripts and identify issues
    - to be done more properly
  * clean code
    - achieved by:
      - PageObject design patterns 
      - descriptive naming policy - test scripts names and test method names clearly describe what is tested; each function call within the test method describes test step; by this tests are documentation in the same time
      - no comments policy - functions names and parameters manes should clearly explain what this function is doing
    
  
* Specific for UI testing
  * platform independent 
    - possible to run on Windows, Linux, Mac OS X
    - to be tested on Windows and Linux
  * browser independent
    - achieved by Factory design pattern, possible to run against any browser
    - need to be tested on other browsers than Firefox or Chrome
  * maintainable 
    - page look and HTML structure changes from time to time, framework should be easily adjustable
    - achieved by ObjectMap design pattern, one text file which is map between UI and test code so developers if changing UI, they can change map file as well and tests remain valid

### Structure
The fallowing structure have been presented to show good practices of TestSuite organization.

* Project root
  * `pom.xml` - contains all settings for installation and tests run
  * `logging.properties` - text file with the logging settings, should be in project root because contains global settings as well
  * `user-interface-map.properties` - text file contains ObjectMap between UI and test code, should be at the project root because contains common content and should be easily accessible for everybody
  * `src/test/java/org.prosky.ghost.selenium` - actual TestSuite
  * `src/test/resources` - resources used for testing
  * `target` - all files generated during test run
 
* TestSiute (org.prosky.ghost.selenium)
  * `TestEnviroment.java` - general script contains all function needed to prepare TestEnvironment
  * `baseFramework` - all modules/classes providing base framework functionality, can be common for many test suites of the same type i.e. functional tests of UI for different WebApplications
  * `pageObjects` - wrapper of selenium functions to page specific business readable functions, represent  functionality of specific WebApplication
  * `testCases` - test scripts and test methods have to fallow the naming convention while groups not; this naming convention allows easy recognize tests and ensure order of execution; pattern is fallowed by fully descriptive name explaining what is tested; groups just describe general tested functionality and order is not important
    * `Test_01_someFunctionality.java`
    * `Test_01_someFunctionality.java > test_01_particularUsageOfFunctionality`
    * `testsGroup`
    * `testsGroup/Test_01_someFunctionalityOfThisGroup.java`
    
### TestCases
The examples of test solving various programing challenges as well as showing useful functionality of TestFramework.

* `Test_01_BaseFunctionality.java`:
    Contain very basic tests like entering blog page, entering admin page and log in. They are basic check it there was no major error in the code so code doesn't compile, doesn't start of crash just after start. If any of these fail there is no sense to run any other because it means that there is an obvious mistake that need to be fixed immediately. In order to ensure this tests have annotation @Test(groups = "initialCheck") and all other have annotation @Test(dependsOnGroups = { "initialCheck" }). Additionally other tests have parameter Test(ignoreMissingDependencies = true}) this is useful during test development and allow to run this one test separetly. So if full TestSuite is run and initialCheck not success, dependent tests would be skipped but if run a single tests, dependencies would be ignored.

* `Test_02_PostCreateEditDelete.java`:
    This tests are showing DataDriven approach. Each tests is run multiple times depends on data set.


## Installation

### Prerequisites

1. [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
2. [Maven 3](https://maven.apache.org/download.cgi)
3. [Chrome](https://www.google.com/chrome/browser/desktop/index.html)
4. [Firefox](https://www.mozilla.org/en-US/firefox/new/)
5. [TestObject: Ghost CMS](https://github.com/prosky-pmaj/ghost-cms)

### How to run it

1. Open a terminal window/command prompt
2. Clone this project.
3. `cd ghost-test-suite` (or whatever folder you cloned it into)
4. `mvn clean verify`

All dependencies should now be downloaded and the Selenium test will have run successfully (assuming you have Firefox installed in the default location).

### What should I know?

- To run any unit tests that test your Selenium framework you just need to ensure that all unit test file names end, or start with "test" and they will be run as part of the build.
- The maven failsafe plugin has been used to create a profile with the id "selenium-tests".  This is active by default. It will pick up any files that matching this pattern `**/Test_??_*.java` by default.

It is possible to perform a build without running your selenium tests you can disable it using:
		mvn clean verify -P-selenium-tests

### Anything else?

Yes you can specify which browser to use by using one of the following switches:

- -Dbrowser=firefox
- -Dbrowser=chrome
- -Dbrowser=ie (not tested yet)
- -Dbrowser=edge (not tested yet)
- -Dbrowser=opera (not tested yet)
- -Dbrowser=htmlunit (not tested yet)
- -Dbrowser=phantomjs (not tested yet)

You don't need to worry about downloading the IEDriverServer, MicrosoftWebDriver, chromedriver , operachromium, or wires binaries, this project will do that for you automatically. Not got PhantomJS? Don't worry that will be automatically downloaded for you as well!

You can specify a grid to connect to where you can choose your browser, browser version and platform:

- -Dremote=true 
- -DseleniumGridURL=http://{username}:{accessKey}@ondemand.saucelabs.com:80/wd/hub 
- -Dplatform=xp 
- -Dbrowser=firefox 
- -DbrowserVersion=44

You can even specify multiple threads (you can do it on a grid as well!):

- -Dthreads=2

You can also specify a proxy to use

- -DproxyEnabled=true
- -DproxyHost=localhost
- -DproxyPort=8080

TODO: If the tests fail screenshots will be saved in ${project.basedir}/target/screenshots

If you need to force a binary overwrite you can do:

- -Doverwrite.binaries=true


### It's not working!!!

You have probably got outdated driver binaries, by default they are not overwritten if they already exist to speed things up.  You have two options:

- `mvn clean verify -Doverwrite.binaries=true`
- Delete the `selenium_standalone_binaries` folder in your resources directory
