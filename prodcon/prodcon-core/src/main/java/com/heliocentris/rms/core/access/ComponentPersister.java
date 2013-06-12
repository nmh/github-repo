package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.Component;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface ComponentPersister extends EntityPersister<Component, Long>, ComponentProvider
{

}
