package com.heliocentris.rms.core.access;

import javax.ejb.Stateless;

import com.heliocentris.rms.core.data.Event;
import com.heliocentris.rms.util.persistent.AbstractEntityPersister;

@Stateless(name="EventPersister")
public class EventPersisterBean extends AbstractEntityPersister<Event, Long> implements EventPersister
{
   // private static final Logger logger = LoggerFactory.getLogger(CurrentDataPersisterBean.class);

}
