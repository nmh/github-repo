package com.heliocentris.rms.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name=Unit.TABLE, uniqueConstraints=@UniqueConstraint(columnNames={Unit.SITE, Unit.SERIAL_NUMBER}))
public class Unit extends Component
{
   private static final long serialVersionUID = -6700234286680925072L;

   /**
    * Column name of the member <code>active</code> in the database table.
    */
   public static final String ACTIVE = "active";
   /**
    * Column name of the member <code>active</code> in the database table.
    */
   public static final String UNIT_TYPE = "unit_type_oid";
   
   /**
    * Column name of the member <code>serialNumber</code> in the database table.
    */
   public static final String SERIAL_NUMBER = "serial_number";

   /**
    * Specified the table name of the entity in the backing store.
    */
   public static final String TABLE = "unit";

   @ManyToOne(optional=false)
   @JoinColumn(name=SITE, nullable=false)
   private Site site;
   
   @Column(name=ACTIVE, nullable=false)
   private boolean active;

   @Column(name=SERIAL_NUMBER, nullable=false)
   private String serialNumber;
   
   @ManyToOne(optional=false)
   @JoinColumn(name=UNIT_TYPE, nullable=false)
   private UnitType unitType;
   
   @Override
   public Scope getScope()
   {
      return Scope.Unit;
   }

   public String getSerialNumber()
   {
      return serialNumber;
   }

   public boolean isActive()
   {
      return active;
   }

   public void setActive(boolean active)
   {
      this.active = active;
   }

   public void setSerialNumber(String serialNumber)
   {
      this.serialNumber = serialNumber;
   }

   public Site getSite()
   {
      return site;
   }

   public void setSite(Site site)
   {
      this.site = site;
   }
   
   public UnitType getUnitType()
   {
      return unitType;
   }

   public void setUnitType(UnitType unitType)
   {
      this.unitType = unitType;
   }

   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(super.toString());
      buffer.deleteCharAt(buffer.length() - 1);
      buffer.append(", active=").append(active);      
      buffer.append(", site=").append(site == null ? null : site.getOid());      
      buffer.append(", serialNumber=").append(serialNumber);      
      buffer.append(", unitType=").append(unitType == null ? null : unitType.getOid());
      return buffer.append("]").toString();
   }
   
}
;