package com.heliocentris.rms.core.access;

import java.util.Date;
import java.util.List;

import com.heliocentris.rms.core.Site;
import com.heliocentris.rms.core.data.HistoryHeader;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface HistoryHeaderProvider extends EntityProvider<HistoryHeader, Long>
{

   /**
    * 
    * @param site
    * @param packageId
    * @param creationDate
    * @return
    */
   HistoryHeader find(Site site, long packageId, Date creationDate);
   
   /**
    * 
    * @param site
    * @param startDate
    * @param endDate
    * @param maxResults
    * @return
    */
   List<HistoryHeader> getAllBy(Site site, Date startDate, Date endDate, int maxResults);   

   /**
    * 
    * @param site
    * @param packageId
    * @param creationDate
    * @return
    */
   public boolean exists(Site site, long packageId, Date creationDate);
   
}
