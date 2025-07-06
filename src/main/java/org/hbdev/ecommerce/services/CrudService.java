package org.hbdev.ecommerce.services;

import java.util.List;

/**
 * @param <M> Model used for service
 * @param <ID> Id model
 */
public interface CrudService <M, ID>{
    void create(M object);
    M getById(ID id);
    List<M> getAll();
    M update(M object);
    void delete(ID id);
}
