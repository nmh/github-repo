package com.heliocentris.rms.core.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.heliocentris.rms.core.Component;
import com.heliocentris.rms.util.AbstractEntity;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(uniqueConstraints=@UniqueConstraint(columnNames={Data.COMPONENT, Data.DATA_TYPE_ID, Data.CREATION_DATE}))
@SequenceGenerator(name = Data.SEQUENCE_NAME, sequenceName = Data.SEQUENCE_NAME, allocationSize = 1, initialValue = 1)

public abstract class Data implements Serializable
{
   /**
    * Column name of the member <code>booleanValue</code>. 
    */
   public static final String BOOLEAN_VALUE = "boolean_value";
   
   /**
    * Column name of the member <code>byteValue</code>. 
    */
   public static final String BYTE_VALUE = "byte_value";
   
   /**
    * Column name of the member <code>component</code>. 
    */
   public static final String COMPONENT = "component_oid";

   /**
    * Column name of the member <code>creationDate</code>.
    */
   public static final String CREATION_DATE = "creation_date";

   /**
    * Column name of the member <code>dataTypeId</code>. 
    */
   public static final String DATA_TYPE_ID = "data_type_id";
   
   /**
    * 
    */
   public static final String DATETIME_COLUMN_DEFINITION = AbstractEntity.DATETIME_COLUMN_DEFINITION;
   
   /**
    * Column name of the member <code>doubleValue</code>. 
    */
   public static final String DOUBLE_VALUE = "double_value";
   
   /**
    * Column name of the member <code>floatValue</code>. 
    */
   public static final String FLOAT_VALUE = "float_value";

   /**
    * Column name of the member <code>dataHeader</code>. 
    */
   public static final String HEADER = "header_oid";

   /**
    * Column name of the member <code>integerValue</code>. 
    */
   public static final String INTEGER_VALUE = "integer_value";
   
   
   /**
    * Column name of the member <code>longValue</code>. 
    */
   public static final String LONG_VALUE = "long_value";
   
   /**
    * Column name of the member <code>oid</code>. 
    */
   public static final String OID = "oid";

   /**
    * Column name of the member <code>segment</code>. 
    */
   public static final String SEGMENT = "segment";

   /**
    * Sequence name of the entity
    */
   public static final String SEQUENCE_NAME = "data_oid";
   
   private static final long serialVersionUID = 2349680625981636527L;

   
   /**
    * Column name of the member <code>shortValue</code>. 
    */
   public static final String SHORT_VALUE = "short_value";
   
   /**
    * Column name of the member <code>stringValue</code>. 
    */
   public static final String STRING_VALUE = "string_value";
   
