package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.data.HistoryData;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface HistoryDataPersister extends EntityPersister<HistoryData, Long>, HistoryDataProvider
{

}
