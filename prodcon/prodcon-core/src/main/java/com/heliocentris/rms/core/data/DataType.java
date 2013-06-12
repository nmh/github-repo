package com.heliocentris.rms.core.data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.heliocentris.rms.core.util.Version;
import com.heliocentris.rms.util.AbstractEntity;

/**
 * 
 * 
 * @author thien
 *
 */

@Entity
@SequenceGenerator(name = DataType.TABLE, sequenceName = DataType.SEQUENCE_NAME, allocationSize = 1, initialValue = 1)
@Table(name=DataType.TABLE, uniqueConstraints=@UniqueConstraint(columnNames={DataType.EM_SW_VERSION, DataType.MODULE_ID, DataType.ID}))

public class DataType extends AbstractEntity
{
   public enum Category { Accumulation, Configuration, Measurement, Status }
   public enum Scope { Module, System, Unit }
   public enum ValueType { Boolean, Byte, Double, Float, Integer, Long, Short, String }
   /**
    * Column name of the member <code>actionIndex</code> in the database table.
    */
   public static final String ACTION_INDEX = "action_index";
   /**
    * Column name of the member <code>available</code> in the database table.
    */
   public static final String AVAILABLE = "available";
   /**
    * Column name of the member <code>category</code> in the database table.
    */
   public static final String CATEGORY = "category";   
   /**
    * Column name of the member <code>defaultValue</code> in the database table.
    */
   public static final String DEFAULT_VALUE = "default_value";   
   /**
    * Column name of the member <code>dynamic</code> in the database table.
    */
   public static final String DYNAMIC = "dynamic";   
   /**
    * Column name of the member <code>emSwVersion</code> in the database table.
    */
   public static final String EM_SW_VERSION = "em_sw_version";
   /**
    * Column name of the member <code>group</code> in the database table.
    */
   public static final String GROUP = "group_";
   /**
    * Column name of the member <code>helpIndex</code> in the database table.
    */
   public static final String HELP_INDEX = "help_index";
   /**
    * Column name of the member <code>id</code> in the database table.
    */
   public static final String ID = "id";   

   /**
    * Column name of the member <code>label</code> in the database table.
    */
   public static final String LABEL = "label";   

   /**
    * Column name of the member <code>maxX</code> in the database table.
    */
   public static final String MAX_X = "max_x";   
   
   /**
    * Column name of the member <code>maxY</code> in the database table.
    */
   public static final String MAX_Y = "max_y";   

   /**
    * Column name of the member <code>moduleId</code> in the database table.
    */
   public static final String MODULE_ID = "module_id";   

   /**
    * Column name of the member <code>ordinal</code> in the database table.
    */
   public static final String ORDINAL = "ordinal";   

   /**
    * Column name of the member <code>readAccess</code> in the database table.
    */
   public static final String READ_ACCESS = "read_access";   
   
   /**
    * Column name of the member <code>readable</code> in the database table.
    */
   public static final String READABLE = "readable";

   /**
    * Column name of the member <code>scope</code> in the database table.
    */
   public static final String SCOPE = "scope";

   /**
    * Sequence name of the member <code>DataType</code> in the database table.
    */
   public static final String SEQUENCE_NAME = "data_type_oid";
   
   private static final long serialVersionUID = 4144755313408484697L;
   
   /**
    * Table name of the member <code>DataType</code> in the database table.
    */
   public static final String TABLE = "data_type";

   /**
    * Column name of the member <code>dynamic</code> in the database table.
    */
   public static final String UNIT = "unit";

   /**
    * Column name of the member <code>unsigned</code> in the database table.
    */
   public static final String UNSIGNED = "unsigned";

   /**
    * Column name of the member <code>valueType</code> in the database table.
    */
   public static final String VALUE_TYPE = "value_type";

   /**
    * Column name of the member <code>visible</code> in the database table.
    */
   public static final String VISIBLE = "visible";
   
   /**
    * Column name of the member <code>writable</code> in the database table.
    */
   public static final String WRITABLE = "writable";

   /**
    * Column name of the member <code>writeAccess</code> in the database table.
    */
   public static final String WRITE_ACCESS = "write_access";
   
   @Column(name=ACTION_INDEX, nullable=false)
   private int actionIndex;
   
   @Column(name=AVAILABLE, nullable=false)
   private boolean available;
   
   @Enumerated(EnumType.STRING)
   @Column(name=CATEGORY, nullable=false)
   private Category category;
   
   @Column(name=DEFAULT_VALUE, nullable=true)
   private String defaultValue;
   
   @Column(name=DYNAMIC, nullable=false)
   private boolean dynamic;

   @Embedded
   @AttributeOverride(name="version", column=@Column(name=EM_SW_VERSION, nullable=true))
   private Version emSwVersion;
   
   @Column(name=GROUP, nullable=false)
   private int group;
   
   @Column(name=HELP_INDEX, nullable=false)
   private int helpIndex;
   
   @Column(name=ID, nullable=false)
   private int id;

   @Column(name=LABEL, nullable=true)
   private String label;

   @Column(name=MAX_X, nullable=false)
   private float maxX;

   @Column(name=MAX_Y, nullable=false)
   private float maxY;

   @Column(name=MODULE_ID, nullable=false)
   private short moduleId;

   @Id@Column(name=OID)
   @GeneratedValue(generator=DataType.TABLE, strategy=GenerationType.SEQUENCE)
   private Long oid;
   
