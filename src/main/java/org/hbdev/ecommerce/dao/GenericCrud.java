package org.hbdev.ecommerce.dao;

import java.util.List;

public interface GenericCrud <T, ID>{
    void insert(T object);
    T findById(ID id);
    List<T> findAll();
    T update(T object);
    void delete(ID id);
}
