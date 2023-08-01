package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Customer;
import ir.maktab.entity.Duty;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.SubDuty;
import ir.maktab.repository.SubDutyRepository;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;

import java.util.Collection;
import java.util.Optional;

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

    @Override
    public Optional<SubDuty> isExistSubDuty(String name) {
        String hql = "select sd from SubDuty sd where sd.name=:name ";
        try {
            return Optional.ofNullable(session.createQuery(hql, SubDuty.class)
                    .setParameter("name", name)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
