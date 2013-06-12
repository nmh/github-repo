package com.heliocentris.rms.core.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
/**
 * Absolute coordinate or position of the point at Earth's surface.
 * 
 * @author thien
 *
 */
public class Position implements Serializable
{
   private static final long serialVersionUID = 7759447921689664477L;
   
   /**
    * Column name of the latitude in the database table.
    */
   public static final String LATITUDE ="latitude";
   /**
    * Column name of the longitude in the database table.
    */
   public static final String LONGITUDE ="longitude";   

   @Column(name=LATITUDE, nullable=false)
   private Float latitude = 0.0f;

   @Column(name=LONGITUDE, nullable=false)
   private Float longitude = 0.0f;

   public Position()
   {
   }
   
   public Position(float latitude, float longitude)
   {
      setLatitude(latitude);
      setLongitude(longitude);
   }
   
   /**
    * Latitude is a geographic coordinate that specifies the north-south(Y-Axis) position of a point on the Earth's surface.<br>
    * It is ranging from 0 at the Equator to +90 (North) and -90 (South) at the poles.
    * @return <code>Float</code> value represents the latitude.
    */
   public Float getLatitude()
   {
      return latitude;
   }

   /**
    * Longitude is a geographic coordinate that specifies the east-west(X-Axis) position of a point on the Earth's surface.
    * It is ranging from 0 at the Prime Meridian to +180 eastward and -180 westward.
    * @return <code>Float</code> value represents the longitude. 
    */
   public Float getLongitude()
   {
      return longitude;
   }

   /**
    * Determine if the <code>Position</code> intersect with the specified <code>Area</code>.
    * @param area the specified <code>Area</code>.
    * @return true or false indicate intersect or not.
    */
   public boolean intersect(Area area)
   {
      throw new UnsupportedOperationException("not yet implemented");
   }

   public void setLatitude(Float latitude)
   {
      assert(Math.abs(latitude) <= 90);
      this.latitude = latitude;
   }

   public void setLongitude(Float longitude)
   {
      assert(Math.abs(latitude) <= 180);
      this.longitude = longitude;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder(getClass().getSimpleName());
      builder.append("[latitude=").append(latitude);
      builder.append(", longitude=").append(longitude);      
      return builder.append("]").toString();
   }
   
}
