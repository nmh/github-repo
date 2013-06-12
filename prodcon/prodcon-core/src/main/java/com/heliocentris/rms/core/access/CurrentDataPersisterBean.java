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
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="CurrentDataPersister")
public class CurrentDataPersisterBean extends AbstractEntityPersister<CurrentData, Long> implements CurrentDataPersister
{
   private static final Logger logger = LoggerFactory.getLogger(CurrentDataPersisterBean.class);

   @Override
   public List<CurrentData> getAllBy(Site site, int firstResult, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      CriteriaQuery<CurrentData> criteria = builder.createQuery(CurrentData.class);
      Root<CurrentData> data = criteria.from(CurrentData.class);
      Join<CurrentData, Header> header = data.join("header");
      
      if (site == null)
      {
         criteria.where(builder.isNull(header.get("site")));
      }
      else
      {
         criteria.where(builder.equal(header.get("site"), site));
      }
      TypedQuery<CurrentData> query = entityManager.createQuery(criteria.orderBy(builder.asc(header.get("creationDate"))));
   
      query.setMaxResults(maxResults);
      query.setFirstResult(firstResult);
      List<CurrentData> resultList = query.getResultList();
      return logger.exiting(resultList);
   }

   @Override
   public List<CurrentData> getAllBy(Site site, int dataTypeId, int firstResult, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();

      Class<CurrentData> type = CurrentData.class;
      CriteriaQuery<CurrentData> criteria = builder.createQuery(type);
      Root<CurrentData> data = criteria.from(type);
      Join<CurrentData, Header> header = data.join("header");
      
      Predicate predicate = builder.equal(data.get("dataTypeId"), dataTypeId);      
      if (site == null)
      {
         predicate = builder.and(predicate, builder.isNull(header.get("site")));
      }
      else
      {
         predicate = builder.and(predicate, builder.equal(header.get("site"), site));
      }
      TypedQuery<CurrentData> query = entityManager.createQuery(criteria.where(predicate).orderBy(builder.asc(header.get("creationDate"))));
   
      query.setMaxResults(maxResults);
      query.setFirstResult(firstResult);
      List<CurrentData> resultList = query.getResultList();
      return logger.exiting(resultList);
   }


   @Override
   public List<CurrentData> getAllBy(Site site, Date startDate, Date endDate, int firstResult, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();

      Class<CurrentData> type = CurrentData.class;
      CriteriaQuery<CurrentData> criteria = builder.createQuery(type);
      Root<CurrentData> data = criteria.from(type);
      Join<CurrentData, Header> header = data.join("header");
      
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
      TypedQuery<CurrentData> query = entityManager.createQuery(criteria.where(predicate).orderBy(builder.asc(header.get("creationDate"))));
   
      query.setMaxResults(maxResults);
      query.setFirstResult(firstResult);
      List<CurrentData> resultList = query.getResultList();
      return logger.exiting(resultList);
   }
   
   @Override
   public  List<CurrentData> getAllBy(Component component, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      Class<CurrentData> type = CurrentData.class;
      CriteriaQuery<CurrentData> criteria = builder.createQuery(type);
      Root<CurrentData> entity = criteria.from(type);
      
      if (component == null)
      {
         criteria.where(builder.isNull(entity.get("component")));
      }
      else
      {
         criteria.where(builder.equal(entity.get("component"), component));
      }
      TypedQuery<CurrentData> query = entityManager.createQuery(criteria);
   
      query.setMaxResults(maxResults);
      List<CurrentData> resultList = query.getResultList();
      return logger.exiting(resultList);
   }


   @Override
   public List<CurrentData> getAllBy(Component component, int dataTypeId, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      Class<CurrentData> type = CurrentData.class;
      CriteriaQuery<CurrentData> criteria = builder.createQuery(type);
      Root<CurrentData> entity = criteria.from(type);
      
      Predicate predicate = null;      
      if (component == null)
      {
         predicate = builder.isNull(entity.get("component"));
      }
      else
      {
         predicate = builder.equal(entity.get("component"), component);
      }
      predicate = builder.and(predicate, builder.equal(entity.get("dataTypeId"), dataTypeId));
      TypedQuery<CurrentData> query = entityManager.createQuery(criteria.where(predicate));
   
      query.setMaxResults(maxResults);
      List<CurrentData> resultList = query.getResultList();
      return logger.exiting(resultList);
   }

   public List<CurrentData> getAllBy(Component component, Date startDate, Date endDate, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      Class<CurrentData> type = CurrentData.class;
      CriteriaQuery<CurrentData> criteria = builder.createQuery(type);
      Root<CurrentData> entity = criteria.from(type);
      Join<CurrentData, Header> header = entity.join("header");
      
      Predicate predicate = null;
      if (component == null)
      {
         predicate = builder.isNull(entity.get("component"));
      }
      else
      {
         predicate = builder.equal(entity.get("component"), component);
         predicate = builder.and(predicate, builder.equal(header.get("site"), component.getSite()));
      }
      if (startDate != null)
      {
         predicate = builder.and(predicate, builder.greaterThanOrEqualTo(header.get("creationDate").as(Date.class), startDate));
      }
      if (endDate != null)
      {
         predicate = builder.and(predicate, builder.lessThanOrEqualTo(header.get("creationDate").as(Date.class), endDate));
      }
      TypedQuery<CurrentData> query = entityManager.createQuery(criteria.where(predicate).orderBy(builder.asc(header.get("creationDate"))));
   
      query.setMaxResults(maxResults);
      List<CurrentData> resultList = query.getResultList();
      return logger.exiting(resultList);
   }

   public List<CurrentData> getAllBy(Component component, int dataTypeId, Date startDate, Date endDate, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      Class<CurrentData> type = CurrentData.class;
      CriteriaQuery<CurrentData> criteria = builder.createQuery(type);
      Root<CurrentData> entity = criteria.from(type);
      Join<CurrentData, Header> header = entity.join("header");
      
      Predicate predicate = null;
      predicate = builder.equal(entity.get("dataTypeId"), dataTypeId);
      if (component == null)
      {
         predicate = builder.and(predicate, builder.isNull(entity.get("component")));
      }
      else
      {
         predicate = builder.and(predicate, builder.equal(entity.get("component"), component));
         predicate = builder.and(predicate, builder.equal(header.get("site"), component.getSite()));
      }
      if (startDate != null)
      {
         predicate = builder.and(predicate, builder.greaterThanOrEqualTo(header.get("creationDate").as(Date.class), startDate));
      }
      if (endDate != null)
      {
         predicate = builder.and(predicate, builder.lessThanOrEqualTo(header.get("creationDate").as(Date.class), endDate));
      }
      TypedQuery<CurrentData> query = entityManager.createQuery(criteria.where(predicate).orderBy(builder.asc(header.get("creationDate"))));
   
      query.setMaxResults(maxResults);
      List<CurrentData> resultList = query.getResultList();
      return logger.exiting(resultList);
   }
   
   public List<CurrentData> getAllBy(Component component, int dataTypeId, Date startDate, Date endDate, int firstResult, int maxResults)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      
      CriteriaQuery<CurrentData> criteria = builder.createQuery(CurrentData.class);
      Root<CurrentData> data = criteria.from(CurrentData.class);
      
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
      
      TypedQuery<CurrentData> query = entityManager.createQuery(criteria.where(predicate).orderBy(builder.asc(data.get("creationDate"))));
   
      query.setMaxResults(maxResults);
      query.setFirstResult(firstResult);
      List<CurrentData> resultList = query.getResultList();
      return logger.exiting(resultList);
   }
   
}
