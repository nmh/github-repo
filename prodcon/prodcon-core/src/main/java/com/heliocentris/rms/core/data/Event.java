package com.heliocentris.rms.core.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.heliocentris.rms.core.Component;
import com.heliocentris.rms.util.AbstractEntity;

@Entity
@SequenceGenerator(name = Event.TABLE, sequenceName = Event.SEQUENCE_NAME, allocationSize = 1, initialValue = 1)

// @Table(name=Event.TABLE)

@Table(name=Event.TABLE, uniqueConstraints = 
{
      @UniqueConstraint(columnNames = { Event.SET_HEADER, Data.COMPONENT, Event.CODE }), 
      @UniqueConstraint(columnNames = { Event.CONFIRM_HEADER, Data.COMPONENT, Event.CODE }), 
      @UniqueConstraint(columnNames = { Event.CLEAR_HEADER, Data.COMPONENT, Event.CODE })      
})
public class Event implements Serializable
{
   /**
    * Column name of the member <code>acknowledgeDate</code>.
    */
   public static final String ACKNOWNLEDGE_DATE = "acknowledge_date";

   /**
    * Column name of the member <code>alarm</code>.
    */
   public static final String ALARM = "alarm";
   
   /**
    * Column name of the member <code>clearDate</code>.
    */
   public static final String CLEAR_DATE = "clear_date";
   
   /**
    * Column name of the member <code>clearHeader</code>.
    */
   public static final String CLEAR_HEADER = "clear_header_oid";
   
   /**
    * Column name of the member <code>clearValue</code>.
    */
   public static final String CLEAR_VALUE = "clear_value";
   
   /**
    * Column name of the member <code>code</code>.
    */
   public static final String CODE = "code";
   
   /**
    * Column name of the member <code>component</code>. 
    */
   public static final String COMPONENT = "component_oid";

   /**
    * Column name of the member <code>confirmDate</code>
    */
   public static final String CONFIRM_DATE = "confirm_date";

   /**
    * Column name of the member <code>confirmHeader</code>.
    */
   public static final String CONFIRM_HEADER = "confirm_header_oid";

   /**
    * Column name of the member <code>confirmValue</code>.
    */
   public static final String CONFIRM_VALUE = "confirm_value";

   /**
    * Column name of the member <code>counter</code>.
    */
   public static final String COUNTER = "counter";

   /**
    * Column name of the member <code>current</code>.
    */
   public static final String CURRENT = "current";

   /**
    * Column name of the member <code>dataHeader</code>. 
    */
   public static final String DATA_HEADER = "header_oid";
   
   /**
    * Column name of the member <code>dataTypeId</code>. 
    */
   public static final String DATA_TYPE_ID = "data_type_id";
   
   /**
    * Column name of the member <code>oid</code>. 
    */
   public static final String OID = "oid";
   
   /**
    * Column name of the member <code>parent</code>.
    */
   public static final String PARENT = "parent_oid";
   
   /**
    * Sequence name of the entity.
    */
   public static final String SEQUENCE_NAME = "event_oid";
   
   private static final long serialVersionUID = 6432883002207173696L;
   
   /**
    * Column name of the member <code>setHeader</code>.
    */
   public static final String SET_HEADER = "set_header_oid";
   
   /**
    * Column name of the member <code>setValue</code>.
    */
   public static final String SET_VALUE = "set_value";
   
