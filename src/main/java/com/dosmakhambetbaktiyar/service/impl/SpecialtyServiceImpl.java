package com.dosmakhambetbaktiyar.service.impl;

import com.dosmakhambetbaktiyar.model.Specialty;
import com.dosmakhambetbaktiyar.repository.SpecialtyRepository;
import com.dosmakhambetbaktiyar.service.SpecialtyService;

import java.util.List;

public class SpecialtyServiceImpl implements SpecialtyService {
    private SpecialtyRepository repository;

    public SpecialtyServiceImpl(SpecialtyRepository repository){
        this.repository = repository;
    }

    @Override
    public Specialty create(Specialty specialty) {
        if(specialty.getId() == null)
            return repository.create(specialty);

        return null;
    }

    @Override
    public Specialty get(Long id) {

        return repository.get(id);
    }

    @Override
    public List<Specialty> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return repository.delete(id);
    }

    @Override
    public Specialty update(Specialty specialty) {
        if(specialty.getId() != null)
            return repository.update(specialty);

        return null;
    }
}
