package ir.maktab.base.repository;

import ir.maktab.base.BaseEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public abstract class BaseRepositoryImpl <E extends BaseEntity<ID>,ID extends Serializable>
        implements Serializable,BaseRepository<E,ID> {
    private final Session session;

    public BaseRepositoryImpl(Session session) {
        this.session = session;
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
    public Collection<E> load() {
        return null;
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.empty();
    }
public abstract Class<E> getEnitytyClass();
}
