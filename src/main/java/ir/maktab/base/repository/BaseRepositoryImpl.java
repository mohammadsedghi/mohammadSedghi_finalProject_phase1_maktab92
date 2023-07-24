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
        session.persist(entity);
        return entity;
    }

    @Override
    public E update(E entity) {
         session.merge(entity);
         return entity;
    }

    @Override
    public E remove(E entity) {
        session.remove(entity);
        return entity;
    }

    @Override
    public Collection<E> load() {
        return session.createQuery("from "+getEnitytyClass().getSimpleName(),getEnitytyClass()).getResultList();
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.ofNullable(session.find(getEnitytyClass(),id));
    }
public abstract Class<E> getEnitytyClass();
}
