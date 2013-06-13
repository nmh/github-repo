package com.heliocentris.rms.util;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Locale;

import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;

/**
 * 
 * @organisation Heliocentris GmbH
 * @author Thien
 * @since 13.06.2013
 */
public class Localizer {
	private static final Logger logger = LoggerFactory.getLogger(Localizer.class);

	public static final char SEPARATOR = '.';

	public static final String Label = "label";

	public static final String Message = "message";

	public static final String Text = "text";

	public static final String Title = "title";

	protected ResourceBundle bundle;

	public static void setLocale(Locale locale) {
		ResourceBundle.setLocale(locale);
	}

	public Localizer(String name) {
		bundle = ResourceBundle.getBundle(name);
	}

	public Localizer(Class<?>... classes) {
		bundle = ResourceBundle.getBundle(classes);
	}

	public Localizer(ResourceBundle bundle) {
		if (bundle == null)
			throw new NullPointerException();

		this.bundle = bundle;
	}

	public Localizer(ResourceBundle bundle, String... names) {
		bundle = ResourceBundle.getBundle(bundle, names);
	}

	public Localizer(ResourceBundle bundle, Class<?>... classes) {
		bundle = ResourceBundle.getBundle(bundle, classes);
	}

	public String getLabel(Localizable localizable) {
		return getLabel(localizable.getKey(), localizable.getArguments());
	}

	public String getMessage(Localizable localizable) {
		return getMessage(localizable.getKey(), localizable.getArguments());
	}

	public String getText(Localizable localizable) {
		return getText(localizable.getKey(), localizable.getArguments());
	}

	public String getTitle(Localizable localizable) {
		return MessageFormat.format(getTitle(localizable.getKey()), localizable.getArguments());
	}

	public String getLabel(String key) {
		return getString(key + SEPARATOR + Label);
	}

	public String getMessage(String key) {
		return getString(key + SEPARATOR + Message);
	}

	public String getText(String key) {
		return getString(key + SEPARATOR + Text);
	}

	public String getTitle(String key) {
		return getString(key + SEPARATOR + Title);
	}

	public String getLabel(String key, Object... args) {
		return MessageFormat.format(getLabel(key), args);
	}

	public String getMessage(String key, Object... args) {
		return MessageFormat.format(getMessage(key), args);
	}

	public String getTitle(String key, Object... args) {
		return MessageFormat.format(getTitle(key), args);
	}

	public String getText(String key, Object... args) {
		return MessageFormat.format(getText(key), args);
	}

	public String getString(String key) {
		return bundle.getString(key);
	}

	public String getString(String key, Object... args) {
		return MessageFormat.format(bundle.getString(key), args);
	}

	public void localize(Object object) {
		logger.entering(object);
		logger.exiting();
	}

	public static Localizer getLocalizer(Class<?> aClass) {
		Localizer localizer = null;
		try {
			logger.entering(aClass);

			while (aClass != null) {
				Field[] fields = aClass.getDeclaredFields();
				for (Field field : fields) {
					if (Localizer.class.isAssignableFrom(field.getType())) {
						field.setAccessible(true);
						localizer = (Localizer) field.get(aClass);
						return logger.exiting(localizer);
					}
				}
				aClass = aClass.getSuperclass();
			}
		} catch (java.lang.Exception exception) {
		}
		return logger.exiting(localizer);
	}

	public Localizer getLocalizer(Object object) {
		Localizer localizer = null;
		logger.entering(object);

		localizer = getLocalizer(object.getClass());
		if (localizer == null) {
			localizer = this;
		}
		return logger.exiting(localizer);
	}
}
