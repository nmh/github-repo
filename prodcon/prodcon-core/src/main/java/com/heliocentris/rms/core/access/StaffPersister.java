package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.Staff;
import com.heliocentris.rms.util.persistent.EntityPersister;

public interface StaffPersister extends StaffProvider, EntityPersister<Staff, Long>
{

}
