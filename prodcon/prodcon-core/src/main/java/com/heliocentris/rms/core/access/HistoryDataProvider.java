package com.heliocentris.rms.core.access;

import java.util.Date;
import java.util.List;

import com.heliocentris.rms.core.Component;
import com.heliocentris.rms.core.Site;
import com.heliocentris.rms.core.data.HistoryData;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface HistoryDataProvider extends EntityProvider<HistoryData, Long>
{
   
   /**
    * 
    * @param component
    * @param id
    * @param start
    * @param end
    * @return
    */
   public List<HistoryData> getAllBy(Component component, int dataTypeId, Date start, Date end);   

   
   /**
    * 
    * @param site
    * @param startDate
    * @param endDate
    * @param maxResults
    * @return
    */
   List<HistoryData> getAllBy(Site site, Date startDate, Date endDate, int firstResult, int maxResults);
   

   /**
    * 
    * @param site
    * @param id
    * @param startDate
    * @param endDate
    * @param maxResults
    * @return
    */
   List<HistoryData> getAllBy(Site site, int dataTypeId, Date startDate, Date endDate, int firstResult, int maxResults);
   
   
   /**
    * 
    * @param component
    * @param startDate
    * @param endDate
    * @param maxResults
    * @return
    */
   List<HistoryData> getAllBy(Component component, Date startDate, Date endDate, int firstResult, int maxResults);


   /**
    * 
    * @param component
    * @param id
    * @param startDate
    * @param endDate
    * @param maxResults
    * @return
    */
   List<HistoryData> getAllBy(Component component, int dataTypeId, Date startDate, Date endDate, int firstResult, int maxResults);
   

}
