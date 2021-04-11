/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.NutricionistDAO;
import com.bienmesabe.rest.domain.Nutricionist;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface NutricionistDAO (repository)
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Repository
public class NutricionistDAOImpl implements NutricionistDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    /**
     * Implementation of interface method to recover the nutricionists present in the DB
     * @return a list with the nutricionists present in the DB
     */
    @Override
    @Transactional
    public List<Nutricionist> findAllNutricionist() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Nutricionist> query = currentSession.createQuery("from Nutricionist", Nutricionist.class);
        List<Nutricionist> nutricionists = query.getResultList();
        return nutricionists;
    }

    /**
     * Implementation of interface method to recover the nutricionists present in the DB by id
     * @param id long that represents the id of the nutricionist to search
     * @return the nutricionist in the DB by id
     */
    @Override
    @Transactional
    public Nutricionist findNutricionistById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Nutricionist nutricionist = currentSession.get(Nutricionist.class, id);
        return nutricionist;
    }

    /**
     * Implementation of interface method to recover the nutricionists present in the DB by id
     * @param cp string that represents the postal code of the nutricionists to search
     * @return the nutricionist filtered by postal code
     */
    @Override
    @Transactional
    public Nutricionist findNutricionistByCP(String cp) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Nutricionist> query = currentSession.createQuery("FROM Nutricionist WHERE companyPostalCode=:companyPostalCode", Nutricionist.class);
        query.setParameter("companyPostalCode", cp);
        Nutricionist nutricionist = query.getSingleResult();
        return nutricionist;
    }

    /**
     * Implementation of interface method to recover the nutricionist present in the DB by postal code range
     * @param cpMin string that represents the mimimum postal code of the nutricionists to search
     * @param cpMax string that represents the maximum postal code of the nutricionists to search
     * @return a list with the nutricionists filtered by postal code range
     */
    @Override
    @Transactional
    public List<Nutricionist> findNutricionistByCPRange(String cpMin, String cpMax) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Nutricionist> query = currentSession.createQuery("FROM Nutricionist WHERE companyPostalCode>=:minCP and companyPostalCode<= maxCP", Nutricionist.class);
        query.setParameter("minCP", cpMin);
        query.setParameter("maxCP", cpMax);
        List<Nutricionist> nutricionists = query.getResultList();
        return nutricionists;
    }

    /**
     * Implementation of interface method to create an nutricionist in the table nutricionists of the DB
     * @param nutricionist object that represents the nutricionist to persist
     * @return a long with the id of the persisted nutricionist
     */
    @Override
    @Transactional
    public Long createNutricionist(Nutricionist nutricionist) {
        Session currentSession = entityManager.unwrap(Session.class);
        
        Query<Nutricionist> query = currentSession.createQuery("FROM Nutricionist WHERE companyName=:name and companyDirection=:direction and companyPostalCode=:postalCode", Nutricionist.class);
        query.setParameter("name", nutricionist.getName());
        query.setParameter("direction", nutricionist.getCompanyDirection());
        query.setParameter("postalCode", nutricionist.getCompanyPostalCode());
        Nutricionist nutricionistInDB = new Nutricionist();
        try{
             nutricionistInDB= query.getSingleResult();
        }catch(Exception e){
            nutricionistInDB = null;
        }
        if(nutricionistInDB==null){
            Long idGenerado = (Long) currentSession.save(nutricionist); 
            return idGenerado;
        }
        return 0L;
    }

    /**
     * Implementation of interface method to modify an nutricionist in the table nutricionists of the DB
     * @param nutricionist object that represents the nutricionist to modify
     */
    @Override
    @Transactional
    public void modifyNutricionist(Nutricionist nutricionist) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(nutricionist);
    }

    /**
     * Implementation of interface method to delete an nutricionist in the table nutricionists of the DB by id
     * @param id long with the id of the nutricionist to delete
     */
    @Override
    @Transactional
    public void deleteNutricionistById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Nutricionist> query = currentSession.createQuery("delete from Nutricionist where id=:nutricionistId");
        query.setParameter("nutricionistId", id);
        query.executeUpdate();
    }
    
}
