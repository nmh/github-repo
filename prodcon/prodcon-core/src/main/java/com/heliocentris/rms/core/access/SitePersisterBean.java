package com.heliocentris.rms.core.access;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.Site;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="SitePersister")
public class SitePersisterBean extends AbstractEntityPersister<Site, Long> implements SitePersister
{
   private static final Logger logger = LoggerFactory.getLogger(SitePersisterBean.class);
   
   public Site findById(String id)
   {
      Site result = null;
      if (id != null && !id.isEmpty())
      {
         EntityManager entityManager = getEntityManager();
         CriteriaBuilder builder = entityManager.getCriteriaBuilder();
         CriteriaQuery<Site> criteria = builder.createQuery(Site.class);
         Root<Site> entity = criteria.from(Site.class);
         criteria.where(builder.equal(entity.get("id"), id));
         TypedQuery<Site> query = entityManager.createQuery(criteria);
         
         query.setMaxResults(1);
         List<Site> resultList = query.getResultList();
         if (resultList.size() > 0)
         {
            result = resultList.get(0);
         }
      }
      return logger.exiting(result);
   }
   
}
