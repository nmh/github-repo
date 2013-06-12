package com.heliocentris.rms.core.access;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.data.DataType;
import com.heliocentris.rms.core.util.Version;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="DataTypePersister")
public class DataTypePersisterBean extends AbstractEntityPersister<DataType, Long> implements DataTypePersister
{
   private static final Logger logger = LoggerFactory.getLogger(DataTypePersisterBean.class);

   
   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.DataTypeProvider#findBy(com.heliocentris.rms.core.util.Version, short, int)
    */
   public DataType findBy(Version emSwVersion, short moduleId, int id)
   {
      DataType result = null;
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<DataType> criteria = builder.createQuery(DataType.class);
      Root<DataType> entity = criteria.from(DataType.class);
      
      Predicate predicate = builder.equal(entity.get("id"), id);
      predicate = builder.and(predicate, builder.equal(entity.get("moduleId"), moduleId));
      if (emSwVersion == null)
      {
         predicate = builder.isNull(entity.get("emSwVersion"));
      }
      else
      {
         predicate = builder.equal(entity.get("emSwVersion"), emSwVersion);
      }
      
      TypedQuery<DataType> query = entityManager.createQuery(criteria.where(predicate));
      
      query.setMaxResults(1);
      List<DataType> resultList = query.getResultList();
      if (resultList.size() > 0)
      {
         result = resultList.get(0);
      }
      return logger.exiting(result);
   }

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.DataTypeProvider#getAllEmSwVersion()
    */
   public Set<Version> getAllEmSwVersion()
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Version> criteria = builder.createQuery(Version.class);
      Root<DataType> entity = criteria.from(DataType.class);
      criteria.select(entity.get("emSwVersion").as(Version.class)).distinct(true);
      TypedQuery<Version> query = entityManager.createQuery(criteria);
      List<Version> resultList = query.getResultList();
      
      return logger.exiting(new LinkedHashSet<Version>(resultList));
   }

   public List<DataType> getAll(Version emSwVersion)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<DataType> criteria = builder.createQuery(DataType.class);
      Root<DataType> entity = criteria.from(DataType.class);
      
      Predicate predicate = null;
      if (emSwVersion == null)
      {
         predicate = builder.isNull(entity.get("emSwVersion"));
      }
      else
      {
         predicate = builder.equal(entity.get("emSwVersion"), emSwVersion);
      }
      TypedQuery<DataType> query = entityManager.createQuery(criteria.where(predicate));
      List<DataType> resultList = query.getResultList();
      return logger.exiting(resultList);
   }

   public List<Short> getAllModuleIdBy(Version emSwVersion)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Short> criteria = builder.createQuery(Short.class);
      Root<DataType> entity = criteria.from(DataType.class);
      
      Predicate predicate = null;
      if (emSwVersion == null)
      {
         predicate = builder.isNull(entity.get("emSwVersion"));
      }
      else
      {
         predicate = builder.equal(entity.get("emSwVersion"), emSwVersion);
      }
      criteria.select(entity.get("moduleId").as(Short.class)).distinct(true);
      TypedQuery<Short> query = entityManager.createQuery(criteria.where(predicate));
      List<Short> resultList = query.getResultList();
      return logger.exiting(resultList);
   }
   
}
