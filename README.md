Ghost TestSuite - Example of Java Selenium TestFramawork
=======================

A TestFramework for Selenium 3 WebDriver combain with Maven and TestND that has the latest dependencies.

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
- The maven failsafe plugin has been used to create a profile with the id "selenium-tests".  This is active by default. It will pick up any files that mathing this pattern `**/Test_??_*.java` by default.

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
