package com.heliocentris.rms.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.heliocentris.rms.util.AbstractEntity;


/**
 * The <code>Participant</code> is an abstract class.
 * The <code>Participant</code> provide general properties for its subclasses.
 * 
 * @author thien
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = Participant.SEQUENCE_GENERATOR, sequenceName = Participant.SEQUENCE_NAME, allocationSize = 1, initialValue = 1)

public abstract class Participant implements Serializable
{
   private static final long serialVersionUID = 2609926102312158999L;
   /**
    * Column name of the member <code>created</code> in the database table.
    */
   public static final String CREATED = "created";
   /**
    * Column name of the member <code>creator</code> in the database table.
    */
   public static final String CREATOR = "creator";


   /**
    * Time stamp definition without millisecond 
    */
   public static final String DATETIME_COLUMN_DEFINITION = AbstractEntity.DATETIME_COLUMN_DEFINITION;
   
   /**
    * Column name of the member <code>deleting</code> in the database table.
    */
   public static final String DELETING = "deleting";
   /**
    * Column name of the member <code>disabled</code> in the database table.
    */
   public static final String DISABLED = "disabled";
   /**
    * Column name of the member <code>label</code> in the database table.
    */
   public static final String LABEL = "label";
   /**
    * Column name of the member <code>level</code> in the database table.
    */
   public static final String LEVEL = "level";
   /**
    * Column name of the member <code>oid</code> in the database table.
    */
   public static final String OID = "oid";
   /**
    * Column name of the member <code>ordinal</code> in the database table.
    */
   public static final String ORDINAL = "ordinal";
   
   /**
    * Sequence generator the all <code>Participant</code>.
    */
   public static final String SEQUENCE_GENERATOR = "participant_generator";
   /**
    * Sequencer name of the member <code>oid</code> in the database.
    */
   public static final String SEQUENCE_NAME = "participant_oid";
   
   /**
    * Column name of the member <code>updated</code> in the database table.
    */
   public static final String UPDATED = "updated";
   /**
    * Column name of the member <code>updator</code> in the database table.
    */
   public static final String UPDATOR = "updator";
   /**
    * Column name of the member <code>version</code> in the database table.
    */
   public static final String VERSION = "version";


   @Temporal(TemporalType.TIMESTAMP)
   @Column(name=CREATED, columnDefinition=DATETIME_COLUMN_DEFINITION, nullable=false, updatable=false)
   private Date created;

   @Column(name=CREATOR, nullable=false, updatable=false)
   private String creator = "SYSTEM";

   @Column(name=DELETING, nullable=false)
   private boolean deleting;

   @Column(name=DISABLED, nullable=false)
   private boolean disabled;

   @Column(name=LABEL, nullable=true)
   private String label;

   @Column(name=LEVEL, nullable=false)
   private int level;

   @Id
   @GeneratedValue(generator=Participant.SEQUENCE_GENERATOR, strategy=GenerationType.SEQUENCE)
   @Column(name=OID)
   private Long oid;

   @Column(name=ORDINAL, nullable=false)
   private int ordinal;
   
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name=UPDATED, columnDefinition=DATETIME_COLUMN_DEFINITION, nullable=true)
   private Date updated;
   
   @Column(name=UPDATOR, nullable=true)
   private String updator;
   
   @Version
   @Column(name=VERSION, nullable=false)
   private int version = 1;

   /**
    * The <code>Date</code> of the <code>Participant</code> when is was created.
    * 
    * @return the <code>Date</code> represents the <code>Date</code>.
    */
   public Date getCreated()
   {
      return created;
   }
   
   /**
    * The creator of the <code>Participant</code>.
    * <p>It does not assigned directly to the <code>Staff</code>, cause the <code>Participant</code> must last longer as the <code>Staff</code>.
    * <p>Actually the creator is set to the name of the <code>Staff</code> or background process name.
    * @return the <code>String</code> represents the creator.
    */
   public String getCreator()
   {
      return creator;
   }
   
   /**
    * The label of the <code>Participant</code>. The label may use for searching or grouping.
    * @return the <code>String</code> represents the label.
    */
   public String getLabel()
   {
      return label;
   }

   /**
    * <code>User</code> free definition of some hierarchy level for grouping, sorting and/or searching. 
    * @return the level of the <code>Participant</code>.
    */
   public int getLevel()
   {
      return level;
   }
   
   /**
    * The Unique Object Identifier.
    * @return
    */
   public Long getOid()
   {
      return oid;
   }
   
   /**
    * <code>User</code> free definition of some ordinal for grouping, sorting and/or searching. 
    * @return the level of the <code>Participant</code>.
    */
   public int getOrdinal()
   {
      return ordinal;
   }
   
   /**
    * When ever <code>Participant</code> is updated, the updated Date will be set.
    * @return the <code>Date</code> represents the updated <code>Date</code>.
    */
   public Date getUpdated()
   {
      return updated;
   }


   /**
    * The updator of the <code>Participant</code> when it was updated.
    * <p>It does not assigned directly to some <code>Staff</code>, cause the <code>Participant</code> must survive the <code>Staff</code> deletion.
    * <p>Actually the updator is set to the name of the <code>Staff</code> or background process name.
    * @return the <code>String</code> represents the updator.
    */
   public String getUpdator()
   {
      return updator;
   }
   
   /**
    * Used to synchronize update in the database - optimistic locking.
    * @return
    */
   public int getVersion()
   {
      return version;
   }
   
   /**
    * The <code>Participant</code> is intended to be deleted and should not use further.
    * @return
    */
   public boolean isDeleting()
   {
      return deleting;
   }

   /**
    * The <code>Participant</code> is enabled per default and can be disabled by <code>User</code> manually.
    * @return true or false indicate it is disabled or not.
    */
   public boolean isDisabled()
   {
      return disabled;
   }
   
   /**
    * The <code>Participant</code> is enabled per default and can be disabled by <code>User</code> manually.
    * @return true or false indicate it is enabled or not.
    */
   public boolean isEnabled()
   {
      return !disabled;
   }

   /**
    * Set the created <code>Date</code> to the current date.
    */
   @PrePersist
   public void setCreated()
   {
      setCreated(new Date());
   }
   
   public void setCreated(Date created)
   {
      this.created = created;
   }

   public void setCreator(String creator)
   {
      this.creator = creator;
   }

   public void setDeleting(boolean deleting)
   {
      this.deleting = deleting;
   }

   public void setDisabled(boolean disabled)
   {
      this.disabled = disabled;
   }

   public void setLabel(String label)
   {
      this.label = label;
   }

   public void setLevel(int level)
   {
      this.level = level;
   }

   public void setOid(Long oid)
   {
      this.oid = oid;
   }

   public void setOrdinal(int ordinal)
   {
      this.ordinal = ordinal;
   }

   /**
    * Set the updated <code>Date</code> to the current date.
    */
   @PreUpdate
   public void setUpdated()
   {
      setUpdated(new Date());
   }
   
   public void setUpdated(Date updated)
   {
      this.updated = updated;
   }

   public void setUpdator(String updator)
   {
      this.updator = updator;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((oid == null) ? 0 : oid.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object object)
   {
      if (this == object) return true;
      if (object == null) return false;
      if (getClass() != object.getClass()) return false;
      Participant other = (Participant) object;
      if (oid == null)
      {
         if (other.oid != null) return false;
      } 
      else if (!oid.equals(other.oid)) return false;
      return true;
   }

   /**
    * SimpleClassName[oid=number, version=number, created=datetime, creator=string, updated=datetime, ...].
    * 
    */
   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(getClass().getSimpleName());
      buffer.append("[oid=").append(oid);
      buffer.append(", version=").append(version);
      buffer.append(", created=").append(created);      
      buffer.append(", creator=").append(creator);      
      buffer.append(", updated=").append(updated);      
      buffer.append(", updator=").append(updator);      
      buffer.append(", label=").append(label);      
      buffer.append(", level=").append(level);      
      buffer.append(", ordinal=").append(ordinal);      
      buffer.append(", deleting=").append(deleting);      
      buffer.append(", disabled=").append(disabled);      
      return buffer.append("]").toString();
   }
   
}
