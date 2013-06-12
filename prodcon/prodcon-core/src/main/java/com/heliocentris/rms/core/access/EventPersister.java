package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.data.Event;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface EventPersister extends EntityPersister<Event, Long>, EventProvider
{

}
