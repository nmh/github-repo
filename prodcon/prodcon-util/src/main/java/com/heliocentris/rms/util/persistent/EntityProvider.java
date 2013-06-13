package com.heliocentris.rms.util.persistent;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @organisation Heliocentris GmbH
 * @author mnguyen
 * @since 13.06.2013
 * @param <Entity>
 * @param <Key>
 */
public interface EntityProvider<Entity extends Serializable, Key extends Serializable> {

	/**
    * 
    */
	public enum OrderBy {
		Ascending, Descending, None
	}

	/**
     * 
     */
	void clear();

	/**
	 * 
	 * @param entity
	 * @return
	 */
	<T extends Entity> Collection<T> detach(Collection<T> entity);

	/**
	 * 
	 * @param entity
	 *            the entity to be detach.
	 * @return the detach entity.
	 */
	public <T extends Entity> T detach(T entity);

	/**
	 * Returns the <code>Entity</code> specified by the class and the oid.
	 * 
	 * @return the <code>Entity</code>.
	 */
	public <T extends Entity> T find(Class<T> type, Key oid);

	/**
	 * Returns the <code>Entity</code> specified by the oid.
	 * 
	 * @return the <code>Entity</code>.
	 */
	public Entity find(Key oid);

	/**
     * 
     */
	void flush();

	/**
	 * Returns all <code>Entity</code> limited by Integer.MAX_VALUE.
	 * 
	 * @return the <code>List</code> of <code>Entity</code>.
	 */
	public List<Entity> getAll();

	/**
	 * Returns the list <code>Entity</code> specified by the class limited by
	 * Integer.MAX_VALUE.
	 * 
	 * @return the <code>List</code> of <code>Entity</code>.
	 */
	public <T extends Entity> List<T> getAll(Class<T> type);

	/**
	 * Returns the list <code>Entity</code> specified by the class limited by
	 * maxResults.
	 * 
	 * @return the <code>List</code> of <code>Entity</code>.
	 */
	public <T extends Entity> List<T> getAll(Class<T> type, int maxResults);

	/**
	 * Returns the list <code>Entity</code> specified by the class limited by
	 * maxResults.
	 * 
	 * @return the <code>List</code> of <code>Entity</code>.
	 */
	public <T extends Entity> List<T> getAll(Class<T> type, int firstResult, int maxResults);

	/**
	 * Returns the list <code>Entity</code> specified by the class, order by oid
	 * and limited by Integer.MAX_VALUE.
	 * 
	 * @return the <code>List</code> of <code>Entity</code>.
	 */
	public <T extends Entity> List<T> getAll(Class<T> type, OrderBy oidOrderBy);

	/**
	 * Returns the list <code>Entity</code> specified by the class limited by
	 * maxResults.
	 * 
	 * @return the <code>List</code> of <code>Entity</code>.
	 */
	public <T extends Entity> List<T> getAll(Class<T> type, OrderBy oidOrderBy, int firstResult, int maxResults);

	/**
	 * Returns all <code>Entity</code> specified by maxResults.
	 * 
	 * @return the <code>List</code> of <code>Entity</code>.
	 */
	public List<Entity> getAll(int firstResult, int maxResults);

	/**
	 * Returns all <code>Entity</code> specified by maxResults.
	 * 
	 * @return the <code>List</code> of <code>Entity</code>.
	 */
	public List<Entity> getAll(int maxResults);

	/**
	 * Returns the list <code>Entity</code> order by oid and limited by
	 * Integer.MAX_VALUE.
	 * 
	 * @return the <code>List</code> of <code>Entity</code>.
	 */
	public List<Entity> getAll(OrderBy oidOrderBy);

	/**
	 * @return the entity count.
	 */
	long getCount();

	/**
	 * @return the entity count.
	 */
	<T extends Entity> long getCount(Class<T> type);

	/**
	 * Return the reference to an existing entity.
	 * 
	 * @param type
	 *            the type of the entity
	 * @param oid
	 *            the key of the entity
	 * @return the reference to existing entity.
	 */
	public <T extends Entity> T getReference(Class<T> type, Key oid);

}