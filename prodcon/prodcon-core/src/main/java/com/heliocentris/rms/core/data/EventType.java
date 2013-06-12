package com.heliocentris.rms.core.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.heliocentris.rms.core.Network;
import com.heliocentris.rms.util.AbstractEntity;

/**
 * 
 * @author thien
 * 
 */
@Entity
@Table(name = EventType.TABLE, uniqueConstraints = @UniqueConstraint(columnNames={EventType.NETWORK, EventType.MODULE_ID, EventType.CODE}))
@SequenceGenerator(name = EventType.TABLE, sequenceName = EventType.SEQUENCE_NAME, allocationSize = 1, initialValue = 1)
public class EventType extends AbstractEntity
{
   public enum Severity
   {
      Error, Ignore, Info, Warning
   }

   /**
    * Column name of the member <code>code</code> in the database table.
    */
   public static final String CODE = "code";
   /**
    * Column name of the member <code>description</code> in the database table.
    */
   public static final String DESCRIPTION = "description";
   /**
    * Column name of the member <code>ignore</code> in the database table.
    */
   public static final String IGNORE = "ignore";
   /**
    * Column name of the <code>severity</code> in the database table.
    */
   public static final String MIN_SEVERITY = "min_severity";
   /**
    * Column name of the member <code>moduleId</code> in the database table.
    */
   public static final String MODULE_ID = "module_id";
   /**
    * Column name of the member <code>name</code> in the database table.
    */
   public static final String NAME = "name";
   /**
    * Column name of the member <code>network</code> in the database table.
    */
   public static final String NETWORK = "network_oid";
   /**
    * Column name of the member <code>ordinal</code> in the database table.
    */
   public static final String ORDINAL = "ordinal";
   /**
    * Sequence name of the <code>EventType</code> in the database table.
    */
   public static final String SEQUENCE_NAME = "event_type_oid";
   
   private static final long serialVersionUID = -8621980349725223278L;

   /**
    * Column name of the <code>severity</code> in the database table.
    */
   public static final String SEVERITY = "severity";

   /**
    * Table name of the <code>EventType</code> in the database table.
    */
   public static final String TABLE = "event_type";

   @Column(name = CODE, nullable = false)
   private short code;

   @Column(name = DESCRIPTION, nullable = true)
   private String description;

   @Enumerated(EnumType.STRING)
   @Column(name = MIN_SEVERITY, nullable = false)
   private Severity minSeverity = Severity.Ignore;

   @Column(name = MODULE_ID, nullable = false)
   private short moduleId;

   @Column(name = NAME, nullable = false)
   private String name;

   @ManyToOne
   @JoinColumn(name = NETWORK, nullable = true)
   private Network network;

   @Id
   @Column(name = OID)
   @GeneratedValue(generator = EventType.TABLE, strategy = GenerationType.SEQUENCE)
   private Long oid;

   @Column(name = ORDINAL, nullable = false)
   private int ordinal;

   @Enumerated(EnumType.STRING)
   @Column(name = SEVERITY, nullable = false)
   private Severity severity;

   public EventType()
   {

   }

   public short getCode()
   {
      return code;
   }

   public String getDescription()
   {
      return description;
   }

   public Severity getMinSeverity()
   {
      return minSeverity;
   }

   public short getModuleId()
   {
      return moduleId;
   }

   public String getName()
   {
      return name;
   }

   public Network getNetwork()
   {
      return network;
   }

   public Long getOid()
   {
      return oid;
   }

   public int getOrdinal()
   {
      return ordinal;
   }

   public Severity getSeverity()
   {
      return severity;
   }

   public void setCode(short code)
   {
      this.code = code;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public void setMinSeverity(Severity minSeverity)
   {
      this.minSeverity = minSeverity;
   }

   public void setModuleId(short moduleId)
   {
      this.moduleId = moduleId;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public void setNetwork(Network network)
   {
      this.network = network;
   }

   public void setOrdinal(int ordinal)
   {
      this.ordinal = ordinal;
   }

   public void setSeverity(Severity severity)
   {
      this.severity = severity;
   }

   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(super.toString());
      buffer.deleteCharAt(buffer.length() - 1);
      buffer.append(", code=").append(code);
      buffer.append(", moduleId=").append(moduleId);
      buffer.append(", ordinal=").append(ordinal);
      buffer.append(", name=").append(name);
      buffer.append(", severity=").append(severity);
      buffer.append(", minSeverity=").append(minSeverity);
      buffer.append(", network=").append(network == null ? null : network.getOid());
      buffer.append(", description=").append(description);
      return buffer.append("]").toString();
   }

}
