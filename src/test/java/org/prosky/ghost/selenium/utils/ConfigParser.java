package org.prosky.ghost.selenium.utils;
// package org.prosky.ghost.selenium;
//
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.nio.file.Paths;
// import java.util.Properties;
// import java.util.logging.Level;
// import java.util.logging.Logger;
//
// public class ConfigParser {
// private static final Logger LOG =
// Logger.getLogger(ConfigParser.class.getName());
// private static String configPath =
// Paths.get("config.properties").toAbsolutePath().toString();
//
// protected static String getPropertyFromConfig(String name, String
// defoultValue) {
// InputStream input = null;
// try {
// Properties config = new Properties();
// input = new FileInputStream(configPath);
// config.load(input);
// return config.getProperty(name, defoultValue);
// } catch (IOException ex) {
// LOG.log(Level.WARNING, "Failed to read config file: " + configPath, ex);
// LOG.warning("Parameter: " + name + "=" + defoultValue + " [defoult value
// applied]");
// } finally {
// if (input != null) {
// try {
// input.close();
// } catch (final IOException e) {
// LOG.log(Level.WARNING, "Failed to close config file.", e);
// }
// }
// }
// return defoultValue;
// }
//
// public static String getAdminEmail() {
// return getPropertyFromConfig("users.admin.email", "");
// }
//
// public static String getAdminPassword() {
// return getPropertyFromConfig("users.admin.password", "");
// }
//
// public static String getConfigPath() {
// return configPath;
// }
// }