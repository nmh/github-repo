package com.heliocentris.rms.util.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;

/**
 * 
 * @organisation Heliocentris GmbH
 * @author Thien
 * @since 13.06.2013
 */
public class Service {
	private static final Logger logger = LoggerFactory.getLogger(Service.class);

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Context {
		String context();
	}

	private static final Service instance = new Service();

	private Map<Class<?>, Object> services = new HashMap<Class<?>, Object>();

	public Map<Class<?>, Object> getServices() {
		return logger.exiting(services);
	}

	public void setServices(Map<Class<?>, Object> services) {
		logger.entering(services);
		this.services = services;
		logger.exiting();
	}

	public static Service getInstance() {
		return instance;
	}

	protected Service() {
	}

	public static void addContext(Class<?> aClass) {
		logger.entering(aClass);

		Context context = aClass.getAnnotation(Context.class);
		if (context != null) {
			addContext(context.context());
		}

		logger.exiting();
	}

	public static void addContext(Service.Context context) {
		addContext(context.context());
	}

	@SuppressWarnings("resource")
	public static void addContext(String context) {
		logger.entering(context);

		new ClassPathXmlApplicationContext(context).registerShutdownHook();

		logger.exiting("{}", instance.services);
	}

	public static <T, V extends T> void addService(Class<T> serviceClass, V service) {
		logger.entering(serviceClass, service);
		getInstance().services.put(serviceClass, service);
		logger.exiting();
	}

	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<T> serviceClass) {
		logger.entering(serviceClass);
		return logger.exiting((T) getInstance().services.get(serviceClass));
	}
}
