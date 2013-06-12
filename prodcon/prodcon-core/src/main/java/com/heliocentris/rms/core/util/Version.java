package com.heliocentris.rms.core.util;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Example: 1.2.4-telesite
 * @author thien
 *
 */
@Embeddable
public class Version implements Serializable
{
   private static final long serialVersionUID = -7699649480765290300L;

   public static Version parse(String string)
   {
      return new Version(string);
   }

   private transient int major;
   
   private transient int minor;

   private transient int patch;

   private transient String variant;

   
   private String version;

   public Version()
   {
      setVersion("1.0.0-variant");
   }

   public Version(String version)
   {
      setVersion(version);
   }
   
   public int getMajor()
   {
      return major;
   }

   public int getMinor()
   {
      return minor;
   }

   public int getPatch()
   {
      return patch;
   }

   public String getVariant()
   {
      return variant;
   }

   public String getVersion()
   {
      return version;
   }

   public void setMajor(int major)
   {
      this.major = major;
   }

   public void setMinor(int minor)
   {
      this.minor = minor;
   }

   public void setPatch(int patch)
   {
      this.patch = patch;
   }

   public void setVariant(String variant)
   {
      this.variant = variant;
   }

   public void setVersion(String version)
   {
      this.version = version;
   }
   
   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder(getClass().getSimpleName());
      builder.append("[version=").append(version);
      return builder.append("]").toString();
   }
   
}
