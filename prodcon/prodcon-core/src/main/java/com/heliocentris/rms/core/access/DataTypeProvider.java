package com.heliocentris.rms.core.access;

import java.util.List;
import java.util.Set;

import com.heliocentris.rms.core.data.DataType;
import com.heliocentris.rms.core.util.Version;
import com.heliocentris.rms.util.persistent.EntityProvider;

public interface DataTypeProvider extends EntityProvider<DataType, Long>
{
   
   /**
    * Return the <code>DataType</code>s specified by emSwVersion.
    * @param emSwVersion <code>Version</code> represents Energy Manager Software Version.
    * @return list of <code>DataType</code>.
    */
   public List<DataType> getAll(Version emSwVersion);
   
   /**
    * Find and return the <code>DataType</code> specified by emSwVersion, moduleId and id.
    * @param emSwVersion <code>Version</code> represents Energy Manager Software Version.
    * @return null or the <code>DataType</code>.
    */
   public DataType findBy(Version emSwVersion, short moduleId, int id);


   /**
    * Return the set of Energy Manager Software <code>Version</code>s distinctly.
    * @return set of Energy Manager Software <code>Version</code>.
    */
   public Set<Version> getAllEmSwVersion();

   /**
    * Return the set of <code>moduleId</code> from <code>DataType</code> distinctly.
    * @param emSwVersion Energy Manager Software <code>Version</code>.
    * @return set of <code>moduleId</code>.
    */
   public List<Short> getAllModuleIdBy(Version emSwVersion);
   
}
