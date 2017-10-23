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
	Logger logger = Logger.getLogger(Helper.class.getName());
	logger.info("Hallo");
	System.setProperty(
		"java.util.logging.config.file",
		"/Users/maju/Documents/Egnyte/Private/maju/Dokumenty/Work/Frameworks/ghost-test-suite/config.properties");
	System.out.println(System.getProperties().toString().replaceAll(",", "\n")
		.replaceAll("(?!://):", "\n\t\t").replaceAll("=/", "=\n\t\t/"));
    }

    public static void printGetResourcesInStaticContext() {
	System.out.println("Helper.class.getClassLoader()."
		+ "getResource(/Users/maju/Projects/ghost-test-suite/logging.properties)");
	System.out.println(Helper.class.getClassLoader()
		.getResource("/Users/maju/Projects/ghost-test-suite/logging.properties"));
	System.out.println("Helper.class.getClassLoader().getResource(logging.properties)");
	System.out.println(Helper.class.getClassLoader().getResource("logging.properties"));
	System.out.println(Helper.class.getClassLoader().getResource("/logging.properties"));
	System.out.println(Helper.class.getClassLoader().getResource("./logging.properties"));
	System.out
		.println("Helper.class.getClassLoader().getResourceAsStream(postData.properties)");
	System.out.println(Helper.class.getClassLoader().getResource("postData.properties"));
	System.out.println(Helper.class.getClassLoader().getResource("/postData.properties"));
	System.out.println(Helper.class.getClassLoader().getResource("./postData.properties"));
	System.out.println("Helper.class.getClassLoader().getResourceAsStream(same.lvl.pp)");
	System.out.println(Helper.class.getClassLoader().getResource("same.lvl.pp"));
	System.out.println(Helper.class.getClassLoader().getResource("/same.lvl.pp"));
	System.out.println(Helper.class.getClassLoader().getResource("./same.lvl.pp"));
	System.out.println("Helper.class.getClassLoader().getResourceAsStream(parent.lvl.of.pp)");
	System.out.println(Helper.class.getClassLoader().getResource("parent.lvl.of.pp"));
	System.out.println(Helper.class.getClassLoader().getResource("/parent.lvl.of.pp"));
	System.out.println(Helper.class.getClassLoader().getResource("./parent.lvl.of.pp"));

	System.out.println("Helper.class."
		+ "getResource(/Users/maju/Projects/ghost-test-suite/logging.properties)");
	System.out.println(Helper.class
		.getResource("/Users/maju/Projects/ghost-test-suite/logging.properties"));
	System.out.println("Helper.class.getResource(logging.properties)");
	System.out.println(Helper.class.getResource("logging.properties"));
	System.out.println(Helper.class.getResource("/logging.properties"));
	System.out.println(Helper.class.getResource("./logging.properties"));
	System.out.println("Helper.class.getResourceAsStream(postData.properties)");
	System.out.println(Helper.class.getResource("postData.properties"));
	System.out.println(Helper.class.getResource("/postData.properties"));
	System.out.println(Helper.class.getResource("./postData.properties"));
	System.out.println("Helper.class.getResourceAsStream(same.lvl.pp)");
	System.out.println(Helper.class.getResource("same.lvl.pp"));
	System.out.println(Helper.class.getResource("/same.lvl.pp"));
	System.out.println(Helper.class.getResource("./same.lvl.pp"));
	System.out.println("Helper.class.getResourceAsStream(parent.lvl.of.pp)");
	System.out.println(Helper.class.getResource("parent.lvl.of.pp"));
	System.out.println(Helper.class.getResource("/parent.lvl.of.pp"));
	System.out.println(Helper.class.getResource("./parent.lvl.of.pp"));
    }

    public void printGetResourcesInNonStaticContext() {
	System.out.println("getClass().getClassLoader()"
		+ ".getResourceAsStream(/Users/maju/Projects/ghost-test-suite/logging.properties)");
	System.out.println(getClass().getClassLoader()
		.getResource("/Users/maju/Projects/ghost-test-suite/logging.properties"));
	System.out.println("getClass().getClassLoader().getResourceAsStream(logging.properties)");
	System.out.println(getClass().getClassLoader().getResource("logging.properties"));
	System.out.println(getClass().getClassLoader().getResource("/logging.properties"));
	System.out.println(getClass().getClassLoader().getResource("./logging.properties"));
	System.out.println("getClass().getClassLoader().getResourceAsStream(postData.properties)");
	System.out.println(getClass().getClassLoader().getResource("postData.properties"));
	System.out.println(getClass().getClassLoader().getResource("/postData.properties"));
	System.out.println(getClass().getClassLoader().getResource("./postData.properties"));
	System.out.println("getClass().getClassLoader().getResourceAsStream(same.lvl.pp)");
	System.out.println(getClass().getClassLoader().getResource("same.lvl.pp"));
	System.out.println(getClass().getClassLoader().getResource("/same.lvl.pp"));
	System.out.println(getClass().getClassLoader().getResource("./same.lvl.pp"));
	System.out.println("getClass().getClassLoader().getResourceAsStream(parent.lvl.of.pp)");
	System.out.println(getClass().getClassLoader().getResource("parent.lvl.of.pp"));
	System.out.println(getClass().getClassLoader().getResource("/parent.lvl.of.pp"));
	System.out.println(getClass().getClassLoader().getResource("./parent.lvl.of.pp"));

	System.out.println(
		"getClass().getResourceAsStream(/Users/maju/Projects/ghost-test-suite/logging.properties)");
	System.out.println(
		getClass().getResource("/Users/maju/Projects/ghost-test-suite/logging.properties"));
	System.out.println("getClass().getResourceAsStream(logging.properties)");
	System.out.println(getClass().getResource("logging.properties"));
	System.out.println(getClass().getResource("/logging.properties"));
	System.out.println(getClass().getResource("./logging.properties"));
	System.out.println("getClass().getResourceAsStream(postData.properties)");
	System.out.println(getClass().getResource("postData.properties"));
	System.out.println(getClass().getResource("/postData.properties"));
	System.out.println(getClass().getResource("./postData.properties"));
	System.out.println("getClass().getResourceAsStream(same.lvl.pp)");
	System.out.println(getClass().getResource("same.lvl.pp"));
	System.out.println(getClass().getResource("/same.lvl.pp"));
	System.out.println(getClass().getResource("./same.lvl.pp"));
	System.out.println("getClass().getResourceAsStream(parent.lvl.of.pp)");
	System.out.println(getClass().getResource("parent.lvl.of.pp"));
	System.out.println(getClass().getResource("/parent.lvl.of.pp"));
	System.out.println(getClass().getResource("./parent.lvl.of.pp"));
    }
}
