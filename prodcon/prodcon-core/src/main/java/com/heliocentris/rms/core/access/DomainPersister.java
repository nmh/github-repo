package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.Domain;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface DomainPersister extends EntityPersister<Domain, Long>, DomainProvider
{

}
