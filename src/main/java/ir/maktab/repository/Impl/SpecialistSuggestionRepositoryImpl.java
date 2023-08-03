package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Orders;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.SpecialistSuggestion;
import ir.maktab.repository.SpecialistSuggestionRepository;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;

import java.util.Optional;

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

    @Override
    public Optional<SpecialistSuggestion> findSuggestWithThisSpecialistAndOrder(Specialist specialist, Orders orders) {
        String hql="select ss from SpecialistSuggestion ss where ss.specialist=:specialist and ss.order=:orders ";
        try {
            return Optional.ofNullable(session.createQuery(hql, SpecialistSuggestion.class)
                    .setParameter("specialist", specialist)
                    .setParameter("orders", orders)
                    .getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }
    }


}
