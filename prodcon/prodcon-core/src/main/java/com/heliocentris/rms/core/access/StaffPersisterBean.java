package com.heliocentris.rms.core.access;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.heliocentris.rms.core.Domain;
import com.heliocentris.rms.core.Staff;
import com.heliocentris.rms.core.User;
import com.heliocentris.rms.core.UserGroup;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="StaffPersister")
public class StaffPersisterBean extends AbstractEntityPersister<Staff, Long> implements StaffPersister
{
   private static final Logger logger = LoggerFactory.getLogger(StaffPersisterBean.class);

   
   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.StaffProvider#findByName(java.lang.Class, java.lang.String)
    */
   public <T extends Staff> T findByName(Class<T> type, String name)
   {
      T result = null;
      if (name != null && !name.isEmpty())
      {
         EntityManager entityManager = getEntityManager();
         CriteriaBuilder builder = entityManager.getCriteriaBuilder();
         CriteriaQuery<T> criteria = builder.createQuery(type);
         Root<T> entity = criteria.from(type);
         criteria.where(builder.equal(entity.get("name"), name));
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
    * @see com.heliocentris.rms.core.access.StaffProvider#getUsers(com.heliocentris.rms.core.UserGroup)
    */
   public Set<User> getUsers(UserGroup userGroup)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<User> criteria = builder.createQuery(User.class);
      Root<User> entity = criteria.from(User.class);
      criteria.where(builder.equal(entity.join("userGroups"), userGroup));
      TypedQuery<User> query = entityManager.createQuery(criteria);
      
      List<User> resultList = query.getResultList();
      return logger.exiting(new LinkedHashSet<User>(resultList));
   }

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.access.StaffProvider#getStaffs(java.lang.Class, com.heliocentris.rms.core.Domain)
    */
   public <T extends Staff> Set<T> getStaffs(Class<T> type, Domain domain)
   {
      EntityManager entityManager = getEntityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<T> criteria = builder.createQuery(type);
      Root<T> entity = criteria.from(type);
      criteria.where(builder.equal(entity.join("domains"), domain));
      TypedQuery<T> query = entityManager.createQuery(criteria);
      
      List<T> resultList = query.getResultList();
      return logger.exiting(new LinkedHashSet<T>(resultList));
   }
   
}
