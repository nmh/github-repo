package com.heliocentris.rms.util.junit;

import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.omg.CORBA.SystemException;

import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;

/**
 * The <code>EntityTransaction</code> for test classes.
 * 
 * <p>Usage:
 * <pre>
 * Service.getService(UserTransaction.class).commit();
 * </pre>
 * 
 * @organisation Heliocentris GmbH
 * @author mnguyen
 * @since 13.06.2013
 */
public class TestClassRunnerNullTransaction implements UserTransaction {
	private static final Logger logger = LoggerFactory.getLogger(TestClassRunnerNullTransaction.class);

	@Override
	public void begin() {
		logger.info("TRANSACTION BEGIN");
	}

	@Override
	public void commit() {
		logger.info("TRANSACTION COMMIT");
	}

	@Override
	public void rollback() {
		logger.info("TRANSACTION ROLLBACK");
	}

	@Override
	public void setRollbackOnly() {
		logger.info("SET ROLLBACK ONLY");
	}

	@Override
	public int getStatus() {
		return Status.STATUS_NO_TRANSACTION;
	}

	@Override
	public void setTransactionTimeout(int seconds) throws SystemException {
		logger.entering(seconds);
	}
}
