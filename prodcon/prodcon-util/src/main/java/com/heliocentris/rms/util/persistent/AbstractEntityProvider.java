package com.heliocentris.rms.util.persistent;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;


/**
 * @author Thien
 */
public abstract class AbstractEntityProvider<Entity extends Serializable, Key extends Serializable> implements EntityProvider<Entity, Key>
{
   private static final Logger logger = LoggerFactory.getLogger(AbstractEntityProvider.class);

   private Class<Entity> entityClass;

   @PersistenceContext
   private EntityManager entityManager;

   public AbstractEntityProvider()
   {
      setEntityClass(findEntityClass());
   }

   public AbstractEntityProvider(EntityManager entityManager)
   {
      setEntityManager(entityManager);
      setEntityClass(findEntityClass());
   }

   /**
    * @return
    */
   public Class<Entity> getEntityClass()
   {
      return entityClass;
   }

   /**
    * @param entityClass
    */
   protected void setEntityClass(Class<Entity> entityClass)
   {
      this.entityClass = entityClass;
   }

   /**
     *  
     */
   @SuppressWarnings("unchecked")
   protected Class<Entity> findEntityClass()
   {
      Class<?> aClass = getClass();
      while (!(aClass.getGenericSuperclass() instanceof ParameterizedType))
      {
         aClass = aClass.getSuperclass();
      }
      ParameterizedType type = (ParameterizedType) aClass.getGenericSuperclass();
      Type entityType = type.getActualTypeArguments()[0];
      if (entityType instanceof ParameterizedType)
      {
         entityType = ((ParameterizedType) entityType).getRawType();
      }
      return (Class<Entity>) entityType;
   }

   /**
    * @return
    */
   public EntityManager getEntityManager()
   {
      return entityManager;
   }

   /**
    * @param entityManager
    */
   public void setEntityManager(EntityManager entityManager)
   {
      assert entityManager != null;
      this.entityManager = entityManager;
   }

   /*
    * (non-Javadoc)
    * 
    * @see fdx.persistence.entity.EntityProvider#find(Key)
    */
   public Entity find(Key oid)
   {
      return find(getEntityClass(), oid);
   }

   /*
    * (non-Javadoc)
    * 
    * @see fdx.persistence.entity.EntityProvider#find(java.lang.Class, Key)
    */
   public <T extends Entity> T find(Class<T> type, Key oid)
   {
      logger.entering(type, oid);
      T entity = getEntityManager().find(type, oid);
      return logger.exiting(entity);
   }

   /*
    * (non-Javadoc)
    * 
    * @see fdx.persistence.entity.EntityProvider#getAll()
    */
   public List<Entity> getAll()
   {
      return getAll(getEntityClass(), OrderBy.Ascending, 0, Integer.MAX_VALUE);
   }

   public List<Entity> getAll(int maxResutls)
   {
      return getAll(getEntityClass(), OrderBy.Ascending, 0, maxResutls);
   }
   
   public List<Entity> getAll(int firstResult, int maxResutls)
   {
      return getAll(getEntityClass(), OrderBy.Ascending, firstResult, maxResutls);
   }
   
   /*
    * (non-Javadoc)
    * 
    * @see fdx.persistence.entity.EntityProvider#getAll(java.lang.Class)
    */
   public <T extends Entity> List<T> getAll(Class<T> type)
   {
      return getAll(type, OrderBy.None, 0, Integer.MAX_VALUE);
   }

   public <T extends Entity> List<T> getAll(Class<T> type, int maxResults)
   {
      return getAll(type, OrderBy.Ascending, 0, maxResults);
   }
   
   /*
    * (non-Javadoc)
    * 
    * @see fdx.persistence.entity.EntityProvider#getAll(java.lang.Class, int)
    */
   public <T extends Entity> List<T> getAll(Class<T> type, int firstResult, int maxResults)
   {
      return getAll(type, OrderBy.Ascending, firstResult, maxResults);
   }
   
   /*
    * (non-Javadoc)
    * 
    * @see fdx.persistence.entity.EntityProvider#getAll(java.lang.Class, OrderBy)
    */
   public List<Entity> getAll(OrderBy oidOrderBy)
   {
      return getAll(getEntityClass(), oidOrderBy, 0, Integer.MAX_VALUE);
   }
   
   /*
    * (non-Javadoc)
    * 
    * @see fdx.persistence.entity.EntityProvider#getAll(java.lang.Class, OrderBy)
    */
   public <T extends Entity> List<T> getAll(Class<T> type, OrderBy oidOrderBy)
   {
      return getAll(type, oidOrderBy, 0, Integer.MAX_VALUE);
   }
   
   /*
    * (non-Javadoc)
    * 
    * @see fdx.persistence.entity.EntityProvider#getAll(java.lang.Class, OrderBy, int)
    */
   public <T extends Entity> List<T> getAll(Class<T> type, OrderBy oidOrderBy, int firstResult, int maxResults)
   {
      logger.entering(type, oidOrderBy, maxResults);
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<T> criteria = builder.createQuery(type);
      Root<T> entity = criteria.from(type);
      switch (oidOrderBy)
      {
         case Ascending:
         {
            criteria.orderBy(builder.asc(entity.get("oid"))); break;
         }
         case Descending:
         {
            criteria.orderBy(builder.desc(entity.get("oid"))); break;
         }
         default:
         {
            break;
         }
      }
      TypedQuery<T> query = entityManager.createQuery(criteria);
         
      query.setMaxResults(maxResults);
      query.setFirstResult(firstResult);
      List<T> resultList = query.getResultList();
      return logger.exiting(resultList);
      
   }
   
   /*
    * (non-Javadoc)
    * 
    * @see fdx.persistence.entity.EntityProvider#getCount()
    */
   public long getCount()
   {
      return getCount(getEntityClass());
   }

   /*
    * (non-Javadoc)
    * 
    * @see fdx.persistence.entity.EntityProvider#getCount()
    */
   @Override
   public <T extends Entity> long getCount(Class<T> type)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
      Root<T> entity = criteriaQuery.from(type);
      criteriaQuery.select(criteriaBuilder.count(entity));
      TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
      return logger.exiting(query.getSingleResult());
   }

   public <T extends Entity> T detach(T entity)
   {
      logger.entering(entity);
      getEntityManager().detach(entity);
      return logger.exiting(entity);
   }

   public <T extends Entity> Collection<T> detach(Collection<T> entities)
   {
      logger.entering(entities);
      EntityManager entityManager = getEntityManager();
      for (Entity entity : entities)
      {
         entityManager.detach(entity);
      }
      return logger.exiting(entities);
   }

   public <T extends Entity> T getReference(Class<T> type, Key oid)
   {
      return getEntityManager().getReference(type, oid);
   }
   
   public void flush()
   {
      getEntityManager().flush();
   }

   public void clear()
   {
      getEntityManager().clear();
   }
   
}
