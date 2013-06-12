package com.heliocentris.rms.core.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The <code>TimeZone</code>s are system constants. 
 * The <code>TimeZone</code> may be enabled or disabled 
 * but never be deleted once it has been created and stored in the database.  
 * <p>It is intended to use for display and loose coupled over the <code>id</code> and not over the <code>oid</code>.
 *  
 * @author thien
 *
 */

@Entity
@Table(name=TimeZone.TABLE)
public class TimeZone implements Serializable
{
   private static final long serialVersionUID = -7320105239030972073L;
   
   /**
    * Column name of the member <code>disabled</code> in the database table.
    */
   public static final String DISABLED = "disabled";
   /**
    * Column name of the member <code>displayName</code> in the database table.
    */
   public static final String DISPLAY_NAME = "display_name";
   /**
    * Column name of the member <code>id</code> in the database table.
    */
   public static final String ID = "id";
   /**
    * Column name of the member <code>oid</code> in the database table.
    */
   public static final String OID = "oid";
   /**
    * Table name of the entity <code>TimeZone</code> in the database table.
    */
   public static final String TABLE = "time_zone";

   /**
    * Factory static method to create and return all available <code>TimeZone</code>s.
    * <p>Example:
    * <ul> 
    * <li>TimeZone.getTimeZones(Locale.ENGLISH); // get <code>TimeZone</code>s in English
    * <li>TimeZone.getTimeZones(Locale.GERMAN); // get <code>TimeZone</code>s in German
    * </ul>
    * @param base the base <code>Locale</code>, which specifies the language.
    * @return the list of <code>TimeZone</code>s.
    *
    */
   public static List<TimeZone> getTimeZones(Locale base)
   {
      List<TimeZone> timeZones = new ArrayList<TimeZone>();
      String[] timeZoneIds = java.util.TimeZone.getAvailableIDs();

      TimeZone timeZone = null;
      for (int i = 0; i < timeZoneIds.length; i++)
      {
         timeZone = new TimeZone(i + 1, base, timeZoneIds[i]);
         timeZones.add(timeZone);
      }
      return timeZones;
   }
 
   @Column(name=DISABLED, nullable=false)
   private boolean disabled;
   
   @Column(name=DISPLAY_NAME, nullable=false)
   private String displayName;
   
   @Column(name=ID, nullable=false, unique=true)
   private String id;

   @Id
   @Column(name=OID, nullable=false, unique=true)
   private long oid;

   protected TimeZone()
   {
      
   }

   protected TimeZone(long oid, Locale locale, String timeZoneId)
   {
      setOid(oid);
      java.util.TimeZone timeZone = java.util.TimeZone.getTimeZone(timeZoneId); 
      setId(timeZone.getID());
      setDisplayName(timeZone.getDisplayName(locale));
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      TimeZone other = (TimeZone) obj;
      if (oid != other.oid) return false;
      if (id == null)
      {
         if (other.id != null) return false;
      } 
      else if (!id.equals(other.id)) return false;
      return true;
   }

   /**
    * The <code>displayId</code> of the <code>TimeZone</code> is exactly the <code>id</code> with the underscore converted to blank:
    * <ul>
    * <li>"Africa/Dar_es_Salaam" -> "Africa/Dar es Salaam"
    * <li>"Pacific/Pago_Pago" -> "Pacific/Pago Pago"
    * </ul>
    * @return
    */
   public String getDisplayId()
   {
      return getId().replace("_", " ");
   }

   /**
    * The display name depend on the base <code>Locale</code>.
    * @return
    */
   public String getDisplayName()
   {
      return displayName;
   }

   public String getId()
   {
      return id;
   }

   public long getOid()
   {
      return oid;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (oid ^ (oid >>> 32));
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public boolean isEnabled()
   {
      return !disabled;
   }
   
   public boolean isDisabled()
   {
      return disabled;
   }

   public void setDisabled(boolean disabled)
   {
      this.disabled = disabled;
   }

   public void setDisplayName(String displayName)
   {
      this.displayName = displayName;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   protected void setOid(long oid)
   {
      this.oid = oid;
   }
   
   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(getClass().getSimpleName());
      buffer.append("[oid=").append(oid);
      buffer.append(", id=").append(id);
      buffer.append(", displayName=").append(displayName);      
      buffer.append(", disabled=").append(disabled);      
      return buffer.append("]").toString();
   }
   
}
