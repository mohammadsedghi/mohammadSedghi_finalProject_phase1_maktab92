package ir.maktab.service;


import ir.maktab.entity.Duty;
import ir.maktab.entity.SubDuty;

import java.util.Collection;
import java.util.Set;

public interface SubDutyService  {
    SubDuty addSubDuty(SubDuty duty);
    Collection<SubDuty> showAllSubDutyOfDuty(Duty duty);
    boolean editSubDutyPrice(SubDuty subduty,Double price);
    boolean editSubDutyDescription(SubDuty subduty,String description);
}
