package com.heliocentris.rms.util.logger;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

/**
 * A utility that provides standard mechanisms for logging certain kinds of activities.
 * 
 * @organisation Heliocentris GmbH
 * @author mnguyen
 * @since 13.06.2013
 */
public class Logger extends LoggerWrapper {
	private static final String FQCN = Logger.class.getName();

	static Marker SEVERE_MARKER = MarkerFactory.getMarker("SEVERE");
	static Marker CONFIG_MARKER = MarkerFactory.getMarker("CONFIG");
	static Marker WARN_MARKER = MarkerFactory.getMarker("WARN");

	static Marker INFO_MARKER = MarkerFactory.getMarker("INFO");
	static Marker FLOW_MARKER = MarkerFactory.getMarker("FLOW");
	static Marker ENTERING_MARKER = MarkerFactory.getMarker("ENTER");
	static Marker EXITING_MARKER = MarkerFactory.getMarker("RETURN");

	static Marker EXCEPTION_MARKER = MarkerFactory.getMarker("EXCEPTION");
	static Marker THROWING_MARKER = MarkerFactory.getMarker("THROW");
	static Marker CATCHING_MARKER = MarkerFactory.getMarker("CATCH");

	static {
		ENTERING_MARKER.add(FLOW_MARKER);
		EXITING_MARKER.add(FLOW_MARKER);
		THROWING_MARKER.add(EXCEPTION_MARKER);
		CATCHING_MARKER.add(EXCEPTION_MARKER);
	}

	static String[] ENTERING_MESSAGES = { "ENTER ", "ENTER {}", "ENTER {}, {}", "ENTER {}, {}, {}" };
	static String[] EXITING_MESSAGES = { "RETURN ", "RETURN {}", "RETURN {}, {}", "RETURN {}, {}, {}" };

	/**
	 * Given an underlying logger, construct an XLogger
	 * 
	 * @param logger
	 *            underlying logger
	 */
	public Logger(org.slf4j.Logger logger) {
		super(logger, LoggerWrapper.class.getName());
	}

	/**
	 * Log method info.
	 * 
	 * @param arg
	 *            supplied argument
	 */
	@Override
	public void info(String format, Object... args) {
		if (instanceofLAL && logger.isInfoEnabled(INFO_MARKER)) {
			// String formattedMessage = MessageFormatter.arrayFormat(format,
			// args);
			FormattingTuple tuple = MessageFormatter.arrayFormat(format, args);
			((LocationAwareLogger) logger).log(INFO_MARKER, FQCN, LocationAwareLogger.INFO_INT, tuple.getMessage(),
					null, null);
		}
	}

	public void config(String message) {
		if (instanceofLAL) {
			FormattingTuple tuple = MessageFormatter.format("CONFIG: {}", message);
			((LocationAwareLogger) logger).log(CONFIG_MARKER, FQCN, LocationAwareLogger.WARN_INT, tuple.getMessage(),
					null, null);
		}
	}

	public void warning(String message) {
		if (instanceofLAL) {
			((LocationAwareLogger) logger).log(WARN_MARKER, FQCN, LocationAwareLogger.WARN_INT, message, null, null);
		}
	}

	public void severe(String message) {
		if (instanceofLAL) {
			FormattingTuple tuple = MessageFormatter.format("SEVERE: {}", message);
			((LocationAwareLogger) logger).log(SEVERE_MARKER, FQCN, LocationAwareLogger.ERROR_INT, tuple.getMessage(),
					null, null);
		}
	}

	/**
	 * Log method entering
	 */
	public void entering() {
		if (instanceofLAL && logger.isTraceEnabled(ENTERING_MARKER)) {
			((LocationAwareLogger) logger).log(ENTERING_MARKER, FQCN, LocationAwareLogger.DEBUG_INT,
					ENTERING_MESSAGES[0], null, null);
		}
	}

