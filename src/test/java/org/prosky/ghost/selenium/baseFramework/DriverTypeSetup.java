package org.prosky.ghost.selenium.baseFramework;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface DriverTypeSetup {
	/**
	 * Gets WebDriver with specified configurations.
	 *
	 * @param desiredCapabilities
	 *            configurations for the driver
	 * @return Updated driver with configurations for its type
	 */
	WebDriver

			getWebDriverObject(DesiredCapabilities desiredCapabilities);

	/**
	 * Gets the specific configurations for the driver.
	 *
	 * @param proxySettings
	 *            configurations for the driver
	 * @return Updated configurations for the driver
	 */
	DesiredCapabilities getDesiredCapabilities(Proxy proxySettings);
}