   @Column(name=BOOLEAN_VALUE, nullable=true)
   private Boolean booleanValue;
   
   
   @Column(name=BYTE_VALUE, nullable=true)
   private Byte byteValue;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name=COMPONENT, nullable=false)
   private Component component;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name=CREATION_DATE, columnDefinition=DATETIME_COLUMN_DEFINITION, nullable=false)
   private Date creationDate;
   
   @Column(name=DATA_TYPE_ID, nullable=false)
   private int dataTypeId;

   @Column(name=DOUBLE_VALUE, nullable=true)
   private Double doubleValue;
   
   @Column(name=FLOAT_VALUE, nullable=true)
   private Float floatValue;

   @Column(name=INTEGER_VALUE, nullable=true)
   private Integer integerValue;

   @Column(name=LONG_VALUE, nullable=true)
   private Long longValue;

   @Id
   @Column(name=OID, nullable=false)
   @GeneratedValue(generator = Data.SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
   private Long oid;

   @Column(name=SEGMENT, nullable=false)
   private short segment;

   @Transient
   private String serialNumber;

   @Column(name=SHORT_VALUE, nullable=true)
   private Short shortValue;

   @Column(name=STRING_VALUE, nullable=true)
   private String stringValue;
   
   public Data()
   {
      
   }

   public Data(Component component, int dataTypeId, Boolean value)
   {
      setComponent(component);
      setDataTypeId(dataTypeId);
      setBoolean(value);
   }

   public Data(Component component, int dataTypeId, Byte value)
   {
      setComponent(component);
      setDataTypeId(dataTypeId);
      setByte(value);
   }
   
   public Data(Component component, int dataTypeId, Double value)
   {
      setComponent(component);
      setDataTypeId(dataTypeId);
      setDouble(value);
   }

   public Data(Component component, int dataTypeId, Float value)
   {
      setComponent(component);
      setDataTypeId(dataTypeId);
      setFloat(value);
   }

   public Data(Component component, int dataTypeId, Integer value)
   {
      setComponent(component);
      setDataTypeId(dataTypeId);
      setInteger(value);
   }

   public Data(Component component, int dataTypeId, Long value)
   {
      setComponent(component);
      setDataTypeId(dataTypeId);
      setLong(value);
   }
   
   public Data(Component component, int dataTypeId, Object value)
   {
      setComponent(component);
      setDataTypeId(dataTypeId);
      setValue(value);
   }

   public Data(Component component, int dataTypeId, Short value)
   {
      setComponent(component);
      setDataTypeId(dataTypeId);
      setShort(value);
   }

   public Data(Component component, int dataTypeId, String value)
   {
      setComponent(component);
      setDataTypeId(dataTypeId);
      setString(value);
   }

   public Data(Data data)
   {
      setSegment(data.getSegment());
      setCreationDate(data.getCreationDate());
      
      setHeader(data.getHeader());
      setComponent(data.getComponent());
      setDataTypeId(data.getDataTypeId());
      
      setBoolean(data.getBoolean());
      setByte(data.getByteValue());
      setDouble(data.getDouble());
      setFloat(data.getFloat());
      setInteger(data.getInteger());
      setLong(data.getLong());
      setShort(data.getShort());
      setString(data.getString());
      
      setSerialNumber(data.getSerialNumber()); // just transient
   }

   public Data(int dataTypeId, Boolean value)
   {
      setDataTypeId(dataTypeId);
      setBoolean(value);
   }
   
   public Data(int dataTypeId, Byte value)
   {
      setDataTypeId(dataTypeId);
      setByte(value);
   }

   public Data(int dataTypeId, Double value)
   {
      setDataTypeId(dataTypeId);
      setDouble(value);
   }

   public Data(int dataTypeId, Float value)
   {
      setDataTypeId(dataTypeId);
      setFloat(value);
   }
   
   public Data(int dataTypeId, Integer value)
   {
      setDataTypeId(dataTypeId);
      setInteger(value);
   }

   public Data(int dataTypeId, Long value)
   {
      setDataTypeId(dataTypeId);
      setLong(value);
   }
   
   public Data(int dataTypeId, Object value)
   {
      setDataTypeId(dataTypeId);
      setValue(value);
   }

   public Data(int dataTypeId, Short value)
   {
      setDataTypeId(dataTypeId);
      setShort(value);
   }

   public Data(int dataTypeId, String value)
   {
      setDataTypeId(dataTypeId);
      setString(value);
   }

   public Boolean getBoolean()
   {
      return booleanValue;
   }

   public Byte getByteValue()
   {
      return byteValue;
   }

   public Component getComponent()
   {
      return component;
   }

   public Date getCreationDate()
   {
      return creationDate;
   }

   public int getDataTypeId()
   {
      return dataTypeId;
   }
   
   public Date getDeliveryDate()
   {
      return getHeader().getDeliveryDate();
   }

   public Double getDouble()
   {
      return doubleValue;
   }

   
   public Float getFloat()
   {
      return floatValue;
   }

   public abstract Header getHeader();

   public Integer getInteger()
   {
      return integerValue;
   }

   public Long getLong()
   {
      return longValue;
   }

   public Long getOid()
   {
      return oid;
   }

   public short getSegment()
   {
      return segment;
   }

   public String getSerialNumber()
   {
      return serialNumber;
   }   

   public Short getShort()
   {
      return shortValue;
   }

   public String getString()
   {
      return stringValue;
   }

   public Object getValue()
   {
      if (booleanValue != null) return booleanValue;
      if (byteValue != null) return byteValue;
      if (shortValue != null) return shortValue;
      if (integerValue != null) return integerValue;
      if (longValue != null) return longValue;      
      if (floatValue != null) return floatValue;      
      if (doubleValue != null) return doubleValue;      
      if (stringValue != null) return stringValue;      
     
      return null;
   }

   public void setBoolean(Boolean booleanValue)
   {
      this.booleanValue = booleanValue;
   }
   
   public void setByte(Byte byteValue)
   {
      this.byteValue = byteValue;
   }
   
   public void setComponent(Component component)
   {
      this.component = component;
   }
  
   public void setCreationDate(Date creationDate)
   {
      this.creationDate = creationDate;
   }

   public void setDataTypeId(int dataTypeId)
   {
      this.dataTypeId = dataTypeId;
   }

   public void setDouble(Double doubleValue)
   {
      this.doubleValue = doubleValue;
   }

   public void setFloat(Float floatValue)
   {
      this.floatValue = floatValue;
   }

   public abstract void setHeader(Header header);
   
   public void setInteger(Integer integerValue)
   {
      this.integerValue = integerValue;
   }

   public void setLong(Long longValue)
   {
      this.longValue = longValue;
   }

   public void setOid(Long oid)
   {
      this.oid = oid;
   }
   
   public void setSegment(short segment)
   {
      this.segment = segment;
   }

   public void setSerialNumber(String serialNumber)
   {
      this.serialNumber = serialNumber;
   }
   
   public void setShort(Short shortValue)
   {
      this.shortValue = shortValue;
   }

   public void setString(String stringValue)
   {
      this.stringValue = stringValue;
   }

   
   public void setValue(Object value)
   {
      if (value instanceof Boolean)
      {
         setBoolean((Boolean)value);
      }
      else if (value instanceof Byte)
      {
         setByte((Byte)value);
      }
      else if (value instanceof Short)
      {
         setShort((Short)value);
      }
      else if (value instanceof Integer)
      {
         setInteger((Integer)value);
      }
      else if (value instanceof Long)
      {
         setLong((Long)value);
      }
      else if (value instanceof Float)
      {
         setFloat((Float)value);
      }
      else if (value instanceof Double)
      {
         setDouble((Double)value);
      }
      else
      {
         booleanValue = null;
         byteValue = null;
         shortValue = null;
         integerValue = null;
         longValue = null;
         floatValue = null;
         doubleValue = null;
      }
   }

   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(getClass().getSimpleName());
      buffer.append("[oid=").append(oid);      
      buffer.append(", segment=").append(segment);
      buffer.append(", creationDate=").append(creationDate);
      buffer.append(", dataTypeId=").append(dataTypeId);      
      buffer.append(", header=").append(getHeader() == null ? null : getHeader().getOid());
      buffer.append(", component=").append(component == null ? null : component.getOid());
      buffer.append(", value=").append(getValue());
      buffer.append(", serialNumber=").append(serialNumber);      
      return buffer.append("]").toString();
   }
   
}
