package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.util.TimeZone;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface TimeZonePersister extends EntityPersister<TimeZone, Long>, TimeZoneProvider
{

}
