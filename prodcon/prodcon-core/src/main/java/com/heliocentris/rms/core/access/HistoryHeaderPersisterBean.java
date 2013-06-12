package com.heliocentris.rms.core.access;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.Site;
import com.heliocentris.rms.core.data.CurrentHeader;
import com.heliocentris.rms.core.data.HistoryHeader;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;
import com.heliocentris.rms.util.service.Service;

@Stateless(name="HistoryHeaderPersister")
public class HistoryHeaderPersisterBean extends AbstractEntityPersister<HistoryHeader, Long> implements HistoryHeaderPersister
{
   private static final Logger logger = LoggerFactory.getLogger(HistoryHeaderPersisterBean.class);

   public HistoryHeader find(Site site, long packageId, Date creationDate)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      CriteriaQuery<HistoryHeader> criteria = builder.createQuery(HistoryHeader.class);
      Root<HistoryHeader> header = criteria.from(HistoryHeader.class);
      
      Predicate predicate = null;      
      if (site == null)
      {
         predicate = builder.isNull(header.get("site"));
      }
      else
      {
         predicate = builder.equal(header.get("site"), site);
      }
      predicate = builder.and(predicate, builder.equal(header.get("packageId"), packageId));
      if (creationDate == null)
      {
         predicate = builder.and(predicate, builder.isNull(header.get("creationDate")));
      }
      else
      {
         predicate = builder.and(predicate, builder.equal(header.get("creationDate"), creationDate));
      }
      TypedQuery<HistoryHeader> query = entityManager.createQuery(criteria.where(predicate));
   
      query.setMaxResults(1);
      List<HistoryHeader> resultList = query.getResultList();
      HistoryHeader result = null;
      if (resultList.size() > 0)
      {
         result = resultList.get(0);
      }
      return logger.exiting(result);
   }


   public List<HistoryHeader> getAllBy(Site site, Date startDate, Date endDate, int maxResults)
   {
      return getAllBy(site, startDate, endDate, 0, maxResults);
   }
   
   public List<HistoryHeader> getAllBy(Site site, Date startDate, Date endDate, int firstResult, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      CriteriaQuery<HistoryHeader> criteria = builder.createQuery(HistoryHeader.class);
      Root<HistoryHeader> header = criteria.from(HistoryHeader.class);
      
      Predicate predicate = null;      
      if (site == null)
      {
         predicate = builder.isNull(header.get("site"));
      }
      else
      {
         predicate = builder.equal(header.get("site"), site);
      }
      if (startDate != null)
      {
         predicate = builder.and(predicate, builder.greaterThanOrEqualTo(header.get("creationDate").as(Date.class), startDate));
      }
      if (endDate != null)
      {
         predicate = builder.and(predicate, builder.lessThanOrEqualTo(header.get("creationDate").as(Date.class), endDate));
      }
      TypedQuery<HistoryHeader> query = entityManager.createQuery(criteria.where(predicate));
   
      query.setMaxResults(maxResults);
      query.setFirstResult(firstResult);
      List<HistoryHeader> resultList = query.getResultList();
      return logger.exiting(resultList);
   }
   
   public boolean exists(Site site, long packageId, Date creationDate)
   {
      logger.entering(site, packageId, creationDate);
      
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
      Root<HistoryHeader> header = criteria.from(HistoryHeader.class);
      
      Predicate predicate = builder.equal(header.get("site"), site);
      predicate = builder.and(predicate, builder.equal(header.get("packageId"), packageId));
      predicate = builder.and(predicate, builder.equal(header.get("creationDate"), creationDate));
      
      criteria.select(builder.count(header));
      TypedQuery<Long> query = entityManager.createQuery(criteria.where(predicate));
      
      return logger.exiting(query.getSingleResult() > 0);
   }
   
   @Override
   public <T extends HistoryHeader> T insert(T entity)
   {
      CurrentHeaderPersister currentHeaderPersister = Service.getService(CurrentHeaderPersisterBean.class);      
      CurrentHeader currentHeader = new CurrentHeader(entity);
      currentHeader = currentHeaderPersister.insert(currentHeader);
      entity.setOid(currentHeader.getOid());
      currentHeader = currentHeaderPersister.delete(currentHeader);
      return logger.exiting(entity);
   }
   
}
