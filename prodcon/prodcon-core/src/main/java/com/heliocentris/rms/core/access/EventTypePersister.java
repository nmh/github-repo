package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.data.EventType;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface EventTypePersister extends EntityPersister<EventType, Long>, EventTypeProvider
{

}
