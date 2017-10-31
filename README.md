Ghost TestSuite - Example of Java Selenium TestFramawork
=======================

This is an example to demonstrate a TestFramework for testing user interface written in Java and base on Selenium 3 WebDriver, Maven and TestNG.

## Design Explanation

### Features
Features that testing framework should have in order to be easy and fast to use, clear and easy to understand, maintainable and scalable.

* General
  * easy to install & run
    - we don't want new employees to spend hours trying to run the test as well as we want developers to be able to quickly run tests for their purposes
    - here achieved by Maven, one command to install all dependencies and run tests
      - can run from command line - useful for Jenkins integration and for developers
      - can run from IDE - useful during test development, easy to analyze test runs and results 
  * configurable test run 
    - possible to run one test, all tests in one script, group of tests, all tests with particular attribute, all test without particular attribute, all tests
    - here achieved by SureFire and FailSafe Maven plugins and TestNG
      - all config in pom.xml file - makes it easy to adjust configuration when all is in one file
      - can be config through command line parameters - useful to overwrite config in particular cases
  * reporting 
    - possible e.g. XML in JUnit, MySQL, Excel, console output, possible to track status during run
    - to be done
  * logging and error handling 
    - makes easy to debug tests scripts and identify issues
    - to be done more properly
  * clean code 
    - libraries not but test script should be business readable
    - here achieved by PageObject or ComopnentInterface design patterns 
  * 
  
* Specific for UserInterface testing
  * platform independent 
    - possible to run on Windows, Linux, Mac OS X
    - to be tested on Windows and Linux
  * browser independent
    - here achieved by Factory design pattern, possible to run against any browser, to be tested
  * maintainable 
    - page look and HTML structure changes from time to time, framework should be easily adjustable
    - here achieved by ObjectMap design pattern, one text file which is map between UI and test code so developers if changing UI, they can change map file as well and tests remain valid

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
