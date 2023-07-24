package ir.maktab.base.service;

import ir.maktab.base.BaseEntity;
import ir.maktab.base.repository.BaseRepository;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public class BaseServiceImpl  <E extends BaseEntity<ID>, REPOSITORY extends BaseRepository<E, ID>,ID extends Serializable>
        implements Serializable, BaseService<E,ID> {
    protected REPOSITORY repository;
    Transaction transaction = null;
    public BaseServiceImpl(REPOSITORY repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        try {
            transaction = repository.getSession().getTransaction();
            transaction.begin();
           repository.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            repository.getSession().close();

        }
        return entity;
    }

    @Override
    public E update(E entity) {
        try {
            transaction = repository.getSession().getTransaction();
            transaction.begin();
            repository.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            repository.getSession().close();

        }
        return entity;
    }

    @Override
    public E remove(E entity) {
        try {
            transaction = repository.getSession().getTransaction();
            transaction.begin();
            repository.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            repository.getSession().close();

        }
        return entity;
    }

    @Override
    public Collection<E> loadAll() {
        return repository.load();
    }

    @Override
    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }
}
