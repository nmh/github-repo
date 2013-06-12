package com.heliocentris.rms.core;

import java.util.Locale;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.heliocentris.rms.core.access.StaffProvider;
import com.heliocentris.rms.core.data.Header;
import com.heliocentris.rms.core.util.Address;
import com.heliocentris.rms.core.util.Location;
import com.heliocentris.rms.util.service.Service;




@Entity
public abstract class Domain extends Participant
{
   private static final long serialVersionUID = -6485534161304377220L;

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

   @Embedded
   private Location location = new Location();

   /**
    * Return top most <code>Network</code> from the <code>Domain</code>.
    * @return
    */
   public abstract Network getNetwork();
   
   /**
    * Return all the <code>Site</code>s belong to the <code>Domain</code>.
    * @return
    */
   public abstract Set<Site> getSites();
   
   /**
    * Address of the <code>Domain</code>, can not be <code>null</code>.
    * @return
    */
   public Address getAddress()
   {
      if (address == null)
      {
         synchronized(this)
         {
            if (address == null)
            {
               address = new Address();
            }
         }
      }
      return address;
   }

   public void setAddress(Address address)
   {
      if (this.address != address)
      {
         synchronized (this)
         {
            if (this.address != address)
            {
               this.address = address;
            }
         }
      }
   }

   /**
    * Location of the <code>Domain</code>, can not be <code>null</code>.
    * @return
    */
   public Location getLocation()
   {
      if (location == null)
      {
         synchronized(this)
         {
            if (location == null)
            {
               location = new Location();
            }
         }
      }
      return location;
   }

   public void setLocation(Location location)
   {
      if (this.location != location)
      {
         synchronized (this)
         {
            if (this.location != location)
            {
               this.location = location;
            }
         }
      }
   }

   public Set<Staff> getStaffs()
   {
      return Service.getService(StaffProvider.class).getStaffs(Staff.class, this);
   }
   
   public Locale getLocale()
   {
      return getLocation().getLocale();
   }
   
   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
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
      Domain other = (Domain) object;
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
      buffer.append(", address=").append(address);      
      buffer.append(", location=").append(location);      
      buffer.append(", locale=").append(getLocale());      
      return buffer.append("]").toString();
   }
 
   public void process(Header header)
   {
      throw new UnsupportedOperationException("not yet implemented");      
   }
   
}
