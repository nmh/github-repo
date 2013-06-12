package com.heliocentris.rms.core.access;

import java.util.Date;
import java.util.List;

import com.heliocentris.rms.core.Site;
import com.heliocentris.rms.core.data.CurrentHeader;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface CurrentHeaderProvider extends EntityProvider<CurrentHeader, Long>
{

   /**
    * 
    * @param site
    * @param packageId
    * @param creationDate
    * @return
    */
   CurrentHeader find(Site site, long packageId, Date creationDate);
   
   /**
    * 
    * @param site
    * @param startDate
    * @param endDate
    * @param maxResults
    * @return
    */
   List<CurrentHeader> getAllBy(Site site, Date startDate, Date endDate, int maxResults);   
   
}
