package com.heliocentris.rms.core.access;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.Component;
import com.heliocentris.rms.core.Site;
import com.heliocentris.rms.core.data.CurrentData;
import com.heliocentris.rms.core.data.Header;
import com.heliocentris.rms.core.data.HistoryData;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;
import com.heliocentris.rms.util.service.Service;

@Stateless(name="HistoryDataPersister")
public class HistoryDataPersisterBean extends AbstractEntityPersister<HistoryData, Long> implements HistoryDataPersister
{
   private static final Logger logger = LoggerFactory.getLogger(HistoryDataPersisterBean.class);

   public List<HistoryData> getAllBy(Site site, Date startDate, Date endDate, int firstResult, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      Class<HistoryData> type = HistoryData.class;
      CriteriaQuery<HistoryData> criteria = builder.createQuery(type);
      Root<HistoryData> data = criteria.from(type);
      Join<HistoryData, Header> header = data.join("header");
      
      Predicate predicate = null;      
      if (site == null)
      {
         predicate = builder.isNull(header.get("site"));
      }
      else
      {
         predicate = builder.equal(header.get("site"), site);
         if (startDate != null)
         {
            predicate = builder.and(predicate, builder.greaterThanOrEqualTo(header.get("creationDate").as(Date.class), startDate));
         }
         if (endDate != null)
         {
            predicate = builder.and(predicate, builder.lessThanOrEqualTo(header.get("creationDate").as(Date.class), endDate));
         }
         predicate = builder.and(predicate, builder.equal(data.get("segment"), site.getSegment()));
      }

      if (startDate != null)
      {
         predicate = builder.and(predicate, builder.greaterThanOrEqualTo(data.get("creationDate").as(Date.class), startDate));
      }
      if (endDate != null)
      {
         predicate = builder.and(predicate, builder.lessThanOrEqualTo(data.get("creationDate").as(Date.class), endDate));
      }
      
      TypedQuery<HistoryData> query = entityManager.createQuery(criteria.where(predicate).orderBy(builder.asc(header.get("creationDate"))));
   
      query.setMaxResults(maxResults);
      query.setFirstResult(firstResult);
      List<HistoryData> resultList = query.getResultList();
      return logger.exiting(resultList);
   }

   public List<HistoryData> getAllBy(Site site, int dataTypeId, Date startDate, Date endDate, int firstResult, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      CriteriaQuery<HistoryData> criteria = builder.createQuery(HistoryData.class);
      Root<HistoryData> data = criteria.from(HistoryData.class);
      Join<HistoryData, Header> header = data.join("header");
      
      Predicate predicate = null;      
      if (site == null)
      {
         predicate = builder.isNull(header.get("site"));
      }
      else
      {
         predicate = builder.equal(header.get("site"), site);
         if (startDate != null)
         {
            predicate = builder.and(predicate, builder.greaterThanOrEqualTo(header.get("creationDate").as(Date.class), startDate));
         }
         if (endDate != null)
         {
            predicate = builder.and(predicate, builder.lessThanOrEqualTo(header.get("creationDate").as(Date.class), endDate));
         }
         predicate = builder.and(predicate, builder.equal(data.get("segment"), site.getSegment()));         
      }

      if (startDate != null)
      {
         predicate = builder.and(predicate, builder.greaterThanOrEqualTo(header.get("creationDate").as(Date.class), startDate));
      }
      if (endDate != null)
      {
         predicate = builder.and(predicate, builder.lessThanOrEqualTo(header.get("creationDate").as(Date.class), endDate));
      }
      predicate = builder.and(predicate, builder.equal(data.get("dataTypeId"), dataTypeId));
      
      TypedQuery<HistoryData> query = entityManager.createQuery(criteria.where(predicate).orderBy(builder.asc(header.get("creationDate"))));
   
      query.setMaxResults(maxResults);
      query.setFirstResult(firstResult);      
      List<HistoryData> resultList = query.getResultList();
      return logger.exiting(resultList);
   }
   
   
   public List<HistoryData> getAllBy(Component component, Date startDate, Date endDate, int firstResult, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      CriteriaQuery<HistoryData> criteria = builder.createQuery(HistoryData.class);
      Root<HistoryData> data = criteria.from(HistoryData.class);
      
      Predicate predicate = null;
      if (component == null)
      {
         predicate = builder.isNull(data.get("component"));
      }
      else
      {
         predicate = builder.equal(data.get("component"), component);
         
         Site site = component.getSite();
         if (site != null)
         {
            predicate = builder.and(predicate, builder.equal(data.get("segment"), site.getSegment()));            
         }
      }
      if (startDate != null)
      {
         predicate = builder.and(predicate, builder.greaterThanOrEqualTo(data.get("creationDate").as(Date.class), startDate));
      }
      if (endDate != null)
      {
         predicate = builder.and(predicate, builder.lessThanOrEqualTo(data.get("creationDate").as(Date.class), endDate));
      }
      
      TypedQuery<HistoryData> query = entityManager.createQuery(criteria.where(predicate).orderBy(builder.asc(data.get("creationDate"))));
   
      query.setMaxResults(maxResults);
      query.setFirstResult(firstResult);
      List<HistoryData> resultList = query.getResultList();
      return logger.exiting(resultList);
   }

   public List<HistoryData> getAllBy(Component component, int dataTypeId, Date startDate, Date endDate)
   {
      return getAllBy(component, dataTypeId, startDate, endDate, 0, Integer.MAX_VALUE);
   }
   
   public List<HistoryData> getAllBy(Component component, int dataTypeId, Date startDate, Date endDate, int firstResult, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      CriteriaQuery<HistoryData> criteria = builder.createQuery(HistoryData.class);
      Root<HistoryData> data = criteria.from(HistoryData.class);
      
      Predicate predicate = null;
      if (component == null)
      {
         predicate = builder.isNull(data.get("component"));
      }
      else
      {
         predicate = builder.equal(data.get("component"), component);
         Site site = component.getSite();
         if (site != null)
         {
            predicate = builder.and(predicate, builder.equal(data.get("segment"), site.getSegment()));
         }
      }
      if (startDate != null)
      {
         predicate = builder.and(predicate, builder.greaterThanOrEqualTo(data.get("creationDate").as(Date.class), startDate));
      }
      if (endDate != null)
      {
         predicate = builder.and(predicate, builder.lessThanOrEqualTo(data.get("creationDate").as(Date.class), endDate));
      }
      predicate = builder.and(predicate, builder.equal(data.get("dataTypeId"), dataTypeId));
      
      TypedQuery<HistoryData> query = entityManager.createQuery(criteria.where(predicate).orderBy(builder.asc(data.get("creationDate"))));
   
      query.setMaxResults(maxResults);
      query.setFirstResult(firstResult);
      List<HistoryData> resultList = query.getResultList();
      return logger.exiting(resultList);
   }

   @Override
   public <T extends HistoryData> T insert(T entity)
   {
      CurrentDataPersister currentDataPersister = Service.getService(CurrentDataPersisterBean.class);
      CurrentData currentdata = new CurrentData(entity);
      currentdata = currentDataPersister.insert(currentdata);
      entity.setOid(currentdata.getOid());
      currentdata = currentDataPersister.delete(currentdata);
      return logger.exiting(entity);
   }
   
   
}
