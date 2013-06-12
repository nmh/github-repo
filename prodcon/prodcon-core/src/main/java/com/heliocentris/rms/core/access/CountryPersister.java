package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.util.Country;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface CountryPersister extends EntityPersister<Country, Long>, CountryProvider
{

}
