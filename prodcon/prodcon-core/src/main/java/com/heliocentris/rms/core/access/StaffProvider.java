package com.heliocentris.rms.core.access;

import java.util.Set;

import com.heliocentris.rms.core.Domain;
import com.heliocentris.rms.core.Staff;
import com.heliocentris.rms.core.User;
import com.heliocentris.rms.core.UserGroup;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface StaffProvider extends EntityProvider<Staff, Long>
{
   /**
    * Find the <code>Staff</code> by given type and name.
    * @param type the class of the <code>Staff</code>.
    * @param name the <code>String</code> represents the name.
    * @return the <code>Staff</code> specified by name or null.
    */
   public <T extends Staff> T findByName(Class<T> type, String name);

   /**
    * Return the <code>Set</code> of <code>User</code>s belong to the given <code>UserGroup</code>.
    * @param userGroup
    * @return
    */
   public Set<User> getUsers(UserGroup userGroup);


   /**
    * Return the <code>Set</code> of <code>Staff</code>s assigned to the given <code>Domain</code>.
    * @param type
    * @param domain
    * @return
    */
   public <T extends Staff> Set<T> getStaffs(Class<T> type, Domain domain);
   
}
