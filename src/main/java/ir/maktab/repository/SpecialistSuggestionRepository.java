package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entity.*;

import java.util.Optional;

public interface SpecialistSuggestionRepository extends BaseRepository<SpecialistSuggestion,Long> {
    Optional<SpecialistSuggestion> findSuggestWithThisSpecialistAndOrder(Specialist specialist, Orders orders);
}
