package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.Network;
import com.heliocentris.rms.core.data.EventType;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface EventTypeProvider extends EntityProvider<EventType, Long>
{
   /**
    * Return the overridden <code>EventType</code> by the specified <code>Network</code> or the base <code>EventType</code>.  
    * @param network the <code>Network</code>, can be null.
    * @param moduleId the module id.
    * @param code the event code.
    * @return the <code>EventType</code>. Null if not found.
    */
   EventType find(Network network, short moduleId, short code);
}
