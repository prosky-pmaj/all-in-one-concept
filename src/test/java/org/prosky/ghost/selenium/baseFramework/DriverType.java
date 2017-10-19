package org.prosky.ghost.selenium.baseFramework;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public enum DriverType implements DriverTypeSetup {
	FIREFOX {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.merge(capabilities);
			firefoxOptions.addPreference("browser.startup.page", 0);
			return new FirefoxDriver(firefoxOptions);
		}
	},
	CHROME {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(
					"chrome.switches",
					Collections.singletonList("--no-default-browser-check"));

			HashMap<String, String> chromePreferences = new HashMap<>();
			chromePreferences.put("profile.password_manager_enabled", "false");
			capabilities.setCapability("chrome.prefs", chromePreferences);
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.merge(capabilities);
			chromeOptions.addArguments("--verbose");
			chromeOptions.addArguments("--whitelisted-ips=''");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-infobars");
			return new ChromeDriver(chromeOptions);
		}
	},
	IE {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(
					CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
					true);
			capabilities.setCapability(
					InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,
					true);
			capabilities.setCapability("requireWindowFocus", true);
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new InternetExplorerDriver(capabilities);
		}
	},
	EDGE {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = DesiredCapabilities.edge();
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new EdgeDriver(capabilities);
		}
	},
	SAFARI {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = DesiredCapabilities.safari();
			capabilities.setCapability("safari.cleanSession", true);
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new SafariDriver(capabilities);
		}
	},
	OPERA {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
			return addProxySettings(capabilities, proxySettings);
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new OperaDriver(capabilities);
		}
	},
	PHANTOMJS {
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
			final List<String> cliArguments = new ArrayList<>();
			cliArguments.add("--web-security=false");
			cliArguments.add("--ssl-protocol=any");
			cliArguments.add("--ignore-ssl-errors=true");
			DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
			capabilities.setCapability(
					"phantomjs.cli.args",
					applyPhantomJsProxySettings(cliArguments, proxySettings));
			capabilities.setCapability("takesScreenshot", true);

			return capabilities;
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			return new PhantomJSDriver(capabilities);
		}
	};

	/**
	 * Sets specified Proxy configurations.
	 *
	 * @param capabilities
	 *            configurations for the driver
	 * @param proxySettings
	 *            proxy specific configurations for the driver
	 * @return Updated configurations for the driver
	 */
	protected DesiredCapabilities addProxySettings(
			DesiredCapabilities capabilities,
			Proxy proxySettings) {
		if (null != proxySettings) {
			capabilities.setCapability(PROXY, proxySettings);
		}
		return capabilities;
	}

	/**
	 * Sets specific to PhantomJS Proxy configurations.
	 *
	 * @param cliArguments
	 *            configurations for the driver
	 * @param proxySettings
	 *            proxy specific configurations for the driver
	 * @return Updated configurations for the driver
	 */
	protected List<String> applyPhantomJsProxySettings(
			List<String> cliArguments,
			Proxy proxySettings) {
		if (null == proxySettings) {
			cliArguments.add("--proxy-type=none");
		} else {
			cliArguments.add("--proxy-type=http");
			cliArguments.add("--proxy=" + proxySettings.getHttpProxy());
		}
		return cliArguments;
	}
}
