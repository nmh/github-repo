package com.heliocentris.rms.core.access;

import java.util.List;

import com.heliocentris.rms.core.util.Language;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface LanguageProvider extends EntityProvider<Language, Long>
{
   
   /**
    * Find and return the <code>Country</code> specified by iso for both ISO2 or ISO3.
    * If the parameter iso is null, the result is also null.
    * @param iso <code>String</code> represents ISO2- or ISO3-Code.
    * @return null or the <code>Country</code>.
    */
   public Language findByIso(String iso);
   
   /**
    * Return all enabled <code>Language</code>.
    * 
    * @return the list of enabled <code>Language</code>.
    */
   public List<Language> getAllEnabled();
   
   /**
    * Return all <code>Language</code> specified by disabled flag.
    * 
    * @param disabled specified if the <code>Language</code> disabled or not.
    * @return the list of <code>Language</code>.
    */
   public List<Language> getAllByDisabled(boolean disabled);
   
}
