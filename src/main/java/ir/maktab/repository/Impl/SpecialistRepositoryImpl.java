package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Specialist;
import ir.maktab.repository.SpecialistRepository;
import org.hibernate.Session;

public class SpecialistRepositoryImpl extends BaseRepositoryImpl<Specialist,Long>
        implements SpecialistRepository {

    private final Session session;

    public SpecialistRepositoryImpl(Session session, Session session1) {
        super(session);
        this.session = session1;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class<Specialist> getEnitytyClass() {
        return Specialist.class;
    }
}
