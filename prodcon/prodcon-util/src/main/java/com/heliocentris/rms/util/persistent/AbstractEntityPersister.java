package com.heliocentris.rms.util.persistent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;

/**
 * 
 * @organisation Heliocentris GmbH
 * @author Thien
 * @since 13.06.2013
 * @param <Entity>
 * @param <Key>
 */
public abstract class AbstractEntityPersister<Entity extends Serializable, Key extends Serializable> extends
		AbstractEntityProvider<Entity, Key> implements EntityPersister<Entity, Key> {
	private static final Logger logger = LoggerFactory.getLogger(AbstractEntityPersister.class);

	public AbstractEntityPersister() {
	}

	public AbstractEntityPersister(EntityManager entityManager) {
		super(entityManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fdx.persistence.entity.EntityPersister#insert(Entity)
	 */
	public <T extends Entity> T insert(T entity) {
		logger.entering(entity);
		getEntityManager().persist(entity);
		return logger.exiting(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fdx.persistence.entity.EntityPersister#update(Entity)
	 */
	public <T extends Entity> T update(T entity) {
		logger.entering(entity);
		entity = getEntityManager().merge(entity);
		return logger.exiting(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fdx.persistence.entity.EntityPersister#delete(Entity)
	 */
	public <T extends Entity> T delete(T entity) {
		logger.entering(entity);
		EntityManager entityManager = getEntityManager();
		if (!entityManager.contains(entity)) {
			logger.debug("entity manager has no {}. First merge then remove!", entity);
			entity = entityManager.merge(entity);
		}
		entityManager.remove(entity);
		return logger.exiting(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fdx.persistence.entity.EntityPersister#deleteAll()
	 */
	public int deleteAll() {
		return deleteAll(getEntityClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fdx.persistence.entity.EntityPersister#deleteAll(java.lang.Class)
	 */
	public <T extends Entity> int deleteAll(Class<T> type) {
		logger.entering(type);
		EntityManager entityManager = getEntityManager();
		List<T> resultList = getAll(type);
		for (T item : getAll(type)) {
			entityManager.remove(item);
		}
		return logger.exiting(resultList.size());
	}

}
