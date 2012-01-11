package org.devcon.tool.service.impl;

import java.util.List;
import java.util.Map;

import org.devcon.tool.service.GenericService;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.ModelMeta;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

public abstract class GenericServiceImpl<T, K> implements GenericService<T, K>{
    private Class<T> persistentClass;
    
    private ModelMeta<T> modelMeta;
    
    public GenericServiceImpl(final Class<T> persistentClass){
        this.persistentClass = persistentClass;
    }
    
    public GenericServiceImpl(final Class<T> persistentClass, ModelMeta<T> modelMeta) {
        this.persistentClass = persistentClass;
        this.modelMeta = modelMeta;
    }
    
    public T save(Map<String, Object> input) throws Exception{
        T obj = persistentClass.newInstance();
        BeanUtil.copy(input, obj);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(obj);
        tx.commit();
        return obj;
    }
    
    public boolean exists(K id) {
        if (get(id) != null){
            return true;
        }
        return false;
    }

    public T get(K id) {
        return (T) Datastore.get(persistentClass, KeyFactory.stringToKey((java.lang.String) id));
    }

    public T get(Key key){
        return (T) Datastore.get(persistentClass, key);
    }
    
    public List<T> getAll() {
        return (List<T>) Datastore.query(modelMeta).asList();
    }

    public List<T> getAllDistinct() {
        return (List<T>) Datastore.query(modelMeta).asList();
    }

    public void remove(String id){
        Transaction tx = Datastore.beginTransaction();
        Datastore.delete(KeyFactory.stringToKey((java.lang.String) id));
        tx.commit();
    }
    
}
