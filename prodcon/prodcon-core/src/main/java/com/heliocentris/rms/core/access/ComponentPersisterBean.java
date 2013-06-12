package com.heliocentris.rms.core.access;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.Component;
import com.heliocentris.rms.core.Unit;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="ComponentPersister")
public class ComponentPersisterBean extends AbstractEntityPersister<Component, Long> implements ComponentPersister
{
   private static final Logger logger = LoggerFactory.getLogger(ComponentPersisterBean.class);

   public Unit findUnitBySerialNumber(String serialNumber)
   {
      logger.entering(serialNumber);
      
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
      Root<Unit> unit = criteria.from(Unit.class);
      
      Predicate predicate = builder.equal(unit.get("serialNumber"), serialNumber);
      TypedQuery<Unit> query = entityManager.createQuery(criteria.where(predicate));
   
      query.setMaxResults(1);
      List<Unit> resultList = query.getResultList();
      Unit result = null;
      if (resultList.size() > 0)
      {
         result = resultList.get(0);
      }
      return logger.exiting(result);
   }
}
