package com.heliocentris.rms.core;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name=User.TABLE)
public class User extends Staff
{
   public enum Role { ServerAdmin, ServiceAmin, NetworkAdmin, Operator }

   private static final long serialVersionUID = 4751883964750201911L;
   
   /**
    * Specified the table name of the class <code>User</code> in the backing store.
    */
   public static final String TABLE = "user_";
   /**
    * Column name of the member <code>password</code> in the database table.
    */
   public static final String PASSWORD = "password";
   /**
    * Column name of the member <code>role</code> in the database table.
    */
   public static final String ROLE = "role";
   
   /**
    * Specified the table name of the relation between <code>User</code> and <code>UserGroup</code> in the backing store.
    */
   public static final String USER_USER_GROUP = "user_user_group";
   
   /**
    * Specified the join column name of the relation between <code>User</code> and <code>UserGroup</code> in the backing store.
    */
   public static final String USER_OID = "user_oid";

   /**
    * Specified the join column name of the relation between <code>User</code> and <code>UserGroup</code> in the backing store.
    */
   public static final String USER_GROUPS_OID = "user_groups_oid";
   
   @Column(name=PASSWORD, nullable=true)
   private String password;
   
   @Enumerated(EnumType.STRING)
   @Column(name=ROLE, nullable=false)
   private Role role = Role.Operator;
   
   @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
   @JoinTable(name=USER_USER_GROUP, joinColumns=@JoinColumn(name=USER_OID), inverseJoinColumns=@JoinColumn(name=USER_GROUPS_OID))   
   private Set<UserGroup> userGroups;

   /**
    * The <code>Role</code> of the <code>User</code>.
    * @return
    */
   public Role getRole()
   {
      return role;
   }

   public void setRole(Role role)
   {
      this.role = role;
   }

   /**
    * Retrieve all <code>UserGroup</code>(s) the <code>User</code> belong to.
    * @return the set of <code>UserGroup</code>s.
    */
   public Set<UserGroup> getUserGroups()
   {
      return userGroups;
   }

   /**
    * Add the specified <code>UserGroup</code> to the <code>User</code>.
    * @param userGroup the <code>UserGroup</code> to add.
    * @return
    */
   public boolean addUserGroup(UserGroup userGroup)
   {
      return !userGroups.contains(userGroup) && userGroups.add(userGroup);
   }
   
   /**
    * Remove the specified <code>UserGroup</code> to the <code>User</code>.
    * @param userGroup the <code>UserGroup</code> to add.
    * @return
    */
   public boolean removeUserGroup(UserGroup userGroup)
   {
      return userGroups.contains(userGroup) && userGroups.remove(userGroup);
   }

   
   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(super.toString());
      buffer.deleteCharAt(buffer.length() - 1);
      buffer.append(", role=").append(role);      
      buffer.append(", password=").append(password);
      return buffer.append("]").toString();
   }
   
   /**
    * 
    */
   public Set<Network> getLoginNetworks()
   {
      Set<Network> resultList = super.getLoginNetworks();
      for (UserGroup userGroup : getUserGroups())
      {
         if (!userGroup.isDisabled() && !userGroup.isDeleting())
         {
            resultList.addAll(userGroup.getLoginNetworks());
         }
      }
      return resultList;
   }
}
