package ir.maktab.service.Impl;

import ir.maktab.entity.Address;
import ir.maktab.repository.AddressRepository;
import ir.maktab.repository.Impl.AddressRepositoryImpl;
import ir.maktab.service.AddressService;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.Collection;
import java.util.Optional;

/**
 * this class design for address instance and all thing that related with address.
 * Crud method is implemented
 * and other required method that use addressRepository to occur something(read,write)in database
 */
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private Session session;

    public AddressServiceImpl(Session session) {
        this.session = session;
        addressRepository = new AddressRepositoryImpl(session);
    }


    public Address save(Address entity) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            addressRepository.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return entity;
    }


    public Address update(Address entity) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            addressRepository.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            addressRepository.getSession().close();

        }
        return entity;
    }



    public Address remove(Address entity) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            addressRepository.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            addressRepository.getSession().close();

        }
        return entity;
    }


    public Collection<Address> load() {
        return addressRepository.load();
    }

    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }


    @Override
    public Address createAddress(Address address) {
            addressRepository.save(address);
             return address;
    }
}
