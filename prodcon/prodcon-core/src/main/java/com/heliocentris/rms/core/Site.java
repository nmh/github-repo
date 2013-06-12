package com.heliocentris.rms.core;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.heliocentris.rms.core.data.Header;
import com.heliocentris.rms.core.util.Location;
import com.heliocentris.rms.core.util.Version;

@Entity
@Table(name=Site.TABLE)
/**
 * 
 * @author thien
 *
 */
public class Site extends Participant
{
   /**
    * 
    * @author thien
    *
    */
   public enum Severity
   {
      Error, Info, Warning
   }
   /**
    * Column name of the member <code>alarmInfo</code> in the backing store.
    */
   public static final String ALARM_INFO = "alarm_info";
   /**
    * Column name of the member <code>batteryTemperature</code> in the backing store.
    */
   public static final String BATTERY_TEMPERATURE = "battery_temperature";
   /**
    * Column name of the member <code>description</code> in the backing store.
    */
   public static final String DESCRIPTION = "description";
   
   /**
    * Column length of the member <code>emSwVersion</code> in the backing store.
    */
   public static final String EM_SW_VERSION = "em_sw_version";
   /**
    * Column name of the member <code>environmentTemperature</code> in the backing store.
    */
   public static final String ENVIRONMENT_TEMPERATURE = "environment_temperature";
   /**
    * Column name of the member <code>fuelLevel</code> in the backing store.
    */
   public static final String FUEL_LEVEL = "fuel_level";
   
   /**
    * Column name of the member <code>fuelPercent</code> in the backing store.
    */
   public static final String FUEL_PERCENT = "fuel_percent";
   
   /**
    * Column name of the member <code>id</code> in the backing store.
    */
   public static final String ID = "id";
   /**
    * Column length of the member <code>id</code> in the backing store.
    */
   public static final int ID_LENGTH = 30;   
   /**
    * Column length of the member <code>installDate</code> in the backing store.
    */
   public static final String INSTALL_DATE = "install_date";
   
   /**
    * Column length of the member <code>lastDataDate</code> in the backing store.
    */
   public static final String LAST_DATA_DATE = "last_data_date";
   /**
    * Column length of the member <code>lastEventDate</code> in the backing store.
    */
   public static final String LAST_EVENT_DATE = "last_event_date";
   
   /**
    * Column length of the member <code>lastRemoteChangedDate</code> in the backing store.
    */
   public static final String LAST_REMOTE_CHANGED_DATE = "last_remote_changed_date";
   /**
    * Column length of the member <code>lastRemoteTranferDate</code> in the backing store.
    */
   public static final String LAST_REMOTE_TRANSFER_DATE = "last_remote_transfer_date";
   /**
    * Column length of the member <code>name</code> in the backing store.
    */
   public static final String NAME = "name";
   
   /**
    * Column length of the member <code>network</code> in the backing store.
    */
   public static final String NETWORK = "network_oid";
   /**
    * Column length of the member <code>nextMaintenanceDate</code> in the backing store.
    */
   public static final String NEXT_MAINTENANCE_DATE = "next_maintenance_date";
   /**
    * Column length of the member <code>operationMode</code> in the backing store.
    */
   public static final String OPERATION_MODE = "operation_mode";
   /**
    * Column length of the member <code>predictedRefillDate</code> in the backing store.
    */
   public static final String PREDICTED_REFILL_DATE = "predicted_refill_date";
   /**
    * Column length of the member <code>region</code> in the backing store.
    */
   public static final String REGION = "region_oid";
   /**
    * Column name of the member <code>segment</code> in the backing store.
    */
   public static final String SEGMENT = "segment";
   private static final long serialVersionUID = 5491211136605285799L;
   /**
    * Column length of the member <code>severity</code> in the backing store.
    */
   public static final String SEVERITY = "severity";
   
   
   /**
    * Column length of the member <code>subregion</code> in the backing store.
    */
   public static final String SUBREGION = "subregion_oid";
   
   /**
    * Specified the table name of the entity in the backing store.
    */
   public static final String TABLE = "site";

   @Column(name=ALARM_INFO, nullable=true)
   private String alarmInfo;

   @Column(name=BATTERY_TEMPERATURE, nullable=true)
   private Float batteryTemperature;

   @Column(name=DESCRIPTION, nullable=true)
   private String description;

   @Embedded
   @AttributeOverride(name="version", column=@Column(name=EM_SW_VERSION, nullable=true))
   private Version emSwVersion;

   @Column(name=ENVIRONMENT_TEMPERATURE, nullable=true)
   private Float environmentTemperature;

