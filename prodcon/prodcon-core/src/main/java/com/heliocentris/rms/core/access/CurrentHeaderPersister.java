package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.data.CurrentHeader;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface CurrentHeaderPersister extends EntityPersister<CurrentHeader, Long>, CurrentHeaderProvider
{

   /**
    *  
    * @param currentHeader
    * @param history if true, insert the <code>CurrentHeader</code> also insert into the history.
    * @return
    */
   CurrentHeader insert(CurrentHeader currentHeader, boolean history);   
}
