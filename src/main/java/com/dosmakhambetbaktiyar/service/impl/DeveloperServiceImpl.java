package com.dosmakhambetbaktiyar.service.impl;

import com.dosmakhambetbaktiyar.model.Developer;
import com.dosmakhambetbaktiyar.repository.DeveloperRepository;
import com.dosmakhambetbaktiyar.service.DeveloperService;

import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {
    private DeveloperRepository repository;

    public DeveloperServiceImpl(DeveloperRepository repository){
        this.repository = repository;
    }
    @Override
    public Developer create(Developer developer) {
        if(developer.getId() == null)
            return repository.create(developer);

        return null;
    }

    @Override
    public Developer get(Long id) {

        return repository.get(id);
    }

    @Override
    public List<Developer> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return repository.delete(id);
    }

    @Override
    public Developer update(Developer developer) {
        if(developer.getId() != null)
            return repository.update(developer);

        return null;
    }
}
