package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Duty;
import ir.maktab.entity.Order;
import ir.maktab.repository.DutyRepository;
import org.hibernate.Session;

public class DutyRepositoryImpl extends BaseRepositoryImpl<Duty,Long>
        implements DutyRepository {
    private final Session session;

    public DutyRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class<Duty> getEnitytyClass() {
        return Duty.class;
    }
}
