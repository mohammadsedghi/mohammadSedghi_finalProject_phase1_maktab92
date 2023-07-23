package ir.maktab.base.service;

import ir.maktab.base.BaseEntity;
import ir.maktab.base.repository.BaseRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public class BaseServiceImpl  <E extends BaseEntity<ID>, REPOSITORY extends BaseRepository<E, ID>,ID extends Serializable>
        implements Serializable, BaseService<E,ID> {
    protected REPOSITORY repository;

    public BaseServiceImpl(REPOSITORY repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        return null;
    }

    @Override
    public E update(E entity) {
        return null;
    }

    @Override
    public E remove(E entity) {
        return null;
    }

    @Override
    public Collection<E> loadAll() {
        return null;
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.empty();
    }
}
