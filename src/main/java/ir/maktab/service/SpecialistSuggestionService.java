package ir.maktab.service;


import ir.maktab.entity.Orders;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.SpecialistSuggestion;

import java.util.Optional;

public interface SpecialistSuggestionService  {
    SpecialistSuggestion submitSpecialistSuggestion(SpecialistSuggestion specialistSuggestion);
    void findSuggestWithThisSpecialistAndOrder(Specialist specialist, Orders orders);

}
