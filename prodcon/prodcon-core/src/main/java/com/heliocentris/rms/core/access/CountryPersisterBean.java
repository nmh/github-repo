package com.heliocentris.rms.core.access;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.util.Country;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="CountryPersister")
public class CountryPersisterBean extends AbstractEntityPersister<Country, Long> implements CountryPersister
{
   private static final Logger logger = LoggerFactory.getLogger(CountryPersisterBean.class);

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.CountryProvider#findByIso(java.lang.String)
    */
   public Country findByIso(String iso)
   {
      Country result = null;
      if (iso != null && !iso.isEmpty())
      {
         iso = iso.toLowerCase();
         
         EntityManager entityManager = getEntityManager();
         CriteriaBuilder builder = entityManager.getCriteriaBuilder();
         CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
         Root<Country> entity = criteria.from(Country.class);
         
         criteria.where(builder.or(builder.equal(builder.lower(entity.get("iso2").as(String.class)), iso), 
                                   builder.equal(builder.lower(entity.get("iso3").as(String.class)), iso)));
         
         TypedQuery<Country> query = entityManager.createQuery(criteria);
         
         query.setMaxResults(1);
         List<Country> resultList = query.getResultList();
         if (resultList.size() > 0)
         {
            result = resultList.get(0);
         }
      }
      return logger.exiting(result);
   }

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.CountryProvider#getAllEnabled()
    */
   public List<Country> getAllEnabled()
   {
      return getAllByDisabled(false);
   }
   
   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.CountryProvider#getAllByDisabled(boolean)
    */
   public List<Country> getAllByDisabled(boolean disabled)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
      Root<Country> entity = criteria.from(Country.class);
      
      criteria.where(builder.equal(entity.get("disabled"), disabled));
      TypedQuery<Country> query = entityManager.createQuery(criteria);
      
      List<Country> resultList = query.getResultList();
      return logger.exiting(resultList);
   }
}
