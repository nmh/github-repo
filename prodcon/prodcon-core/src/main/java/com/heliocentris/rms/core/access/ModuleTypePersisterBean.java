package com.heliocentris.rms.core.access;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.ModuleType;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="ModuleTypePersister")
public class ModuleTypePersisterBean extends AbstractEntityPersister<ModuleType, Long> implements ModuleTypePersister
{
   private static final Logger logger = LoggerFactory.getLogger(ModuleTypePersisterBean.class);

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.ModuleTypeProvider#findBy(short)
    */
   public ModuleType findBy(short moduleId)
   {
      ModuleType result = null;
      
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<ModuleType> criteria = builder.createQuery(ModuleType.class);
      Root<ModuleType> entity = criteria.from(ModuleType.class);
      criteria.where(builder.equal(entity.get("moduleId"), moduleId));
      TypedQuery<ModuleType> query = entityManager.createQuery(criteria);
      
      query.setMaxResults(1);
      List<ModuleType> resultList = query.getResultList();
      if (resultList.size() > 0)
      {
         result = resultList.get(0);
      }
      return logger.exiting(result);
   }
   
}
