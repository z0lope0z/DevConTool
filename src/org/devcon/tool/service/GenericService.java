package org.devcon.tool.service;

import java.util.List;
import java.util.Map;

public interface GenericService<T, K> {
    List<T> getAll();

    /**
     * Gets all records without duplicates.
     * <p>
     * Note that if you use this method, it is imperative that your model
     * classes correctly implement the hashcode/equals methods
     * </p>
     * 
     * @return List of populated objects
     */
    List<T> getAllDistinct();

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     * 
     * @param id
     *            the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(K id);

    /**
     * Checks for existence of an object of type T using the id arg.
     * 
     * @param id
     *            the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(K id);

    /**
     * Generic method to save an object - handles both update and insert.
     * 
     * @param object
     *            the object to save
     * @return the persisted object
     */
    T save(Map<String, Object> input) throws Exception;

    /**
     * Generic method to delete an object based on class and id
     * 
     * @param id
     *            the identifier (primary key) of the object to remove
     */
    void remove(String id);
}