	public void entering(Object arg) {
		if (instanceofLAL && logger.isTraceEnabled(ENTERING_MARKER)) {
			// String formattedMessage =
			// MessageFormatter.format(ENTERING_MESSAGES[1], arg);
			FormattingTuple tuple = MessageFormatter.format(ENTERING_MESSAGES[1], arg);
			((LocationAwareLogger) logger).log(ENTERING_MARKER, FQCN, LocationAwareLogger.DEBUG_INT,
					tuple.getMessage(), null, null);
		}
	}

	/**
	 * Log method entry.
	 * 
	 * @param argArray
	 *            supplied parameters
	 */
	public void entering(Object... args) {
		if (instanceofLAL && logger.isTraceEnabled(ENTERING_MARKER)) {
			String messagePattern = null;
			if (args.length < ENTERING_MESSAGES.length) {
				messagePattern = ENTERING_MESSAGES[args.length];
			} else {
				messagePattern = buildMessagePattern(ENTERING_MESSAGES[0], args.length);
			}
			FormattingTuple tuple = MessageFormatter.arrayFormat(messagePattern, args);
			((LocationAwareLogger) logger).log(ENTERING_MARKER, FQCN, LocationAwareLogger.DEBUG_INT,
					tuple.getMessage(), null, null);
		}
	}

	/**
	 * Log an exception being catching
	 * 
	 * @param throwable
	 *            the exception being caught.
	 */
	public void catching(Throwable throwable) {
		if (instanceofLAL) {
			((LocationAwareLogger) logger).log(THROWING_MARKER, FQCN, LocationAwareLogger.ERROR_INT, "CATCHING ", null,
					throwable);
		}
	}

	/**
	 * Log an exception being thrown
	 * 
	 * @param throwable
	 *            the exception being caught.
	 */
	public <T extends Throwable> T throwing(T throwable) {
		if (instanceofLAL) {
			((LocationAwareLogger) logger).log(THROWING_MARKER, FQCN, LocationAwareLogger.ERROR_INT, "THROWING ", null,
					throwable);
		}
		return throwable;
	}

	/**
	 * Log method exiting
	 */
	public void exiting() {
		if (instanceofLAL && logger.isTraceEnabled(EXITING_MARKER)) {
			((LocationAwareLogger) logger).log(EXITING_MARKER, FQCN, LocationAwareLogger.DEBUG_INT,
					EXITING_MESSAGES[0], null, null);
		}
	}

	/**
	 * Log method exiting
	 */
	public <T> T exiting(T result) {
		if (instanceofLAL && logger.isTraceEnabled(EXITING_MARKER)) {
			// String formattedMessage =
			// MessageFormatter.format(EXITING_MESSAGES[1], result);
			FormattingTuple tuple = MessageFormatter.format(EXITING_MESSAGES[1], result);
			((LocationAwareLogger) logger).log(EXITING_MARKER, FQCN, LocationAwareLogger.DEBUG_INT, tuple.getMessage(),
					null, null);
		}
		return result;
	}

	/**
	 * Log method exiting.
	 * 
	 * @param argArray
	 *            supplied parameters
	 */
	public <T> T[] exiting(T... args) {
		if (instanceofLAL && logger.isTraceEnabled(EXITING_MARKER)) {
			String messagePattern = null;
			if (args.length < EXITING_MESSAGES.length) {
				messagePattern = EXITING_MESSAGES[args.length];
			} else {
				messagePattern = buildMessagePattern(EXITING_MESSAGES[0], args.length);
			}
			// String formattedMessage =
			// MessageFormatter.arrayFormat(messagePattern, args);
			FormattingTuple tuple = MessageFormatter.arrayFormat(messagePattern, args);
			((LocationAwareLogger) logger).log(EXITING_MARKER, FQCN, LocationAwareLogger.DEBUG_INT, tuple.getMessage(),
					null, null);
		}
		return args;
	}

	private static String buildMessagePattern(String header, int len) {
		StringBuilder sb = new StringBuilder();
		sb.append(header);
		for (int i = 0; i < len; i++) {
			sb.append("{}");
			if (i != len - 1)
				sb.append(", ");
		}
		return sb.toString();
	}

}
