package org.prosky.ghost.selenium.utils;
// package org.prosky.ghost.selenium;
//
// import java.io.IOException;
// import java.util.logging.Level;
// import java.util.logging.LogManager;
// import java.util.logging.Logger;
//
// public class TestEnviroment extends ConfigParser {
// private static final Logger LOG =
// Logger.getLogger(TestEnviroment.class.getName());
//
// public static void configureLogger() {
// LOG.info("Configuring logger..");
// // System.setProperty("java.util.logging.config.file", getConfigPath());
// try {
// LogManager.getLogManager().readConfiguration();
// } catch (SecurityException | IOException e) {
// LOG.log(Level.WARNING, "Failed to configure logger.", e);
// }
// }
//
// public static void configureTestEnviroment() {
// LOG.info("Configuring test enviroment..");
// System.setProperty("trimStackTrace", "false");
// }
//
// public static String getBrowser() {
// return System
// .getProperty("browser", getPropertyFromConfig("browser", "FIREFOX"))
// .toUpperCase();
// }
//
// public static String getOperatingSystem() {
// return System.getProperty("os.name").toUpperCase();
// }
//
// public static String getSystemArchitecture() {
// return System.getProperty("os.arch");
// }
// }
