package com.dosmakhambetbaktiyar.service.impl;

import com.dosmakhambetbaktiyar.model.Skill;
import com.dosmakhambetbaktiyar.repository.SkillRepository;
import com.dosmakhambetbaktiyar.service.SkillService;

import java.util.List;

public class SkillServiceImpl implements SkillService {
    private SkillRepository repository;

    public SkillServiceImpl(SkillRepository repository){
        this.repository = repository;
    }
    @Override
    public Skill create(Skill skill) {
        if(skill.getId() == null)
            return repository.create(skill);

        return null;
    }

    @Override
    public Skill get(Long id) {

        return repository.get(id);
    }

    @Override
    public List<Skill> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return repository.delete(id);
    }

    @Override
    public Skill update(Skill skill) {
        if(skill.getId() != null)
            return repository.update(skill);

        return null;
    }
}
