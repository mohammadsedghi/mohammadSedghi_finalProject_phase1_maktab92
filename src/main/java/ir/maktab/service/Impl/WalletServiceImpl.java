package ir.maktab.service.Impl;


import ir.maktab.entity.Wallet;
import ir.maktab.repository.Impl.WalletRepositoryImpl;
import ir.maktab.repository.WalletRepository;
import ir.maktab.service.WalletService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.util.Collection;
import java.util.Optional;

/**
 * this class design for work with wallet instance and all thing that related with wallet.
 *     Crud method is implemented
 *    and other required method that use walletRepository to occur something(read,write)in database
 *
 */
public class WalletServiceImpl implements WalletService {
    private Session session;
    private Transaction transaction;
    private WalletRepository walletRepository;

    public WalletServiceImpl(Session session) {
        this.session = session;
        transaction = session.getTransaction();
        walletRepository = new WalletRepositoryImpl(session);
    }

    public Wallet update(Wallet wallet) {
        try {
            transaction.begin();
            walletRepository.update(wallet);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return wallet;
    }


    public Wallet remove(Wallet wallet) {
        try {
            transaction.begin();
            walletRepository.remove(wallet);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return wallet;
    }


    public Collection<Wallet> load() {
        return walletRepository.load();
    }


    public Optional<Wallet> findById(Long id) {
        return walletRepository.findById(id);
    }


    public Session getSession() {
        return session;
    }


    public Wallet createWallet() {
        Wallet wallet = new Wallet(0d);
        walletRepository.save(wallet);
        return wallet;
    }

    @Override
    public Double payByWallet(Double cost) {
        return null;
    }

    @Override
    public Double payByPaymentGateway(Double cost) {
        return null;
    }
}
