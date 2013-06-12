package com.heliocentris.rms.core.util;

import java.io.Serializable;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The <code>Address</code>, postal reachable.
 * 
 * @author thien
 */
@Embeddable
public class Address implements Serializable
{
   private static final long serialVersionUID = 7443069508330651313L;
   
   /**
    * Column name of the city in the database table.
    */
   public static final String CITY = "city";
   /**
    * Column name of the country in the database table.
    */
   public static final String COUNTRY = "country";
   /**
    * Column name of the county in the database table.
    */
   public static final String COUNTY = "county";   
   /**
    * Column name of the email in the database table.
    */
   public static final String EMAIL = "email";
   /**
    * Column name of the mobile in the database table.
    */
   public static final String MOBILE = "mobile";
   /**
    * Column name of the street in the database table.
    */
   public static final String STREET = "street";
   /**
    * Column name of the telephone in the database table.
    */
   public static final String TELEPHONE = "telephone";
   /**
    * Column name of the zip code in the database table.
    */
   public static final String ZIP_CODE = "zip_code";   

   
   @Column(name=CITY, nullable=true)
   private String city;

   @Column(name=COUNTRY, length=3, nullable=true)
   private String country;
   
   @Column(name=COUNTY, nullable=true)
   private String county;

   @Column(name=EMAIL, nullable=true)
   private String email;

   @Column(name=MOBILE, nullable=true)
   private String mobile;

   @Column(name=STREET, nullable=true)
   private String street;

   @Column(name=TELEPHONE, nullable=true)
   private String telephone;

   @Column(name=ZIP_CODE, nullable=true)
   private String zipCode;

   
   private transient Locale locale;
   
   /**
    * This is the city you are in.
    * @return
    */
   public String getCity()
   {
      return city;
   }

   /**
    * The ISO2- or ISO3-Code of the <code>Country</code>.
    * @return ISO2-Code or ISO3-Code.
    * @see Country
    */
   public String getCountry()
   {
      return country;
   }

   /**
    * This is the county
    * @return
    */
   public String getCounty()
   {
      return county;
   }

   /**
    * And this is the email
    * @return
    */
   public String getEmail()
   {
      return email;
   }

   /**
    * Of course this is the mobile phone number.
    * 
    * @return
    */
   public String getMobile()
   {
      return mobile;
   }


   /**
    * The street has two part: the street name and the street number.<br>
    * It is responsible to the user to format it right as the format for that is varying too much.
    * <p>Example:
    * <ul>
    *    <li>50 First Avenue.
    *    <li>First Avenue 50, behind the bar.
    * </ul>   
    * @return the street represents the street name and the street number.
    * 
    */
   public String getStreet()
   {
      return street;
   }

   /**
    * This is the telephone number. Must not been set.
    * @return
    */
   public String getTelephone()
   {
      return telephone;
   }

   /**
    * User defined <code>zipCode.</code>
    * @return
    */
   public String getZipCode()
   {
      return zipCode;
   }

   /**
    * Return the <code>Locale</code> using Locale.getDefault.getLanguage() and the <code>country</code>.  
    * @return the <code>Locale</code>.
    */
   public Locale getLocale()
   {
      return getLocale(Locale.getDefault().getLanguage());
   }

   /**
    * Get the <code>Locale</code> represents the specified <code>Language</code> and <code>Country</code>.
    * @param language ISO2- or ISO3-Code for the language.
    * @return
    */
   public Locale getLocale(String language)
   {
      if (locale == null)
      {
         if (country != null && !country.isEmpty())
         {
            locale = new Locale(language == null ? Locale.getDefault().getLanguage() : language, country);
         }
      }
      return locale;
   }
   
   /**
    * 
    * @param locale
    */
   public void setLocale(Locale locale)
   {
      if (locale == null)
      {
         country = null; // or country = Locale.getDefault().getCountry();
      }
      else
      {
         country = locale.getCountry();
      }
      this.locale = locale;
   }
   
   /**
    * Set the city name of the <code>Address</code>.
    * @param city the string represents the name of the city.
    * @see Address#getCity()
    */
   public void setCity(String city)
   {
      this.city = city;
   }

   /**
    * Set the ISO2- or ISO3-Code of the <code>Country</code>.
    * Set the also the transient <code>Locale</code> to null.
    * @param country
    */
   public void setCountry(String country)
   {
      this.locale = null;
      this.country = country;
   }

   public void setCounty(String county)
   {
      this.county = county;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public void setMobile(String mobile)
   {
      this.mobile = mobile;
   }

   public void setStreet(String street)
   {
      this.street = street;
   }

   public void setTelephone(String telephone)
   {
      this.telephone = telephone;
   }

   public void setZipCode(String zipCode)
   {
      this.zipCode = zipCode;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder(getClass().getSimpleName());
      builder.append("[street=").append(street);
      builder.append(", zipCode=").append(zipCode);
      builder.append(", city=").append(city);
      builder.append(", county=").append(county);
      builder.append(", country=").append(country);
      builder.append(", email=").append(email);
      builder.append(", mobile=").append(mobile);
      builder.append(", telephone=").append(telephone);
      return builder.append("]").toString();
   }

}
