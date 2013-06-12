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
import com.heliocentris.rms.core.data.CurrentData;
import com.heliocentris.rms.core.data.CurrentHeader;
import com.heliocentris.rms.core.data.Data;
import com.heliocentris.rms.core.data.HistoryData;
import com.heliocentris.rms.core.data.HistoryHeader;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;
import com.heliocentris.rms.util.service.Service;

@Stateless(name="CurrentHeaderPersister")
public class CurrentHeaderPersisterBean extends AbstractEntityPersister<CurrentHeader, Long> implements CurrentHeaderPersister
{
   private static final Logger logger = LoggerFactory.getLogger(CurrentHeaderPersisterBean.class);

   public CurrentHeader find(Site site, long packageId, Date creationDate)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      CriteriaQuery<CurrentHeader> criteria = builder.createQuery(CurrentHeader.class);
      Root<CurrentHeader> entity = criteria.from(CurrentHeader.class);
      
      Predicate predicate = null;      
      if (site == null)
      {
         predicate = builder.isNull(entity.get("site"));
      }
      else
      {
         predicate = builder.equal(entity.get("site"), site);
      }
      predicate = builder.and(predicate, builder.equal(entity.get("packageId"), packageId));
      if (creationDate == null)
      {
         predicate = builder.and(predicate, builder.isNull(entity.get("creationDate")));
      }
      else
      {
         predicate = builder.and(predicate, builder.equal(entity.get("creationDate"), creationDate));
      }
      TypedQuery<CurrentHeader> query = entityManager.createQuery(criteria.where(predicate));
   
      query.setMaxResults(1);
      List<CurrentHeader> resultList = query.getResultList();
      CurrentHeader result = null;
      if (resultList.size() > 0)
      {
         result = resultList.get(0);
      }
      return logger.exiting(result);
   }

   
   public List<CurrentHeader> getAllBy(Site site, Date startDate, Date endDate, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      CriteriaQuery<CurrentHeader> criteria = builder.createQuery(CurrentHeader.class);
      Root<CurrentHeader> entity = criteria.from(CurrentHeader.class);
      
      Predicate predicate = null;      
      if (site == null)
      {
         predicate = builder.isNull(entity.get("site"));
      }
      else
      {
         predicate = builder.equal(entity.get("site"), site);
      }
      if (startDate != null)
      {
         predicate = builder.and(predicate, builder.greaterThanOrEqualTo(entity.get("creationDate").as(Date.class), startDate));
      }
      if (endDate != null)
      {
         predicate = builder.and(predicate, builder.lessThanOrEqualTo(entity.get("creationDate").as(Date.class), endDate));
      }
      TypedQuery<CurrentHeader> query = entityManager.createQuery(criteria.where(predicate));
   
      query.setMaxResults(maxResults);
      List<CurrentHeader> resultList = query.getResultList();
      return logger.exiting(resultList);
   }

   public CurrentHeader insert(CurrentHeader currentHeader, boolean history)
   {
      logger.entering(currentHeader);

      if (history)
      {
         HistoryHeader historyHeader = new HistoryHeader(currentHeader);
         historyHeader = Service.getService(HistoryHeaderPersister.class).insert(historyHeader);
         
         for(Data data : currentHeader.getData())
         {
            CurrentData currentData = (CurrentData) data;
            HistoryData historyData = new HistoryData(currentData);
            historyHeader.addData(historyData);
            
            historyData = Service.getService(HistoryDataPersister.class).insert(historyData);
            currentData.setOid(historyData.getOid());
         }
         currentHeader.setOid(historyHeader.getOid());
      }
      return logger.exiting(super.insert(currentHeader));
   }
   
   
}
