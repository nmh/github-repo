package com.heliocentris.rms.core;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.heliocentris.rms.core.util.Address;

@Entity
public abstract class Staff extends Participant
{
   private static final long serialVersionUID = 1282851348249628567L;

   /**
    * Column name of the member <code>name</code> in the database table.
    */
   public static final String NAME = "name";
   /**
    * Column name of the member <code>description</code> in the database table.
    */
   public static final String DESCRIPTION = "description";
   
   @Column(name=NAME, nullable=false, unique=true)
   private String name;
   
   @Column(name=DESCRIPTION, nullable=true)
   private String description;

   @Embedded
   private Address address = new Address();

   @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
   private Set<Domain> domains;


   /**
    * Return the unique name of the <code>Staff</code> over the one server. The name can not be <code>null</code>.
    * @return the name as <code>String</code>
    */
   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   /**
    * Return the description of the <code>Staff</code>, can be <code>null</code>.
    * @return the description as <code>String</code>
    */
   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   /**
    * Return the <code>Address</code> of the <code>Staff</code>.
    * @return the address.
    */
   public Address getAddress()
   {
      if (address == null)
      {
         synchronized (this)
         {
            if (address == null)
            {
               address = new Address();
            }
         }
         address = new Address();
      }
      return address;
   }

   public void setAddress(Address address)
   {
      this.address = address;
   }

   /**
    * The <code>Staff</code> generally can be assigned to any <code>Domain</code>s.
    * @return the list of assigned <code>Domain</code>s.
    */
   public Set<Domain> getDomains()
   {
      return domains;
   }

   public boolean addDomain(Domain domain)
   {
      return !domains.contains(domain) && domains.add(domain);
   }
   
   public boolean removeDomain(Domain domain)
   {
      return domains.contains(domain) && domains.remove(domain);
   }
   
   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object object)
   {
      if (this == object) return true;
      if (!super.equals(object)) return false;
      if (getClass() != object.getClass()) return false;
      Staff other = (Staff) object;
      if (name == null)
      {
         if (other.name != null) return false;
      } 
      else if (!name.equals(other.name)) return false;
      return true;
   }

   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(super.toString());
      buffer.deleteCharAt(buffer.length() - 1);
      buffer.append(", name=").append(name);      
      buffer.append(", description=").append(description);
      buffer.append(", address=").append(address);
      return buffer.append("]").toString();
   }

   /**
    * Get the top most <code>Network</code>s for the <code>User</code> to choice for login.
    * @return the list of <code>Network</code>s for the <code>User</code> to choice for login. 
    */
   public Set<Network> getLoginNetworks()
   {
      Set<Network> resultList = new LinkedHashSet<Network>();
      if (!isDisabled() && !isDeleting())
      {
         for (Domain domain : getDomains())
         {
            if (!domain.isDisabled() && !domain.isDeleting())
            {
               resultList.add(domain.getNetwork());
            }
         }
      }
      return resultList;
   }
}
