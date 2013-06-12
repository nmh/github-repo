package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.Site;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface SitePersister extends EntityPersister<Site, Long>, SiteProvider
{

}
