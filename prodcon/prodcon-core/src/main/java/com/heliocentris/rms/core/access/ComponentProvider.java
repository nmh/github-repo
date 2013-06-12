package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.Component;
import com.heliocentris.rms.core.Unit;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface ComponentProvider extends EntityProvider<Component, Long>
{

   Unit findUnitBySerialNumber(String serialNumber);
}
