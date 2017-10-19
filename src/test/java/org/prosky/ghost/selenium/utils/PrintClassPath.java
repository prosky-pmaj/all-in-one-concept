package org.prosky.ghost.selenium.utils;

import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLClassLoader;

public class PrintClassPath {

	public static void main(String args[]) throws FileNotFoundException {

		ClassLoader cl = ClassLoader.getSystemClassLoader();

		URL[] urls = ((URLClassLoader) cl).getURLs();
		for (URL url : urls) {
			System.out.println(url.getFile());
		}

	}
}