package org.prosky.ghost.selenium.utils;

import java.util.logging.Logger;

public class Helper {
	public static void main(String[] args) {
		System.out.println("Helper.main()");
		System.out.println("Helper.class: " + Helper.class);
		System.out.println("Helper.class.getName(): " + Helper.class.getName());
		System.out.println("Helper.class.getClass(): " + Helper.class.getClass());
		System.out.println(
				"System.getProperty(\"notexisting\"): " + System.getProperty("notexisting"));
		System.out.println(
				"MyClass3.class.getClassLoader().getResourceAsStream(\"logging.properties\");");
		System.out.println(Helper.class.getClassLoader().getResource("logging.properties"));
		System.out.println("System.getProperties().toString():");
		System.out.println(
				System.getProperties().toString().replaceAll(",", "\n").replaceAll(":", "\n\t\t"));
		Logger logger = Logger.getLogger(Helper.class.getName());
		logger.info("Hallo");
		System.setProperty(
				"java.util.logging.config.file",
				"/Users/maju/Documents/Egnyte/Private/maju/Dokumenty/Work/Frameworks/ghost-test-suite/config.properties");
		System.out.println(
				System.getProperties().toString().replaceAll(",", "\n").replaceAll(":", "\n\t\t"));
	}
}
