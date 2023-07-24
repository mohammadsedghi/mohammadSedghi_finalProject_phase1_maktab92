package ir.maktab.service.Impl;

import ir.maktab.base.service.BaseServiceImpl;
import ir.maktab.entity.Specialist;
import ir.maktab.repository.SpecialistRepository;
import ir.maktab.service.SpecialistService;

public class SpecialistServiceImpl extends BaseServiceImpl<Specialist, SpecialistRepository,Long >
        implements SpecialistService {
    public SpecialistServiceImpl(SpecialistRepository repository) {
        super(repository);
    }
}
