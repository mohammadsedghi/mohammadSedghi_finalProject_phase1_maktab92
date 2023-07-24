package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.SpecialistSuggestion;
import ir.maktab.repository.SpecialistSuggestionRepository;
import org.hibernate.Session;

public class SpecialistSuggestionRepositoryImpl extends BaseRepositoryImpl<SpecialistSuggestion,Long>
        implements SpecialistSuggestionRepository {
    private final Session session;

    public SpecialistSuggestionRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class<SpecialistSuggestion> getEnitytyClass() {
        return SpecialistSuggestion.class;
    }
}
