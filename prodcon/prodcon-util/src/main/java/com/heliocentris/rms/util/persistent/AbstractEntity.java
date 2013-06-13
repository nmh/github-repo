package com.heliocentris.rms.util.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * The <code>AbstractEntity</code> is the predefined and premapped class for
 * convenient use. User may or may not inherits from the
 * <code>AbstractEntity</code> to provide entity classes.
 * 
 * @organisation Heliocentris GmbH
 * @author Thien
 * @since 13.06.2013
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = -6744424369390283580L;

	@Version
	@Column(nullable = false)
	private int version = 1;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@Column(nullable = false)
	private String creator = "ANONYMOUS";

	@Basic
	private String updator;

	/**
	 * Returns the object identifier within the class of the entity.
	 * 
	 * @return the object identifier of the entity.
	 */
	public abstract Long getOid();

	/**
	 * Returns the unique logical identifier of the entity. The uniqueness is
	 * not warranty over the class. This lie to the subclass.
	 * 
	 * @return the unique logical identifier of the entity.
	 */
	public String getId() {
		return (getOid() == null || getOid() == 0) ? String.valueOf(super.hashCode()) : getOid().toString();
	}

	/**
	 * The version of the entity.
	 * 
	 * @return the version.
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Returns the creation date. The creation date should not be null.
	 * 
	 * @return the creation date.
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Set the creation date of the entity to current date.
	 */
	@PrePersist
	public void setCreated() {
		setCreated(new Date());
	}

	/**
	 * @param created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Return the creator of the entity. The creator should not be null, there
	 * is the database constraint ensure for this.
	 * 
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * The last update date of the entity or null if the entity was never be
	 * updated before.
	 * 
	 * @return the update date.
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * Set the update date to current date.
	 * 
	 */
	@PreUpdate
	public void setUpdated() {
		setUpdated(new Date());
	}

	/**
	 * @param updated
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * Returns the updator of the entity or null if the entity was never be
	 * updated before.
	 * 
	 * @return the updator
	 */
	public String getUpdator() {
		return updator;
	}

	/**
	 * @param updator
	 */
	public void setUpdator(String updator) {
		this.updator = updator;
	}

	// @Override
	// public int hashCode()
	// {
	// return getOid().hashCode() + getId().hashCode();
	// }
	//
	// @Override
	// public boolean equals(Object object)
	// {
	// if (this == object) return true;
	// if (object instanceof AbstractEntity)
	// {
	// AbstractEntity that = (AbstractEntity)object;
	// if (this.getClass().equals(that.getClass()))
	// {
	// if (this.getOid().equals(that.getOid()) &&
	// this.getId().equals(that.getId()))
	// {
	// return true;
	// }
	// }
	// }
	// return false;
	// }

	/**
	 * Returns the string represents the entity in the form of
	 * simpleClassName[oid=nnn, version=nnn, id=xxx].
	 * 
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(getClass().getSimpleName());
		buffer.append("[oid=").append(getOid());
		buffer.append(", version=").append(version);
		buffer.append(", id=").append(getId());
		return buffer.append("]").toString();
	}
}
