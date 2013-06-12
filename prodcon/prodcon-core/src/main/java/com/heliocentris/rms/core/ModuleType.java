package com.heliocentris.rms.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.heliocentris.rms.util.AbstractEntity;

/**
 * 
 * @author thien
 *
 */

@Entity
@Table(name=ModuleType.TABLE)
@SequenceGenerator(name = ModuleType.TABLE, sequenceName = ModuleType.SEQUENCE_NAME, allocationSize = 1, initialValue = 1)

public class ModuleType extends AbstractEntity
{
   private static final long serialVersionUID = -5019392995405452187L;
   /**
    * Column name of the member <code>description</code> in the database table.
    */
   public static final String DESCRIPTION = "description";
   /**
    * Column name of the member <code>moduleId</code> in the database table.
    */
   public static final String MODULE_ID = "module_id";
   /**
    * Column name of the member <code>name</code> in the database table.
    */
   public static final String NAME = "name";

   /**
    * Column name of the member <code>oid</code> in the database table.
    */
   public static final String ORDINAL = "ordinal";
   /**
    * Column name of the member <code>maxUnits</code> in the database table.
    */
   public static final String SINGLE_ACTIVE_UNIT = "single_active_unit";
   
   /**
    * Sequence name of the member <code>ModuleType</code> in the database table.
    */
   public static final String SEQUENCE_NAME = "module_type_oid";
   /**
    * Table name of the member <code>ModuleType</code> in the database table.
    */
   public static final String TABLE = "module_type";
   

  
   @Column(name=DESCRIPTION, nullable=true)
   private String description;
   
   @Column(name=MODULE_ID, nullable=false, unique=true)
   private short moduleId;
   
   @Column(name=NAME, nullable=false)
   private String name;
   
   @Id
   @GeneratedValue(generator=ModuleType.TABLE, strategy=GenerationType.SEQUENCE)
   @Column(name=OID)
   private Long oid;
   
   @Column(name=ORDINAL, nullable=false)
   private int ordinal;

   @Column(name=SINGLE_ACTIVE_UNIT, nullable=false)
   private boolean singleActiveUnit = false;
   
   public ModuleType()
   {
   }
   
   public ModuleType(short moduleId)
   {
      setModuleId(moduleId);
   }

   public ModuleType(short moduleId, String name)
   {
      setName(name);
      setModuleId(moduleId);
   }
   
   public String getDescription()
   {
      return description;
   }

   /**
    * The <code>moduleId</code> is the unique system constant and can not be null.
    *   
    * @return
    */
   public short getModuleId()
   {
      return moduleId;
   }

   public String getName()
   {
      return name;
   }

   public Long getOid()
   {
      return oid;
   }

   public int getOrdinal()
   {
      return ordinal;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public void setModuleId(short moduleId)
   {
      this.moduleId = moduleId;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public void setOid(Long oid)
   {
      this.oid = oid;
   }

   public void setOrdinal(int ordinal)
   {
      this.ordinal = ordinal;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + moduleId;
      result = prime * result + ((oid == null) ? 0 : oid.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object object)
   {
      if (this == object) return true;
      if (object == null) return false;
      if (getClass() != object.getClass()) return false;
      ModuleType other = (ModuleType) object;
      if (moduleId != other.moduleId) return false;
      if (oid == null)
      {
         if (other.oid != null) return false;
      } 
      else if (!oid.equals(other.oid)) return false;
      return true;
   }

   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(super.toString());
      buffer.deleteCharAt(buffer.length() - 1);
      buffer.append(", moduleId=").append(moduleId);      
      buffer.append(", ordinal=").append(ordinal);      
      buffer.append(", name=").append(name);      
      buffer.append(", singleActiveUnit=").append(singleActiveUnit);
      buffer.append(", description=").append(description);      
      return buffer.append("]").toString();
   }
   
}
