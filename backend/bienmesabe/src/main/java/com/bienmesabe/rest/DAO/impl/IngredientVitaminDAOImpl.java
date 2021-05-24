/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.IngredientVitaminDAO;
import com.bienmesabe.rest.domain.IngredientVitamin;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface IngredientVitaminDAO (repository)
 * @author RAUL
 * @version 19/05/2021
 */
@Repository
public class IngredientVitaminDAOImpl implements IngredientVitaminDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    /**
     * Implementation of interface method to recover the vitamins present in the DB
     * @return a list with the vitamins in the DB
     */
    @Override
    @Transactional
    public List<IngredientVitamin> findAllVitamins() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<IngredientVitamin> query = currentSession.createQuery("FROM IngredientMineral",IngredientVitamin.class);
        return query.getResultList();
    }
    
}
