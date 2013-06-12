package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.Site;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface SiteProvider extends EntityProvider<Site, Long>
{
   public Site findById(String siteId);
}
