package com.heliocentris.rms.core.util;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * User specified <code>Location</code>.
 * 
 */
@Embeddable
public class Location implements Serializable
{
   private static final long serialVersionUID = 1653118116345366036L;
   
   /**
    * Column name of the language in the database table.
    */
   public static final String LANGUAGE = "language";
   
   /**
    * Column name of the name in the database table.
    */
   public static final String NAME = "location_name";
   /**
    * Column name of the timeZoneId in the database table.
    */
   public static final String TIME_ZONE_ID = "time_zone_id";

   @Column(name=NAME, nullable=true)
   private String name;
   
   @Column(name=LANGUAGE, length=3, nullable=true)
   private String language;

   @Embedded
   private Position position = new Position();

   private transient Locale locale;
   private transient TimeZone timeZone;
   
   @Column(name=TIME_ZONE_ID, nullable=true)
   private String timeZoneId;

   public Location()
   {
   }
   
   public Location(float latitude, float longitude)
   {
      getPosition().setLatitude(latitude);
      getPosition().setLongitude(longitude);
   }

   public Location(String name)
   {
      setName(name);
   }
   
   public Location(String name, float latitude, float longitude)
   {
      setName(name);
      getPosition().setLatitude(latitude);
      getPosition().setLongitude(longitude);
   }

   /**
    * The ISO2- or ISO3-Code of the <code>Language</code>.
    * @return ISO2-Code or ISO3-Code.
    * @see Language
    */
   public String getLanguage()
   {
      return language;
   }

   /**
    * 
    * @return
    */
   public Locale getLocale()
   {
      return getLocale(Locale.getDefault().getCountry());
   }

   /**
    * 
    * @param country
    * @return
    */
   public Locale getLocale(String country)
   {
      if (locale == null)
      {
         if (language != null && !language.isEmpty())
         {
            locale = new Locale(language, country == null ? Locale.getDefault().getCountry() : country);
         }
      }
      return locale;
   }
   
   /**
    * Very specific name of small <code>Location</code>.
    * <p>Unlike the county or city in <code>Address</code>, the name of the <code>Location</code> specifies significant smaller place:
    * <ul>
    *    <li>Downtown
    *    <li>Southern City
    * </ul>
    *   
    * @return
    */
   public String getName()
   {
      return name;
   }

   /**
    * The position on the Earth's surface, can not be null.
    * @return 
    */
   public Position getPosition()
   {
      return position;
   }

   /**
    * Get the <code>TimeZone</code> accordingly to the <code>timeZoneId</code>.
    * @return
    */
   public TimeZone getTimeZone()
   {
      if (timeZone == null)
      {
         if (timeZoneId != null)
         {
            timeZone = TimeZone.getTimeZone(timeZoneId);
         }
      }
      return timeZone;
   }

   /**
    * The <code>timeZoneId</code> of the <code>Location</code>.
    * 
    * @return
    */
   public String getTimeZoneId()
   {
      return timeZoneId;
   }

   public void setLanguage(String language)
   {
      this.locale = null;
      this.language = language;
   }

   public void setLocale(Locale locale)
   {
      if (locale == null)
      {
         language = null; // or language = Locale.getDefault().getLanguage();
      }
      else
      {
         language = locale.getLanguage();
      }
      this.locale = locale;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public void setPosition(Position position)
   {
      this.position = position;
   }

   public void setTimeZone(TimeZone timeZone)
   {
      if (timeZone == null)
      {
         timeZoneId = null;
      }
      else
      {
         timeZoneId = timeZone.getID();
      }
      this.timeZone = timeZone;
   }

   public void setTimeZoneId(String timeZoneId)
   {
      this.timeZone = null;
      this.timeZoneId = timeZoneId;
   }
   
   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder(getClass().getSimpleName());
      builder.append("[name=").append(name);
      builder.append(", language=").append(language);
      builder.append(", timeZoneId=").append(timeZoneId);
      builder.append(", position=").append(position);      
      return builder.append("]").toString();
   }
   
}