   @Column(name=FUEL_LEVEL, nullable=true)
   private Float fuelLevel;
   
   @Column(name=FUEL_PERCENT, nullable=true)
   private Float fuelPercent;

   @Column(name=ID, length=ID_LENGTH, nullable=false, unique=true)
   private String id;

   @Temporal(TemporalType.DATE)
   @Column(name=INSTALL_DATE, nullable=true)
   private Date installDate;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name=LAST_DATA_DATE, nullable=true, columnDefinition=DATETIME_COLUMN_DEFINITION)
   private Date lastDataDate;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name=LAST_EVENT_DATE, nullable=true, columnDefinition=DATETIME_COLUMN_DEFINITION)
   private Date lastEventDate;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name=LAST_REMOTE_CHANGED_DATE, nullable=true, columnDefinition=DATETIME_COLUMN_DEFINITION)
   private Date lastRemoteChangedDate;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name=LAST_REMOTE_TRANSFER_DATE, nullable=true, columnDefinition=DATETIME_COLUMN_DEFINITION)
   private Date lastRemoteTranferDate;

   @Embedded
   private Location location = new Location();

   @OneToMany(mappedBy="site", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
   private Set<Module> modules = new HashSet<Module>();

   @Column(name=NAME, nullable=true, unique=true)
   private String name;

   @ManyToOne(optional=true, fetch=FetchType.LAZY)
   @JoinColumn(name=NETWORK)
   private Network network;

   @Temporal(TemporalType.DATE)
   @Column(name=NEXT_MAINTENANCE_DATE, nullable=true)
   private Date nextMaintenanceDate;

   @Column(name=OPERATION_MODE, nullable=true)
   private String operationMode;

   @Temporal(TemporalType.DATE)
   @Column(name=PREDICTED_REFILL_DATE, nullable=true)
   private Date predictedRefillDate;

   @ManyToOne(optional=true, fetch=FetchType.LAZY)
   @JoinColumn(name=REGION)
   private Region region;

   @Column(name=SEGMENT, nullable=false)
   private byte segment = 1;

   @Column(name=SEVERITY, nullable=false)
   private Severity severity = Severity.Info;

   @ManyToOne(optional=true, fetch=FetchType.LAZY)
   @JoinColumn(name=SUBREGION)
   private Subregion subregion;
   
   
   @OneToMany(mappedBy="site", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
   private Set<Unit> units = new HashSet<Unit>();
   
   
   public boolean addModule(Module module)
   {
      module.setSite(this);
      return modules.add(module);
   }

   public boolean addUnit(Unit unit)
   {
      unit.setSite(this);
      return units.add(unit);
   }

   @Override
   public boolean equals(Object object)
   {
      if (this == object) return true;
      if (!super.equals(object)) return false;
      if (getClass() != object.getClass()) return false;
      Site other = (Site) object;
      if (id == null)
      {
         if (other.id != null) return false;
      } 
      else if (!id.equals(other.id)) return false;
      return true;
   }

   public String getAlarmInfo()
   {
      return alarmInfo;
   }

   public Float getBatteryTemperature()
   {
      return batteryTemperature;
   }

   public String getDescription()
   {
      return description;
   }

   public Version getEmSwVersion()
   {
      return emSwVersion;
   }

   public Float getEnvironmentTemperature()
   {
      return environmentTemperature;
   }

   public Float getFuelLevel()
   {
      return fuelLevel;
   }

   public Float getFuelPercent()
   {
      return fuelPercent;
   }

   /**
    * The identifier of the <code>Site</code> within the whole server instance. Also known as Remote Access Site ID.
    * @return the <code>String</code> represents the <code>Site</code> identifier.
    */
   public String getId()
   {
      return id;
   }

   public Date getInstallDate()
   {
      return installDate;
   }

   public Date getLastDataDate()
   {
      return lastDataDate;
   }

   public Date getLastEventDate()
   {
      return lastEventDate;
   }

   public Date getLastRemoteChangedDate()
   {
      return lastRemoteChangedDate;
   }

   public Date getLastRemoteTranferDate()
   {
      return lastRemoteTranferDate;
   }

   public Location getLocation()
   {
      if (location == null)
      {
         synchronized (this)
         {
            if (location == null)
            {
               location = new Location();
            }            
         }
      }
      return location;
   }

   public Set<Module> getModules()
   {
      return modules;
   }

   public String getName()
   {
      return name;
   }

   public Network getNetwork()
   {
      return network;
   }

   public Date getNextMaintenanceDate()
   {
      return nextMaintenanceDate;
   }

   public String getOperationMode()
   {
      return operationMode;
   }

   public Date getPredictedRefillDate()
   {
      return predictedRefillDate;
   }

   public Region getRegion()
   {
      return region;
   }

   /**
    * Segment of the storage to store the data.
    * @return
    */
   public byte getSegment()
   {
      return segment;
   }

   public Severity getSeverity()
   {
      return severity;
   }

   public Subregion getSubregion()
   {
      return subregion;
   }

   public Set<Unit> getUnits()
   {
      return units;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public void process(Header header)
   {
      if (header.isLifeSignal())
      {
         setLastDataDate(new Date());
      }
      
      throw new UnsupportedOperationException("not yet implemented");      
   }

   public boolean removeModule(Module module)
   {
      module.setSite(null);
      return modules.add(module);
   }

   public boolean removeUnit(Unit unit)
   {
      unit.setSite(null);
      return units.add(unit);
   }

   public void setAlarmInfo(String alarmInfo)
   {
      this.alarmInfo = alarmInfo;
   }

   public void setBatteryTemperature(Float batteryTemperature)
   {
      this.batteryTemperature = batteryTemperature;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public void setEmSwVersion(Version emSwVersion)
   {
      this.emSwVersion = emSwVersion;
   }

   public void setEnvironmentTemperature(Float environmentTemperature)
   {
      this.environmentTemperature = environmentTemperature;
   }

   public void setFuelLevel(Float fuelLevel)
   {
      this.fuelLevel = fuelLevel;
   }

   public void setFuelPercent(Float fuelPercent)
   {
      this.fuelPercent = fuelPercent;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public void setInstallDate(Date installDate)
   {
      this.installDate = installDate;
   }

   public void setLastDataDate(Date lastDataDate)
   {
      this.lastDataDate = lastDataDate;
   }

   public void setLastEventDate(Date lastEventDate)
   {
      this.lastEventDate = lastEventDate;
   }

   public void setLastRemoteChangedDate(Date lastRemoteChangedDate)
   {
      this.lastRemoteChangedDate = lastRemoteChangedDate;
   }

   public void setLastRemoteTranferDate(Date lastRemoteTranferDate)
   {
      this.lastRemoteTranferDate = lastRemoteTranferDate;
   }

   public void setLocation(Location location)
   {
      this.location = location;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }

   public void setNetwork(Network network)
   {
      this.network = network;
   }

   public void setNextMaintenanceDate(Date nextMaintenanceDate)
   {
      this.nextMaintenanceDate = nextMaintenanceDate;
   }
   
   public void setOperationMode(String operationMode)
   {
      this.operationMode = operationMode;
   }

   public void setPredictedRefillDate(Date predictedRefillDate)
   {
      this.predictedRefillDate = predictedRefillDate;
   }

   public void setRegion(Region region)
   {
      this.region = region;
   }
   
   public void setSegment(byte segment)
   {
      this.segment = segment;
   }

   public void setSeverity(Severity severity)
   {
      this.severity = severity;
   }

   public void setSubregion(Subregion subregion)
   {
      this.subregion = subregion;
   }
   
   
   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(super.toString());
      buffer.deleteCharAt(buffer.length() - 1);
      buffer.append(", id=").append(id);      
      buffer.append(", name=").append(name);      
      buffer.append(", severity=").append(severity);      
      buffer.append(", operationMode=").append(operationMode);      
      buffer.append(", alarmInfo=").append(alarmInfo);      
      buffer.append(", em_sw_version=").append(emSwVersion);
      buffer.append(", location=").append(location);      
      buffer.append(", description=").append(description);
      buffer.append(", fuelLevel=").append(fuelLevel);      
      buffer.append(", fuelPercent=").append(fuelPercent);
      buffer.append(", batteryTemperature=").append(batteryTemperature);
      buffer.append(", environmentTemperature=").append(environmentTemperature);
      buffer.append(", installDate=").append(installDate);      
      buffer.append(", lastDataDate=").append(lastDataDate);      
      buffer.append(", lastEventDate=").append(lastEventDate);      
      buffer.append(", lastRemoteChangedDate=").append(lastRemoteChangedDate);      
      buffer.append(", lastRemoteTranferDate=").append(lastRemoteTranferDate);
      buffer.append(", nextMaintenanceDate=").append(nextMaintenanceDate);      
      buffer.append(", predictedRefillDate=").append(predictedRefillDate);      
      buffer.append(", network=").append(network == null ? null : network.getOid());      
      buffer.append(", region=").append(region == null ? null : region.getOid());      
      buffer.append(", subregion=").append(subregion == null ? null : subregion.getOid());      
      return buffer.append("]").toString();
   }
}
