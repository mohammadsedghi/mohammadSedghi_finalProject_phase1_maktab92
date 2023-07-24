package ir.maktab.service.Impl;

import ir.maktab.base.service.BaseServiceImpl;
import ir.maktab.entity.SpecialistSuggestion;
import ir.maktab.repository.SpecialistSuggestionRepository;
import ir.maktab.service.SpecialistSuggestionService;

public class SpecialistSuggestionServiceImpl extends BaseServiceImpl<SpecialistSuggestion, SpecialistSuggestionRepository,Long >
        implements SpecialistSuggestionService {
    public SpecialistSuggestionServiceImpl(SpecialistSuggestionRepository repository) {
        super(repository);
    }
}