   @Column(name=ORDINAL, nullable=false)
   private int ordinal;

   @Column(name=READABLE, nullable=false)
   private boolean readable;

   @Column(name=READ_ACCESS, nullable=false)
   private boolean readAccess;

   @Enumerated(EnumType.STRING)
   @Column(name=SCOPE, nullable=false)
   private Scope scope;

   @Column(name=UNIT, nullable=true)
   private String unit;

   @Column(name=UNSIGNED, nullable=false)
   private boolean unsigned;

   @Enumerated(EnumType.STRING)
   @Column(name=VALUE_TYPE, nullable=false)
   private ValueType valueType;
   
   @Column(name=VISIBLE, nullable=false)
   private boolean visible;

   @Column(name=WRITABLE, nullable=false)
   private boolean writable;
   
   @Column(name=WRITE_ACCESS, nullable=false)
   private boolean writeAccess;
   
   public DataType()
   {
      
   }
   
   public int getActionIndex()
   {
      return actionIndex;
   }

   public Category getCategory()
   {
      return category;
   }
   
   public String getDefaultValue()
   {
      return defaultValue;
   }
   
   /**
    * The Energy Manager Software Version.
    * @return the Software <code>Version</code> of the Energy Manager.
    */
   public Version getEmSwVersion()
   {
      return emSwVersion;
   }
   
   public int getGroup()
   {
      return group;
   }
   
   public int getHelpIndex()
   {
      return helpIndex;
   }
   
   /**
    * The <code>id</code> of the <code>DataType</code>. Unique within the <code>moduleId</code>.
    * @return
    */
   public int getId()
   {
      return id;
   }

   public String getLabel()
   {
      return label;
   }

   public float getMaxX()
   {
      return maxX;
   }

   public float getMaxY()
   {
      return maxY;
   }

   /**
    * The <code>moduleId</code> of the <code>DataType</code>.
    * @return
    */
   public short getModuleId()
   {
      return moduleId;
   }

   public Long getOid()
   {
      return oid;
   }

   public int getOrdinal()
   {
      return ordinal;
   }

   public Scope getScope()
   {
      return scope;
   }

   public String getUnit()
   {
      return unit;
   }

   public ValueType getValueType()
   {
      return valueType;
   }

   public boolean isAvailable()
   {
      return available;
   }
   
   public boolean isDynamic()
   {
      return dynamic;
   }

   public boolean isReadable()
   {
      return readable;
   }

   public boolean isReadAccess()
   {
      return readAccess;
   }

   /**
    * Specified if the numeric value is signed or unsigned.
    * Used for import and export.
    * 
    * @return false or true.
    */
   public boolean isUnsigned()
   {
      return unsigned;
   }

   public boolean isVisible()
   {
      return visible;
   }

   public boolean isWritable()
   {
      return writable;
   }

   public boolean isWriteAccess()
   {
      return writeAccess;
   }

   public void setActionIndex(int actionIndex)
   {
      this.actionIndex = actionIndex;
   }

   public void setAvailable(boolean available)
   {
      this.available = available;
   }

   public void setCategory(Category category)
   {
      this.category = category;
   }

   public void setDefaultValue(String defaultValue)
   {
      this.defaultValue = defaultValue;
   }

   public void setDynamic(boolean dynamic)
   {
      this.dynamic = dynamic;
   }

   public void setEmSwVersion(Version emSwVersion)
   {
      this.emSwVersion = emSwVersion;
   }

   public void setGroup(int group)
   {
      this.group = group;
   }

   public void setHelpIndex(int helpIndex)
   {
      this.helpIndex = helpIndex;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public void setLabel(String label)
   {
      this.label = label;
   }

   public void setMaxX(float maxX)
   {
      this.maxX = maxX;
   }

   public void setMaxY(float maxY)
   {
      this.maxY = maxY;
   }

   public void setModuleId(short moduleId)
   {
      this.moduleId = moduleId;
   }

   public void setOrdinal(int ordinal)
   {
      this.ordinal = ordinal;
   }

   public void setReadable(boolean readable)
   {
      this.readable = readable;
   }

   public void setReadAccess(boolean readAccess)
   {
      this.readAccess = readAccess;
   }

   public void setScope(Scope scope)
   {
      this.scope = scope;
   }

   public void setUnit(String unit)
   {
      this.unit = unit;
   }

   public void setUnsigned(boolean unsigned)
   {
      this.unsigned = unsigned;
   }

   public void setValueType(ValueType valueType)
   {
      this.valueType = valueType;
   }

   public void setVisible(boolean visible)
   {
      this.visible = visible;
   }

   public void setWritable(boolean writable)
   {
      this.writable = writable;
   }

   public void setWriteAccess(boolean writeAccess)
   {
      this.writeAccess = writeAccess;
   }

   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(super.toString());
      buffer.deleteCharAt(buffer.length() - 1);
      buffer.append(", id=").append(id);
      buffer.append(", moduleId=").append(moduleId);      
      buffer.append(", ordinal=").append(ordinal);      
      buffer.append(", scope=").append(scope);      
      buffer.append(", category=").append(category);      
      buffer.append(", valueType=").append(valueType);      
      buffer.append(", unsigned=").append(unsigned);      
      buffer.append(", defaultValue=").append(defaultValue);      
      buffer.append(", emSwVersion=").append(emSwVersion);      
      return buffer.append("]").toString();
   }
   
}
