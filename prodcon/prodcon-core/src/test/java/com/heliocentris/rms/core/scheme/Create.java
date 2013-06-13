package com.heliocentris.rms.core.scheme;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceProperty;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.heliocentris.rms.util.junit.TestClassRunner;
import com.heliocentris.rms.util.service.Service;

/**
 * Run this junit test to drop all the old one and create new tables, constraints and indexes in your database.
 * 
 * @organisation Heliocentris GmbH
 * @author mnguyen
 * @since 13.06.2013
 */
@RunWith(TestClassRunner.class)
@PersistenceContext(unitName = "test", properties = { @PersistenceProperty(name = "hibernate.hbm2ddl.auto", value = "create") })

@Service.Context(context = "META-INF/test-service-local.xml")

public class Create {
	@Test
	public void testEntityManager() {
		
		
		EntityManager entityManager = Service.getService(EntityManager.class);
		entityManager.flush();
		entityManager.clear();
	}
}
