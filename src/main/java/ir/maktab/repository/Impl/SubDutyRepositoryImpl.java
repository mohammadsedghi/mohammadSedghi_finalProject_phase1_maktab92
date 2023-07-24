package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.SubDuty;
import ir.maktab.repository.SubDutyRepository;
import org.hibernate.Session;

public class SubDutyRepositoryImpl  extends BaseRepositoryImpl<SubDuty,Long>
        implements SubDutyRepository {
    private final Session session;

    public SubDutyRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Session getSession() {
     return session;
    }

    @Override
    public Class<SubDuty> getEnitytyClass() {
        return SubDuty.class;
    }
}
