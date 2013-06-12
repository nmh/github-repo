package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.UnitType;
import com.heliocentris.rms.core.util.Version;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface UnitTypeProvider extends EntityProvider<UnitType, Long>
{
   /**
    * Find and return the <code>UnitType</code> specified by moduleId.
    * @param moduleId <code>short</code> value represents <code>moduleId</code>.
    * @return null or the <code>UnitType</code>.
    */
   public UnitType findBy(short moduleId);


   /**
    * Find and return the <code>UnitType</code> specified by moduleId, hwVersion, swVersion and generic.
    * @param moduleId <code>boolean</code> value represents <code>generic</code>. 
    * @param moduleId <code>short</code> value represents <code>moduleId</code>.
    * @param hwVersion <code>Version</code> value represents hardware version. 
    * @param swVersion <code>Version</code> value represents software version. 
    * @return null or the <code>UnitType</code>.
    */
   public UnitType findBy(boolean generic, short moduleId, Version hwVersion, Version swVersion);
   
}
