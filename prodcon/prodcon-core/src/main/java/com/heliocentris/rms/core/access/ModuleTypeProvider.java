package com.heliocentris.rms.core.access;

import com.heliocentris.rms.core.ModuleType;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface ModuleTypeProvider extends EntityProvider<ModuleType, Long>
{
   /**
    * Find and return the <code>ModuleType</code> specified by moduleId.
    * @param moduleId <code>short</code> value represents <code>moduleId</code>.
    * @return null or the <code>ModuleType</code>.
    */
   public ModuleType findBy(short moduleId);


}
