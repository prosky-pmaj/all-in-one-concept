package org.prosky.ghost.selenium.utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

import org.testng.log.TextFormatter;

public class ConfigureLogger {
	static private Logger globallogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void setGlobalLevel(Level newLevel) throws Exception {
		globallogger.setLevel(newLevel);
	}

	public static void configureConsoleOutput(Level newLevel) throws Exception {
		ConsoleHandler console = new ConsoleHandler();
		Formatter formatterTxt = new TextFormatter();
		console.setFormatter(formatterTxt);
		console.setLevel(newLevel);
		globallogger.addHandler(console);
	}

	public static void suppressConsoleOutput() throws Exception {
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		if (handlers[0] instanceof ConsoleHandler) {
			rootLogger.removeHandler(handlers[0]);
		}
	}

	public static void configureTxtFileOutput(Level newLevel) throws Exception {
		// boolean append = false;
		// fileTxt = new FileHandler("default.log", append);
		FileHandler fileTxt = new FileHandler("Logging.txt");
		Formatter formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		fileTxt.setLevel(newLevel);
		globallogger.addHandler(fileTxt);
	}

	public static void configureXMLFileOutput(Level newLevel) throws Exception {
		FileHandler fileXml = new FileHandler("Logging.xml");
		Formatter formatterXml = new XMLFormatter();
		fileXml.setFormatter(formatterXml);
		fileXml.setLevel(newLevel);
		globallogger.addHandler(fileXml);
	}

	public static void manage(Level newLevel) throws Exception {
		LogManager manager = LogManager.getLogManager();
		manager.readConfiguration();
	}

	// public static void configureHTMLFileOutput() throws Exception {
	// FileHandler fileHTML = new FileHandler("Logging.html");
	// Formatter formatterHTML = new MyHtmlFormatter();
	// fileHTML.setFormatter(formatterHTML);
	// logger.addHandler(fileHTML);
	// }
}