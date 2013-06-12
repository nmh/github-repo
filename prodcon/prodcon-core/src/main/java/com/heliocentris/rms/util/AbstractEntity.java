package com.heliocentris.rms.util;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Premapped entity class
 * 
 * @author thien
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable
{
   private static final long serialVersionUID = -8917975823774409594L;

   /**
    * Column name of the member <code>oid</code> in the database table.
    */
   public static final String OID = "oid";
   
   /**
    * Column name of the member <code>created</code> in the database table.
    */
   public static final String CREATED = "created";
   /**
    * Column name of the member <code>creator</code> in the database table.
    */
   public static final String CREATOR = "creator";

   /**
    * Time stamp definition without millisecond for PostgreSQL 
    */
   public static final String DATETIME_COLUMN_DEFINITION_POSTGRESQL = "timestamp(0)";
   
   /**
    * Time stamp definition without millisecond
    */
   public static final String DATETIME_COLUMN_DEFINITION = DATETIME_COLUMN_DEFINITION_POSTGRESQL;
   
   /**
    * Column name of the member <code>deleting</code> in the database table.
    */
   public static final String DELETING = "deleting";
   /**
    * Column name of the member <code>disabled</code> in the database table.
    */
   public static final String DISABLED = "disabled";
   
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

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name=UPDATED, columnDefinition=DATETIME_COLUMN_DEFINITION, nullable=true)
   private Date updated;
   
   @Column(name=UPDATOR, nullable=true)
   private String updator;

   @Version
   @Column(name=VERSION, nullable=false)
   private int version = 1;
   

	/**
	 * Returns the object identifier within the class of the entity.
	 * 
	 * @return the object identifier of the entity.
	 */
	public abstract Long getOid();


	/**
     * The version of the entity.
     * @return  the version.
     */
	public int getVersion()
    {
        return version;
    }

	/**
     * Returns the creation date. The creation date should not be null.
     * @return  the creation date.
     */
    public Date getCreated()
	{
		return created;
	}

    /**
     * Set the creation date of the entity to current date.
     */
	@PrePersist
	public void setCreated()
	{
		setCreated(new Date());
	}

	/**
     * @param created
     */
	public void setCreated(Date created)
	{
		this.created = created;
	}

	/**
     * Return the creator of the entity. The creator should not be null, there is the database constraint ensure for this.
     * @return  the creator
     */
	public String getCreator()
	{
		return creator;
	}

	/**
     * @param creator
     */
	public void setCreator(String creator)
	{
		this.creator = creator;
	}

	/**
     * The last update date of the entity or null if the entity was never be updated before.
     * @return  the update date.
     */
	public Date getUpdated()
	{
		return updated;
	}

	/**
	 * Set the update date to current date.
	 * 
	 */
	@PreUpdate
	public void setUpdated()
	{
		setUpdated(new Date());
	}

	/**
     * @param updated
     */
	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}

	/**
     * Returns the updator of the entity or null if the entity was never be updated before.
     * @return  the updator
     */
	public String getUpdator()
	{
		return updator;
	}

	/**
     * @param updator
     */
	public void setUpdator(String updator)
	{
		this.updator = updator;
	}
	
	public boolean isDeleting()
   {
      return deleting;
   }


   public void setDeleting(boolean deleting)
   {
      this.deleting = deleting;
   }


   public boolean isDisabled()
   {
      return disabled;
   }


   public void setDisabled(boolean disabled)
   {
      this.disabled = disabled;
   }


   /**
	 * Returns the string represents the entity in the form of simpleClassName[oid=nnn, version=nnn, id=xxx].
	 * 
	 */
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer(getClass().getSimpleName());
		buffer.append("[oid=").append(getOid());
		buffer.append(", version=").append(version);
      buffer.append(", creator=").append(creator);
      buffer.append(", created=").append(created);      
      buffer.append(", updator=").append(updator);
      buffer.append(", updated=").append(updated);      
      buffer.append(", deleting=").append(deleting);
      buffer.append(", disabled=").append(disabled);      
		return buffer.append("]").toString();
	}
}
