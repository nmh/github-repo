package com.heliocentris.rms.core.access;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.Domain;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="DomainPersister")
public class DomainPersisterBean extends AbstractEntityPersister<Domain, Long> implements DomainPersister
{
   private static final Logger logger = LoggerFactory.getLogger(DomainPersisterBean.class);

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.NetworkProvider#findByName(java.lang.String)
    */
   public Domain findByName(String name)
   {
      return findByName(getEntityClass(), name);
   }

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.DomainProvider#findByName(java.lang.Class, java.lang.String)
    */
   public <T extends Domain> T findByName(Class<T> type, String name)
   {
      T result = null;
      if (name != null && !name.isEmpty())
      {
         EntityManager entityManager = getEntityManager();
         CriteriaBuilder builder = entityManager.getCriteriaBuilder();
         CriteriaQuery<T> criteria = builder.createQuery(type);
         Root<T> domain = criteria.from(type);
         criteria.where(builder.equal(domain.get("name"), name));
         TypedQuery<T> query = entityManager.createQuery(criteria);
         
         query.setMaxResults(1);
         List<T> resultList = query.getResultList();
         if (resultList.size() > 0)
         {
            result = resultList.get(0);
         }
      }
      return logger.exiting(result);
   }
   
   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.NetworkProvider#getAllEnabled()
    */
   public List<Domain> getAllEnabled()
   {
      return getAllByDisabled(Domain.class, false);
   }
   
   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.NetworkProvider#getAllByDisabled(java.lang.Class, boolean)
    */
   public <T extends Domain> List<T> getAllByDisabled(Class<T> type, boolean disabled)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<T> criteria = builder.createQuery(type);
      Root<T> domain = criteria.from(type);
      criteria.where(builder.equal(domain.get("disabled"), disabled));
      TypedQuery<T> query = entityManager.createQuery(criteria);
      
      List<T> resultList = query.getResultList();
      return logger.exiting(resultList);
   }
}
