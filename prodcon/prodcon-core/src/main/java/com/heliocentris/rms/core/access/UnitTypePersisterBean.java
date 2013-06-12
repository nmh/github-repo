package com.heliocentris.rms.core.access;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.UnitType;
import com.heliocentris.rms.core.util.Version;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="UnitTypePersister")
public class UnitTypePersisterBean extends AbstractEntityPersister<UnitType, Long> implements UnitTypePersister
{
   private static final Logger logger = LoggerFactory.getLogger(UnitTypePersisterBean.class);

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.UnitTypeProvider#findBy(short)
    */
   public UnitType findBy(short moduleId)
   {
      UnitType result = null;
      
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<UnitType> criteria = builder.createQuery(UnitType.class);
      Root<UnitType> entity = criteria.from(UnitType.class);
      
      criteria.where(builder.equal(entity.join("moduleType").get("moduleId"), moduleId));
      TypedQuery<UnitType> query = entityManager.createQuery(criteria);
      
      query.setMaxResults(1);
      List<UnitType> resultList = query.getResultList();
      if (resultList.size() > 0)
      {
         result = resultList.get(0);
      }
      return logger.exiting(result);
   }

   @Override
   public UnitType findBy(boolean generic, short moduleId, Version hwVersion, Version swVersion)
   {
      UnitType result = null;
      
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<UnitType> criteria = builder.createQuery(UnitType.class);
      Root<UnitType> entity = criteria.from(UnitType.class);
      
      Predicate predicate = builder.equal(entity.get("generic"), generic);
      predicate = builder.and(predicate, builder.equal(entity.join("moduleType").get("moduleId"), moduleId));
      if (hwVersion == null)
      {
         predicate = builder.and(predicate, builder.isNull(entity.get("hwVersion")));
      }
      else
      {
         predicate = builder.and(predicate, builder.equal(entity.get("hwVersion"), hwVersion));
      }
      if (swVersion == null)
      {
         predicate = builder.and(predicate, builder.isNull(entity.get("swVersion")));
      }
      else
      {
         predicate = builder.and(predicate, builder.equal(entity.get("swVersion"), swVersion));
      }
      
      TypedQuery<UnitType> query = entityManager.createQuery(criteria.where(predicate));
      
      query.setMaxResults(1);
      List<UnitType> resultList = query.getResultList();
      if (resultList.size() > 0)
      {
         result = resultList.get(0);
      }
      return logger.exiting(result);
   }

   
}
