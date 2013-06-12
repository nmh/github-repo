package com.heliocentris.rms.core;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.heliocentris.rms.core.access.StaffProvider;
import com.heliocentris.rms.util.service.Service;



/**
 * <code>UserGroup</code> only groups the <code>User</code> together.
 * @author thien
 *
 */

@Entity
@Table(name=UserGroup.TABLE)
public class UserGroup extends Staff
{
   private static final long serialVersionUID = -8372676680349373039L;
   
   /**
    * Specified the table name of the entity in the backing store.
    */
   public static final String TABLE = "user_group";
   
   /**
    * Retrieve all <code>User</code>(s) belong to the <code>UserGroup</code>.
    * @return the set of <code>User</code>s.
    */
   public Set<User> getUsers()
   {
      return Service.getService(StaffProvider.class).getUsers(this);
   }
   
}
