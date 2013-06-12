package com.heliocentris.rms.core.access;

import java.util.List;

import com.heliocentris.rms.core.util.TimeZone;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface TimeZoneProvider extends EntityProvider<TimeZone, Long>
{
   
   /**
    * Find <code>TimeZone</code> specified by the id.
    * @param id the unique identifier for the <code>Timezone</code>.
    * @return the the <code>TimeZone</code> or null if not found.
    */
   public TimeZone findById(String id);

   
   /**
    * Return all enabled <code>TimeZone</code>.
    * 
    * @return the list of enabled <code>TimeZone</code>.
    */
   public List<TimeZone> getAllEnabled();
   
   /**
    * Return all <code>TimeZone</code> specified by disabled flag.
    * 
    * @param disabled specified if the <code>TimeZone</code> disabled or not.
    * @return the list of <code>TimeZone</code>.
    */
   public List<TimeZone> getAllByDisabled(boolean disabled);
   
}
