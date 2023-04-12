package com.dosmakhambetbaktiyar.repository.impl;


import com.dosmakhambetbaktiyar.model.Specialty;
import com.dosmakhambetbaktiyar.model.Status;
import com.dosmakhambetbaktiyar.repository.SpecialtyRepository;
import com.dosmakhambetbaktiyar.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SpecialtyRepositoryImpl implements SpecialtyRepository {
    @Override
    public Specialty create(Specialty specialty) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(specialty);
            transaction.commit();
            return specialty;
        }catch (Exception e){
            System.err.println("Specialty create() error. " + e.getMessage());
        }

        return null;
    }

    @Override
    public Specialty get(Long aLong) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Specialty specialty = session.get(Specialty.class,aLong);
            return specialty;
        }catch (Exception e){
            System.err.println("Specialty get() error. " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Specialty> getAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List specialties = session.createQuery("from Specialty where status = " + Status.ACTIVE.ordinal()).list();
            transaction.commit();

            return specialties;
        }catch (Exception e){
            System.err.println("Specialty getAll() error. " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean delete(Long aLong) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Specialty specialty = session.get(Specialty.class,aLong);
            if(specialty.getDevelopers().stream().noneMatch(developer -> developer.getStatus() == Status.ACTIVE)) {
                specialty.setStatus(Status.DELETE);
                session.update(specialty);
                transaction.commit();
                return true;
            }
        }catch (Exception e){
            System.err.println("Specialty delete() error. " + e.getMessage());
        }
        return false;
    }

    @Override
    public Specialty update(Specialty specialty) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(specialty);
            transaction.commit();
            return specialty;
        }catch (Exception e){
            System.err.println("Specialty update() error. " + e.getMessage());
        }

        return null;
    }
}
