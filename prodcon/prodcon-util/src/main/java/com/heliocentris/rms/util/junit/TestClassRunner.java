package com.heliocentris.rms.util.junit;

import java.util.ArrayList;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceProperty;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import javax.xml.transform.Result;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.service.Service;

/**
 * Define your test case with
 * 
 * <pre>
 * &#064;RunWith(TestClassRunner.class)
 * &#064;PersistenceContext(unitName = &quot;test&quot;, properties = { @PersistenceProperty(name = &quot;hibernate.hbm2ddl.auto&quot;, value = &quot;create-drop&quot;) })
 * public class YourTestCase {
 * 
 * }
 * </pre>
 * 
 * and all connection and testClassRunnerTransaction will be handled for you
 * automatically.
 * 
 * @author thien
 */

public class TestClassRunner extends org.junit.runners.BlockJUnit4ClassRunner {
	private static final Logger logger = LoggerFactory.getLogger(TestClassRunner.class);

	private RunNotifier notifier = null;

	public TestClassRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
		try {
			logger.entering(testClass);

			PersistenceContext persistenceContext = testClass.getAnnotation(PersistenceContext.class);
			if (persistenceContext != null) {
				EntityManagerFactory entityManagerFactory = createEntityManagerFactory(persistenceContext);
				Service.addService(EntityManagerFactory.class, entityManagerFactory);
			}

			Service.Context serviceContext = testClass.getAnnotation(Service.Context.class);
			if (serviceContext != null) {
				Service.addContext(serviceContext.context());
			}

			logger.exiting();
		} catch (Exception exception) {
			ArrayList<Throwable> errors = new ArrayList<Throwable>();
			errors.add(exception);
			throw new InitializationError(errors);
		}
	}

	@Override
	public void run(RunNotifier notifier) {
		this.notifier = notifier;

		RunListener runListener = new RunListener();

		notifier.addFirstListener(runListener);
		notifier.fireTestRunStarted(getDescription());

		try {
			super.run(notifier);
		} catch (Exception exception) {
			notifier.fireTestFailure(new Failure(getDescription(), exception));
		}

		notifier.fireTestRunFinished(null);
		notifier.removeListener(runListener);

	}

	private class RunListener extends org.junit.runner.notification.RunListener {

		@Override
		public void testRunStarted(Description description) throws Exception {
			try {
				logger.entering(description);
				begin();
			} catch (Exception exception) {
				notifier.fireTestFailure(new Failure(description, exception));
			}
			logger.exiting();
		}

		@Override
		public void testStarted(Description description) throws Exception {
			logger.entering(description);
			try {
				begin();
			} catch (Exception exception) {
				notifier.fireTestFailure(new Failure(description, exception));
			}
			logger.exiting();
		}

		@Override
		public void testFailure(Failure failure) throws Exception {
			logger.entering(failure);
			try {
				rollback();
			} catch (Exception exception) {
				logger.catching(exception);
			}

			try {
				begin();
			} catch (Exception exception) {
				logger.catching(exception);
			}
			logger.exiting();
		}

		@Override
		public void testFinished(Description description) throws Exception {
			logger.entering(description);
			try {
				commit();
			} catch (Exception exception) {
				notifier.fireTestFailure(new Failure(description, exception));
			}

			try {
				begin();
			} catch (Exception exception) {
				notifier.fireTestFailure(new Failure(description, exception));
			}
			logger.exiting();
		}

		public void testRunFinished(Result result) throws Exception {
			try {
				logger.entering(result);
				commit();
			} catch (Exception exception) {
				notifier.fireTestFailure(new Failure(getDescription(), exception));
			}
			logger.exiting();
		}
	}

	private EntityManagerFactory createEntityManagerFactory(PersistenceContext persistenceContext) throws Exception {
		logger.exiting(persistenceContext);

		EntityManagerFactory entityManagerFactory = null;
		Properties properties = new Properties();
		for (PersistenceProperty property : persistenceContext.properties()) {
			properties.setProperty(property.name(), property.value());
		}
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceContext.unitName(), properties);
		return logger.exiting(entityManagerFactory);
	}

	private void begin() throws Exception {
		UserTransaction userTransaction = Service.getService(UserTransaction.class);
		if (userTransaction != null) {
			if (userTransaction.getStatus() == Status.STATUS_ACTIVE) {
				userTransaction.commit();
			}
			userTransaction.begin();
		}
	}

	private void commit() throws Exception {
		UserTransaction userTransaction = Service.getService(UserTransaction.class);
		if (userTransaction != null) {
			if (userTransaction.getStatus() == Status.STATUS_ACTIVE) {
				userTransaction.commit();
			} else if (userTransaction.getStatus() == Status.STATUS_MARKED_ROLLBACK) {
				userTransaction.rollback();
			}
		}
	}

	private void rollback() throws Exception {
		UserTransaction userTransaction = Service.getService(UserTransaction.class);
		if (userTransaction != null) {
			userTransaction.rollback();
		}
	}
}
