package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Wallet;
import ir.maktab.repository.WalletRepository;
import org.hibernate.Session;

public class WalletRepositoryImpl  extends BaseRepositoryImpl<Wallet,Long>
        implements WalletRepository {
    private final Session session;

    public WalletRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Class<Wallet> getEnitytyClass() {
        return Wallet.class;
    }

    @Override
    public Session getSession() {
        return session;
    }
}
