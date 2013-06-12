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
 * The <code>Country</code> is system constant. 
 * The <code>Country</code> may be enabled or disabled 
 * but never be deleted once it has been created and stored in the database.
 * <p>It is intended to use for display and loose coupled over the <code>iso2</code> or <code>iso3</code> and not over the <code>oid</code>. * 
 * <p>To create the default list of <code>Country</code> the static method is used. For examples:
 * <ul>
 *    <li>List{@literal<}Country> countries = Country.getCountries(Locale.CHINESE);
 *    <li>List{@literal<}Country> countries = Country.getCountries(Locale.ENGLISH);
 *    <li>List{@literal<}Country> countries = Country.getCountries(Locale.FRENCH);
 *    <li>List{@literal<}Country> countries = Country.getCountries(Locale.GERMAN);
 *    <li>List{@literal<}Country> countries = Country.getCountries(Locale.ITALIAN);
 *    <li>List{@literal<}Country> countries = Country.getCountries(Locale.JAPANESE);
 *    <li>List{@literal<}Country> countries = Country.getCountries(new Locale("ar", "")); // arabic    
 *    <li>List{@literal<}Country> countries = Country.getCountries(new Locale("vi", "")); // vietnamese
 *    <li>List{@literal<}Country> countries = Country.getCountries(new Locale("", "TN")); // Tunisia
 * </ul>
 *   
 * @author thien
 *
 */

@Entity
@Table(name=Country.TABLE)
public class Country implements Serializable
{
   private static final long serialVersionUID = -6609397558094720099L;

   /**
    * Column name of the disabled in the database table.
    */
   public static final String DISABLED = "disabled";
   /**
    * Column name of the iso2 in the database table.
    */
   public static final String ISO2 = "iso2";
   /**
    * Column name of the iso3 in the database table.
    */
   public static final String ISO3 = "iso3";
   /**
    * Column name of the name in the database table.
    */
   public static final String NAME = "name";
   /**
    * Column name of the oid in the database table.
    */
   public static final String OID = "oid";
   /**
    * Table name of the <code>Country</code> in the database.
    */
   public static final String TABLE = "country";   

   @Id
   @Column(name=OID, nullable=false)
   private long oid;
   
   @Column(name=DISABLED, nullable=false)
   private boolean disabled;

   @Column(name=ISO2, length=2, nullable=false, unique=true)
   private String iso2;

   @Column(name=ISO3, length=3, nullable=false, unique=true)
   private String iso3;

   @Column(name=NAME, nullable=false, unique=false)
   private String name;

   protected Country()
   {
   }

   protected Country(Locale base, Locale locale)
   {
      setName(locale.getDisplayCountry(base));
      setIso2(locale.getCountry());
      setIso3(locale.getISO3Country());
   }
   
   public long getOid()
   {
      return oid;
   }

   protected void setOid(long oid)
   {
      this.oid = oid;
   }

   public String getIso2()
   {
      return iso2;
   }

   public String getIso3()
   {
      return iso3;
   }

   public String getName()
   {
      return name;
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

   public void setIso2(String iso2)
   {
      this.iso2 = iso2;
   }

   public void setIso3(String iso3)
   {
      this.iso3 = iso3;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((iso2 == null) ? 0 : iso2.hashCode());
      result = prime * result + ((iso3 == null) ? 0 : iso3.hashCode());
      result = prime * result + (int) (oid ^ (oid >>> 32));
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Country other = (Country) obj;
      if (iso2 == null)
      {
         if (other.iso2 != null) return false;
      } 
      else if (!iso2.equals(other.iso2)) return false;
      if (iso3 == null)
      {
         if (other.iso3 != null) return false;
      } 
      else if (!iso3.equals(other.iso3)) return false;
      if (oid != other.oid) return false;
      return true;
   }

   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(getClass().getSimpleName());
      buffer.append("[oid=").append(oid);
      buffer.append(", iso2=").append(iso2);
      buffer.append(", iso3=").append(iso3);      
      buffer.append(", name=").append(name);      
      buffer.append(", disabled=").append(disabled);      
      return buffer.append("]").toString();
   }
   
   
   /**
    * Factory static method to create and return all <code>Countries</code>.
    * <p>Example:
    * <ul> 
    * <li>Country.getCountries(Locale.ENGLISH); // get all countries in English
    * <li>Country.getCountries(Locale.GERMAN); // get all countries in German
    * </ul>
    * 
    * @param base the base <code>Locale</code>, which specifies the language.
    * @return the list of unique <code>Countries</code>
    */
   public static List<Country> getCountries(Locale base)
   {
      String[] isos = Locale.getISOCountries();
      List<Country> countries = new ArrayList<Country>();
      
      Locale locale = null;
      Country country = null;
      for (int i = 0; i < isos.length; i++)
      {
         locale = new Locale("", isos[i]);
         country = new Country(base, locale);
         if (!countries.contains(country)) // check for uniqueness
         {
            countries.add(country);
         }
      }
      
      // set the oid statically
      for (int i = 0; i < countries.size(); i++)
      {
         countries.get(i).setOid(i + 1);
      }
      return countries;
   }
   
   /**
    * Short method to test. 
    * @param args
    */
   public static void main(String[] args)
   {
      Locale locale = null;
      for (String string : Locale.getISOLanguages())
      {
         locale = new Locale(string, "");
         System.out.println(locale.getDisplayLanguage());
         for (Country country : Country.getCountries(locale))
         {
            System.out.println(country);
         }
      }
   }
   
}
