package ir.maktab.base.service;

import ir.maktab.base.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface BaseService <E extends BaseEntity<ID>,ID extends Serializable>{
    E save(E entity);
    E update(E entity);
    E remove(E entity);
    Collection<E> loadAll();
    Optional<E> findById(ID id);
}
