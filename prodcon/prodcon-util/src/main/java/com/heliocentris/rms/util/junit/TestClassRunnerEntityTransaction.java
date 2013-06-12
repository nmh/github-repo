package com.heliocentris.rms.util.junit;

import javax.persistence.EntityTransaction;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.omg.CORBA.SystemException;

import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;

/**
 * The <code>EntityTransaction</code> for test classes.
 * This is an adapter from UserTransaction to EntityTransaction.
 * 
 * <p>Usage:
 * <pre>
 * Service.getService(UserTransaction.class).commit();
 * </pre>
 * @author thien
 *
 */
public class TestClassRunnerEntityTransaction implements UserTransaction 
{
	private static final Logger logger = LoggerFactory.getLogger(TestClassRunnerEntityTransaction.class);
	
	private EntityTransaction entityTransaction;
	

	public EntityTransaction getEntityTransaction() 
	{
		return entityTransaction;
	}

	public void setEntityTransaction(EntityTransaction entityTransaction) 
	{
		this.entityTransaction = entityTransaction;
	}

	@Override
	public void begin() 
	{
		if (!entityTransaction.isActive())
		{
			entityTransaction.begin();
			logger.info("TRANSACTION BEGUN");
		}
	}

	@Override
	public void commit() 
	{
		if (entityTransaction.isActive())
		{
			if (entityTransaction.getRollbackOnly())
			{
				entityTransaction.rollback();
				logger.info("TRANSACTION ROLLED BACK");
			}
			else
			{
				entityTransaction.commit();
				logger.info("TRANSACTION COMMITTED");
			}
		}
	}

	@Override
	public void rollback() 
	{
		if (entityTransaction.isActive())
		{
			entityTransaction.rollback();
			logger.info("TRANSACTION ROLLED BACK");
		}
	}

	@Override
	public void setRollbackOnly() 
	{
		entityTransaction.setRollbackOnly();
	}

	@Override
	public int getStatus() throws SystemException 
	{
		if (entityTransaction != null && entityTransaction.isActive())
		{
			if (entityTransaction.getRollbackOnly())
			{
				return Status.STATUS_MARKED_ROLLBACK;
			}
			return Status.STATUS_ACTIVE;
		}
		return Status.STATUS_NO_TRANSACTION;
	}

	@Override
	public void setTransactionTimeout(int seconds) throws SystemException 
	{
	}
}
