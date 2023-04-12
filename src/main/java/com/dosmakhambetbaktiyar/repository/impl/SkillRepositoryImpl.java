package com.dosmakhambetbaktiyar.repository.impl;

import com.dosmakhambetbaktiyar.model.Skill;
import com.dosmakhambetbaktiyar.model.Status;
import com.dosmakhambetbaktiyar.repository.SkillRepository;
import com.dosmakhambetbaktiyar.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SkillRepositoryImpl implements SkillRepository {
    @Override
    public Skill create(Skill skill) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(skill);
            transaction.commit();

            return skill;
        }catch (Exception e){
            System.err.println("Skill create() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public Skill get(Long aLong) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Skill skill = session.get(Skill.class,aLong);
            return skill;
        }catch (Exception e){
            System.err.println("Skill get() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Skill> getAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List skills = session.createQuery("from Skill where status = " + Status.ACTIVE.ordinal()).list();
            transaction.commit();

            return skills;
        }catch (Exception e){
            System.err.println("Skill getAll() error. " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean delete(Long aLong) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Skill skill = session.get(Skill.class,aLong);
            if(skill.getDevelopers().stream().noneMatch(developer -> developer.getStatus() == Status.ACTIVE)) {
                skill.setStatus(Status.DELETE);
                session.update(skill);
                transaction.commit();

                return true;
            }
        }catch (Exception e){
            System.err.println("Skill delete() error. " + e.getMessage());
        }

        return false;
    }

    @Override
    public Skill update(Skill skill) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(skill);
            transaction.commit();
            return skill;
        }catch (Exception e){
            System.err.println("Skill update() error. " + e.getMessage());
        }

        return null;
    }
}
