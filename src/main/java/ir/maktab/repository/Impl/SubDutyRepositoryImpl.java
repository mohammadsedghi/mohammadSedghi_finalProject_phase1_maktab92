package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Duty;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.SubDuty;
import ir.maktab.repository.SubDutyRepository;
import org.hibernate.Session;

import java.util.Collection;

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

    @Override
    public Collection<SubDuty> showSubDutyOfDuty(Duty duty) {
        String hql="select sd from SubDuty sd where sd.duty=:duty";
        return session.createQuery(hql, SubDuty.class)
                .setParameter("duty",duty)
                .getResultList();

    }
}
