package com.heliocentris.rms.core;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.heliocentris.rms.core.util.Version;
import com.heliocentris.rms.util.AbstractEntity;


/**
 * 
 * @author thien
 *
 */

@Entity
@Table(name=UnitType.TABLE, uniqueConstraints=@UniqueConstraint(columnNames={UnitType.MODULE_TYPE, UnitType.HW_VERSION, UnitType.SW_VERSION, UnitType.GENERIC}))
@SequenceGenerator(name = UnitType.TABLE, sequenceName = UnitType.SEQUENCE_NAME, allocationSize = 1, initialValue = 1)


public class UnitType extends AbstractEntity
{
   private static final long serialVersionUID = 3136870052916781048L;
   
   /**
    * Column name of the member <code>description</code> in the database table.
    */
   public static final String DESCRIPTION = "description";
   /**
    * Column name of the member <code>generic</code> in the database table.
    */
   public static final String GENERIC = "generic";
   /**
    * Column name of the member <code>hwVersion</code> in the database table.
    */
   public static final String HW_VERSION = "hw_version";
   /**
    * Column name of the member <code>swVersion</code> in the database table.
    */
   public static final String SW_VERSION = "sw_version";   
   /**
    * Column name of the member <code>moduleType</code> in the database table.
    */
   public static final String MODULE_TYPE = "module_type_oid";
   
   /**
    * Column name of the member <code>name</code> in the database table.
    */
   public static final String NAME = "name";
   /**
    * Column name of the member <code>ordinal</code> in the database table.
    */
   public static final String ORDINAL = "ordinal";
   
   /**
    * Table name of the member <code>UnitType</code> in the database table.
    */
   public static final String TABLE = "unit_type";
   /**
    * Sequence name of the member <code>UnitType</code> in the database table.
    */
   public static final String SEQUENCE_NAME = "unit_type_oid";

   @Id
   @GeneratedValue(generator=UnitType.TABLE, strategy=GenerationType.SEQUENCE)
   @Column(name=OID)
   private Long oid;
   
   @Column(name=NAME, nullable=false)
   private String name;
   
   @Column(name=DESCRIPTION, nullable=true)
   private String description;

   @Column(name=GENERIC, nullable=false)
   private boolean generic = true;

   @ManyToOne(optional=false)
   @JoinColumn(name=MODULE_TYPE, nullable=false)   
   private ModuleType moduleType;
   
   @Embedded
   @AttributeOverride(name="version", column=@Column(name=HW_VERSION, nullable=true))
   private Version hwVersion;
   
   @Embedded
   @AttributeOverride(name="version", column=@Column(name=SW_VERSION, nullable=true))
   private Version swVersion;

   @Column(name=ORDINAL, nullable=false)
   private int ordinal;

   public UnitType()
   {
      
   }
   
   @Override
   public Long getOid()
   {
      return oid;
   }
   
   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public boolean isGeneric()
   {
      return generic;
   }

   public void setGeneric(boolean generic)
   {
      this.generic = generic;
   }

   public int getOrdinal()
   {
      return ordinal;
   }

   public void setOrdinal(int ordinal)
   {
      this.ordinal = ordinal;
   }

   public Version getHwVersion()
   {
      return hwVersion;
   }

   public void setHwVersion(Version hwVersion)
   {
      this.hwVersion = hwVersion;
   }

   public Version getSwVersion()
   {
      return swVersion;
   }

   public void setSwVersion(Version swVersion)
   {
      this.swVersion = swVersion;
   }
   
   public ModuleType getModuleType()
   {
      return moduleType;
   }
   
   public void setModuleType(ModuleType moduleType)
   {
      this.moduleType = moduleType;
   }

   public short getModuleId()
   {
      return getModuleType().getModuleId();
   }
   
   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(super.toString());
      buffer.deleteCharAt(buffer.length() - 1);
      buffer.append(", name=").append(name);      
      buffer.append(", moduleId=").append(moduleType == null ? null : getModuleId());      
      buffer.append(", description=").append(description);      
      buffer.append(", generic=").append(generic);      
      buffer.append(", ordinal=").append(ordinal);      
      buffer.append(", hwVersion=").append(hwVersion);      
      buffer.append(", swVersion=").append(swVersion);      
      return buffer.append("]").toString();
   }
}
