package com.heliocentris.rms.core.access;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.Network;
import com.heliocentris.rms.core.data.EventType;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="EventTypePersister")
public class EventTypePersisterBean extends AbstractEntityPersister<EventType, Long> implements EventTypePersister
{
   private static final Logger logger = LoggerFactory.getLogger(EventTypePersisterBean.class);

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.EventTypeProvider#find(com.heliocentris.rms.core.Network, short, short)
    */
   public EventType find(Network network, short moduleId, short code)
   {
      logger.entering(network, moduleId, code);
      
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<EventType> criteria = builder.createQuery(EventType.class);
      Root<EventType> entity = criteria.from(EventType.class);

      Predicate predicate = builder.isNull(entity.get("network"));
      if (network != null)
      {
         predicate = builder.or(predicate, builder.equal(entity.get("network"), network));
      }
      predicate = builder.and(predicate, builder.equal(entity.get("moduleId"), moduleId));
      predicate = builder.and(predicate, builder.equal(entity.get("code"), code));

      criteria = criteria.where(predicate);
      criteria = criteria.orderBy(builder.asc(entity.get("network")));
      TypedQuery<EventType> query = entityManager.createQuery(criteria);
      
      query.setMaxResults(1);
      List<EventType> resultList = query.getResultList();
      
      EventType result = null;
      if (resultList.size() > 0)
      {
         result = resultList.get(0);
      }
      return logger.exiting(result);
   }

   
}
