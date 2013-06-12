package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.data.HistoryHeader;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface HistoryHeaderPersister extends EntityPersister<HistoryHeader, Long>, HistoryHeaderProvider
{

}
