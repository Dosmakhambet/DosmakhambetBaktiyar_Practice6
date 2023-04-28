package com.dosmakhambetbaktiyar.repository.impl;

import com.dosmakhambetbaktiyar.model.Developer;
import com.dosmakhambetbaktiyar.model.Skill;
import com.dosmakhambetbaktiyar.model.Status;
import com.dosmakhambetbaktiyar.repository.DeveloperRepository;
import com.dosmakhambetbaktiyar.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public Developer create(Developer developer) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(developer);
            transaction.commit();
            return developer;
        }catch (Exception e){
            System.err.println("Developer create() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public Developer get(Long aLong) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Developer developer = session.get(Developer.class,aLong);
            return developer;
        }catch (Exception e){
            System.err.println("Developer get() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Developer> getAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            List developers = session.createQuery("from Developer where status = " + Status.ACTIVE.ordinal()).list();
            List<Developer> developersList = new ArrayList<>();

            for(int i = 0; i < developers.size(); i++){
                Developer developer = (Developer) developers.get(i);
                developer.setSkills(developer.getSkills().stream().map(skill -> new Skill(skill.getId(),skill.getName())).collect(Collectors.toSet()));
                developersList.add(developer);
            }
            transaction.commit();


            return developersList;
        }catch (Exception e){
            System.err.println("Developer getAll() error. " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean delete(Long aLong) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Developer developer = session.get(Developer.class,aLong);
            developer.setStatus(Status.DELETE);
            session.update(developer);
            transaction.commit();

            return true;
        }catch (Exception e){
            System.err.println("Developer delete() error. " + e.getMessage());
        }

        return false;
    }

    @Override
    public Developer update(Developer developer) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(developer);
            transaction.commit();
            return developer;
        }catch (Exception e){
            System.err.println("Developer update() error. " + e.getMessage());
        }

        return null;
    }
}
