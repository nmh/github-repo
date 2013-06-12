package com.heliocentris.rms.core.access;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.util.TimeZone;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="TimeZonePersister")
public class TimeZonePersisterBean extends AbstractEntityPersister<TimeZone, Long> implements TimeZonePersister
{
   private static final Logger logger = LoggerFactory.getLogger(TimeZonePersisterBean.class);
   
   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.TimeZoneProvider#findById(java.lang.String)
    */
   public TimeZone findById(String id)
   {
      
      TimeZone result = null;
      if (id != null && !id.isEmpty())
      {
         EntityManager entityManager = getEntityManager();
         CriteriaBuilder builder = entityManager.getCriteriaBuilder();
         CriteriaQuery<TimeZone> criteria = builder.createQuery(TimeZone.class);
         Root<TimeZone> entity = criteria.from(TimeZone.class);
         
         criteria.where(builder.equal(entity.get("id"), id));
         TypedQuery<TimeZone> query = entityManager.createQuery(criteria);
         
         query.setMaxResults(1);
         List<TimeZone> resultList = query.getResultList();
         if (resultList.size() > 0)
         {
            result = resultList.get(0);
         }
      }
      return logger.exiting(result);
   }

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.TimeZoneProvider#getAllEnabled()
    */
   @Override
   public List<TimeZone> getAllEnabled()
   {
      return getAllByDisabled(false);
   }

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.TimeZoneProvider#getAllByDisabled(boolean)
    */
   @Override
   public List<TimeZone> getAllByDisabled(boolean disabled)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<TimeZone> criteria = builder.createQuery(TimeZone.class);
      Root<TimeZone> entity = criteria.from(TimeZone.class);
      
      criteria.where(builder.equal(entity.get("disabled"), disabled));
      TypedQuery<TimeZone> query = entityManager.createQuery(criteria);
      
      List<TimeZone> resultList = query.getResultList();
      return logger.exiting(resultList);
   }
   
   
}