   /**
    * Table name of the entity.
    */
   public static final String TABLE = "event";
   
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name=ACKNOWNLEDGE_DATE, columnDefinition=AbstractEntity.DATETIME_COLUMN_DEFINITION)
   private Date acknowledgeDate;
   
   @Column(name=ALARM)
   private boolean alarm;
   
   
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name=CLEAR_DATE, columnDefinition=AbstractEntity.DATETIME_COLUMN_DEFINITION)
   private Date clearDate;

   @ManyToOne(targetEntity=HistoryHeader.class)
   @JoinColumn(name=CLEAR_HEADER, nullable=true)
   private Header clearHeader;

   @Column(name=CLEAR_VALUE)
   private Float clearValue;
   
   @Column(name=CODE)
   private short code;
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name=COMPONENT, nullable=false)
   private Component component;
   
   @Column(name=CONFIRM_DATE, columnDefinition=AbstractEntity.DATETIME_COLUMN_DEFINITION)
   private Date confirmDate;
   
   @ManyToOne(targetEntity=HistoryHeader.class)
   @JoinColumn(name=CONFIRM_HEADER, nullable=true)
   private Header confirmHeader;
   
   @Column(name=CONFIRM_VALUE)
   private Float confirmValue;

   @Column(name=COUNTER, nullable=false)
   private int counter;
   
   @Column(name=CURRENT)
   private boolean current;
   
   @ManyToOne(fetch=FetchType.LAZY, targetEntity=HistoryHeader.class)
   @JoinColumn(name=SET_HEADER, nullable=false)
   private Header setHeader;
   
   @Column(name=DATA_TYPE_ID, nullable=true)
   private int dataTypeId;
   
   @Id
   @Column(name=OID)
   @GeneratedValue(generator = Event.TABLE, strategy = GenerationType.SEQUENCE)
   private Long oid;
   
   @ManyToOne()
   @JoinColumn(name=PARENT, nullable=true)
   private Event parent;
   
   @Column(name=SET_VALUE)
   private Float setValue;

   public Event()
   {
      
   }

   public Event(Event event)
   {
      setOid(event.getOid());
      setAlarm(event.isAlarm());
      setCurrent(event.isCurrent());
      
      setAcknowledgeDate(event.getAcknowledgeDate());
      setClearDate(event.getClearDate());
      setClearHeader(event.getClearHeader());
      setClearValue(event.getClearValue());
      setCode(event.getCode());
      setComponent(event.getComponent());
      setConfirmDate(event.getConfirmDate());
      setConfirmHeader(event.getConfirmHeader());
      setConfirmValue(event.getConfirmValue());
      setCounter(event.getCounter());
      setDataTypeId(event.getDataTypeId());
      setParent(event.getParent());
      setSetHeader(event.getSetHeader());
      setSetValue(event.getSetValue());
      
   }
   
   public Date getAcknowledgeDate()
   {
      return acknowledgeDate;
   }
   
   public Date getClearDate()
   {
      return clearDate;
   }   
   
   public Header getClearHeader()
   {
      return clearHeader;
   }
   
   public Float getClearValue()
   {
      return clearValue;
   }
   
   public short getCode()
   {
      return code;
   }

   public Component getComponent()
   {
      return component;
   }

   public Date getConfirmDate()
   {
      return confirmDate;
   }

   public Header getConfirmHeader()
   {
      return confirmHeader;
   }

   public Float getConfirmValue()
   {
      return confirmValue;
   }

   public int getCounter()
   {
      return counter;
   }

   public Header getSetHeader()
   {
      return setHeader;
   }

   public int getDataTypeId()
   {
      return dataTypeId;
   }

   public Long getOid()
   {
      return oid;
   }
   
   public Event getParent()
   {
      return parent;
   }
   
   public Date getSetDate()
   {
      return getSetHeader().getCreationDate();
   }
   
   public Float getSetValue()
   {
      return setValue;
   }
   
   public boolean isAcknowledged()
   {
      return acknowledgeDate != null;
   }
   
   public boolean isAlarm()
   {
      return alarm;
   }
   
   public boolean isCleared()
   {
      return clearDate != null;
   }
   
   public boolean isConfirmed()
   {
      return confirmDate != null;
   }
   public boolean isCurrent()
   {
      return current;
   }
   
   public void setAcknowledgeDate(Date acknowledgeDate)
   {
      this.acknowledgeDate = acknowledgeDate;
   }
   
   public void setAlarm(boolean alarm)
   {
      this.alarm = alarm;
   }
   
   public void setClearDate(Date clearDate)
   {
      this.clearDate = clearDate;
   }
   
   public void setClearHeader(Header clearHeader)
   {
      this.clearHeader = clearHeader;
   }

   public void setClearValue(Float clearValue)
   {
      this.clearValue = clearValue;
   }

   public void setCode(short code)
   {
      this.code = code;
   }

   public void setComponent(Component component)
   {
      this.component = component;
   }

   public void setConfirmDate(Date confirmDate)
   {
      this.confirmDate = confirmDate;
   }

   public void setConfirmHeader(Header confirmHeader)
   {
      this.confirmHeader = confirmHeader;
   }

   public void setConfirmValue(Float confirmValue)
   {
      this.confirmValue = confirmValue;
   }

   public void setCounter(int counter)
   {
      this.counter = counter;
   }

   public void setCurrent(boolean current)
   {
      this.current = current;
   }

   public void setSetHeader(Header setHeader)
   {
      this.setHeader = setHeader;
   }

   public void setDataTypeId(int dataTypeId)
   {
      this.dataTypeId = dataTypeId;
   }

   public void setOid(Long oid)
   {
      this.oid = oid;
   }
   
   public void setParent(Event parent)
   {
      this.parent = parent;
   }
   
   public void setSetValue(Float setValue)
   {
      this.setValue = setValue;
   }

   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(super.toString());
      buffer.deleteCharAt(buffer.length() - 1);
      buffer.append(", code=").append(code);
      buffer.append(", alarm=").append(alarm);      
      buffer.append(", current=").append(current);      
      buffer.append(", counter=").append(counter);      
      buffer.append(", component=").append(component == null ? null : component.getOid());
      buffer.append(", setHeader=").append(setHeader == null ? null : setHeader.getOid());
      buffer.append(", clearHeader=").append(clearHeader == null ? null : clearHeader.getOid());
      buffer.append(", confirmHeader=").append(clearHeader == null ? null : confirmHeader.getOid());
      buffer.append(", parent.oid=").append(parent == null ? null: parent.getOid());      
      return buffer.append("]").toString();
   }
   
}
