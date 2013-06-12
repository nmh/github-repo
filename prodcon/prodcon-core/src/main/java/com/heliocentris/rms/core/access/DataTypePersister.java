package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.data.DataType;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface DataTypePersister extends EntityPersister<DataType, Long>, DataTypeProvider
{

}
