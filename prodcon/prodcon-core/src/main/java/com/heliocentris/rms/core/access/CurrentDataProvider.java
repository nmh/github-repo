package com.heliocentris.rms.core.access;

import java.util.Date;
import java.util.List;

import com.heliocentris.rms.core.Component;
import com.heliocentris.rms.core.Site;
import com.heliocentris.rms.core.data.CurrentData;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface CurrentDataProvider extends EntityProvider<CurrentData, Long>
{
   /**
    * Return all <code>CurrentData</code>s for the specified <code>Site</code>.
    *  
    * @param site the <code>Site</code>
    * @param maxResults maximal rows to be returned.
    * @return
    */
   List<CurrentData> getAllBy(Site site, int firstResult, int maxResults);

   /**
    * 
    * @param site
    * @param id
    * @param maxResults
    * @return
    */
   List<CurrentData> getAllBy(Site site, int id, int firstResult, int maxResults);

   /**
    * 
    * @param site
    * @param startDate
    * @param endDate
    * @param maxResults
    * @return
    */
   List<CurrentData> getAllBy(Site site, Date startDate, Date endDate, int firstResult, int maxResults);
   
   /**
    * Return all <code>CurrentData</code>s for the specified <code>Component</code>
    *  
    * @param component the <code>Component</code>
    * @param maxResults maximal rows to be returned.
    * @return the list of <code>CurrentData</code>.
    */
   <T extends CurrentData> List<T> getAllBy(Component component, int maxResults);
   
   /**
    * 
    * @param component
    * @param id
    * @param maxResults
    * @return
    */
   List<CurrentData> getAllBy(Component component, int dataTypeId, int maxResults);
 

   /**
    * 
    * @param component
    * @param startDate
    * @param endDate
    * @param maxResults
    * @return
    */
   List<CurrentData> getAllBy(Component component, Date startDate, Date endDate, int maxResults);
   
   /**
    * 
    * @param component
    * @param id
    * @param startDate
    * @param endDate
    * @param maxResults
    * @return
    */
   <T extends CurrentData> List<T> getAllBy(Component component, int dataTypeId, Date startDate, Date endDate, int maxResults);   
   
   /**
    * 
    * @param component
    * @param dataTypeId
    * @param startDate
    * @param endDate
    * @param firstResult
    * @param maxResults
    * @return
    */
   List<CurrentData> getAllBy(Component component, int dataTypeId, Date startDate, Date endDate, int firstResult, int maxResults);   
}
