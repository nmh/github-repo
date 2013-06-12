package com.heliocentris.rms.core;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name=Module.TABLE, uniqueConstraints=@UniqueConstraint(columnNames={Module.SITE, Module.MODULE_TYPE}))
public class Module extends Component
{
   private static final long serialVersionUID = -6700234286680925072L;
   
   /**
    * Specified the table name of the entity in the backing store.
    */
   public static final String TABLE = "module";
   /**
    * Column name of the <code>moduleType</code> member in the backing store.
    */
   public static final String MODULE_TYPE = "module_type_oid";
   
   @ManyToOne(optional=false)
   @JoinColumn(name=SITE, nullable=false)
   private Site site;

   @ManyToOne(optional=false)
   @JoinColumn(name=MODULE_TYPE, nullable=false)
   private ModuleType moduleType;
   
   @Override
   public Scope getScope()
   {
      return Scope.Module;
   }

   public Site getSite()
   {
      return site;
   }

   public void setSite(Site site)
   {
      this.site = site;
   }

   public ModuleType getModuleType()
   {
      return moduleType;
   }

   public void setModuleType(ModuleType moduleType)
   {
      this.moduleType = moduleType;
   }
   
   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(super.toString());
      buffer.deleteCharAt(buffer.length() - 1);
      buffer.append(", site=").append(site == null ? null : site.getOid());      
      buffer.append(", moduleType=").append(moduleType == null ? null : moduleType.getOid());
      return buffer.append("]").toString();
   }
   
}
