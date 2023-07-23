package ir.maktab.base.repository;

import ir.maktab.base.BaseEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface BaseRepository<E extends BaseEntity<ID>,ID extends Serializable> {
    E save(E entity);
    E update(E entity);
    E remove(E entity);
    Collection<E> load();
    Optional<E> findById(ID id);
    Session getSession();
}
