package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.data.CurrentData;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface CurrentDataPersister extends EntityPersister<CurrentData, Long>, CurrentDataProvider
{

}
