package com.heliocentris.rms.core.access;

import java.util.List;

import com.heliocentris.rms.core.Domain;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface DomainProvider extends EntityProvider<Domain, Long>
{
   /**
    * Find and return the <code>Domain</code> specified by the unique name.
    * If the parameter name is null, the result is also null.
    * @param name the domain name as <code>String</code>. 
    * @return null or the <code>Domain</code>.
    */
   public Domain findByName(String name);

   /**
    * Find and return the <code>Domain</code> specified by the unique name.
    * If the parameter name is null, the result is also null.
    * @param type the type of the <code>Domain</code> (Network, Region, Subregion).
    * @param name the domain name as <code>String</code>.
    * @return null or the <code>Domain</code>.
    */
   public <T extends Domain> T findByName(Class<T> type, String name);

   /**
    * Return all enabled <code>Domain</code>.
    * 
    * @return the list of enabled <code>Domain</code>.
    */
   public List<Domain> getAllEnabled();
   
   /**
    * Return all <code>Domain</code> specified by disabled flag.
    * 
    * @param disabled specified if the <code>Domain</code> disabled or not.
    * @return the list of <code>Domain</code>.
    */
   public <T extends Domain> List<T> getAllByDisabled(Class<T> type, boolean disabled);

   
}
