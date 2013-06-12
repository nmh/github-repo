package com.heliocentris.rms.core.access;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.util.Language;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="LanguagePersister")
public class LanguagePersisterBean extends AbstractEntityPersister<Language, Long> implements LanguagePersister
{
   private static final Logger logger = LoggerFactory.getLogger(LanguagePersisterBean.class);
   
   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.LanguageProvider#findByIso(java.lang.String)
    */
   public Language findByIso(String iso)
   {
      Language result = null;
      if (iso != null && !iso.isEmpty())
      {
         iso = iso.toLowerCase();
         
         EntityManager entityManager = getEntityManager();
         CriteriaBuilder builder = entityManager.getCriteriaBuilder();
         CriteriaQuery<Language> criteria = builder.createQuery(Language.class);
         Root<Language> entity = criteria.from(Language.class);
         
         criteria.where(builder.or(builder.equal(builder.lower(entity.get("iso2").as(String.class)), iso), 
                                   builder.equal(builder.lower(entity.get("iso3").as(String.class)), iso)));
         
         TypedQuery<Language> query = entityManager.createQuery(criteria);
         
         query.setMaxResults(1);
         List<Language> resultList = query.getResultList();
         if (resultList.size() > 0)
         {
            result = resultList.get(0);
         }
      }
      return logger.exiting(result);
   }

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.LanguageProvider#getAllEnabled()
    */
   public List<Language> getAllEnabled()
   {
      return getAllByDisabled(false);
   }

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.LanguageProvider#getAllByDisabled(boolean)
    */
   public List<Language> getAllByDisabled(boolean disabled)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Language> criteria = builder.createQuery(Language.class);
      Root<Language> entity = criteria.from(Language.class);
      
      criteria.where(builder.equal(entity.get("disabled"), disabled));
      TypedQuery<Language> query = entityManager.createQuery(criteria);
      
      List<Language> resultList = query.getResultList();
      return logger.exiting(resultList);
   }
}
