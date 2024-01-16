package kp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * The wrapper on 'Java Util Logging' with simplified format.<br>
 * It prints only the message without any other items (like date, level).
 * <p>
 * The code which uses 'System.out.println' is noncompliant in 'SonarQube'.
 */
public class Printer {

	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
	static {
		final InputStream inputStream = Printer.class.getClassLoader().getResourceAsStream("logging.properties");
		try (inputStream) {
			readLogConfiguration(inputStream);
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * The constructor.
	 */
	private Printer() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Prints the message to the console with simplified logger.
	 * 
	 * @param message the message
	 */
	public static void print(String message) {

		if (logger.isLoggable(Level.INFO)) {
			logger.info(message);
		}
	}

	/**
	 * Formats the message and prints it to the console with simplified logger.
	 * 
	 * @param format the message format
	 * @param args   the message items
	 */
	public static void printf(String format, Object... args) {

		if (logger.isLoggable(Level.INFO)) {
			logger.info(String.format(format, args));
		}
	}

	/**
	 * Prints the horizontal rule.
	 * 
	 */
	public static void printHor() {

		if (logger.isLoggable(Level.INFO)) {
			logger.info("- ".repeat(50));
		}
	}

	/**
	 * Reads the log configuration.
	 *
	 *
	 * @param inputStream the {@link InputStream}
	 * @throws IOException the {@link IOException}
	 */
	@SuppressWarnings("java:S4792") // SonarQube: configuring loggers is security-sensitive
	private static void readLogConfiguration(InputStream inputStream) throws IOException {
		LogManager.getLogManager().readConfiguration(inputStream);
	}

}
