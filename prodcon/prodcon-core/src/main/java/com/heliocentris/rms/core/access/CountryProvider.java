package com.heliocentris.rms.core.access;

import java.util.List;

import com.heliocentris.rms.core.util.Country;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface CountryProvider extends EntityProvider<Country, Long>
{
   /**
    * Find and return the <code>Country</code> specified by iso for both ISO2 or ISO3.
    * If the parameter iso is null, the result is also null.
    * @param iso <code>String</code> represents ISO2- or ISO3-Code.
    * @return null or the <code>Country</code>.
    */
   public Country findByIso(String iso);

   /**
    * Return all enabled <code>Country</code>.
    * 
    * @return the list of enabled <code>Country</code>.
    */
   public List<Country> getAllEnabled();
   
   /**
    * Return all <code>Country</code> specified by disabled flag.
    * 
    * @param disabled specified if the <code>Country</code> disabled or not.
    * @return the list of <code>Country</code>.
    */
   public List<Country> getAllByDisabled(boolean disabled);
   
}
