package com.heliocentris.rms.util.persistent;

import java.io.Serializable;

/**
 * 
 * @organisation Heliocentris GmbH
 * @author Thien
 * @since 13.06.2013
 * @param <Entity>
 * @param <Key>
 */
public interface EntityPersister<Entity extends Serializable, Key extends Serializable> extends EntityProvider<Entity, Key>
{

    /**
     * Insert the <code>Entity</code> into the backing store.
     * 
     * @param entity the <code>Entity</code>.
     * @return the inserted <code>Entity</code>.
     */
    <T extends Entity> T insert(T entity);

    /**
     * Update the <code>Entity</code> in the backing store.
     * 
     * @param entity the <code>Entity</code>.
     * @return the updated <code>Entity</code>.
     */
    <T extends Entity> T update(T entity);

    /**
     * Delete the <code>Entity</code> from the backing store.
     * 
     * @param entity the <code>Entity</code>.
     * @return the deleted <code>Entity</code>.
     */
    <T extends Entity> T delete(T entity);

    /**
     * Delete all the <code>Entity</code> from the backing store.
     * 
     * @param entity the <code>Entity</code>.
     * @return the count of deleted <code>Entity</code>.
     */
    int deleteAll();
    
    /**
     * Delete all the <code>Entity</code> specified by the type from the backing store.
     * 
     * @param type the class <code>Entity</code>.
     * @return the count of deleted <code>Entity</code>.
     */
    <T extends Entity> int deleteAll(Class<T> type);


    
}
