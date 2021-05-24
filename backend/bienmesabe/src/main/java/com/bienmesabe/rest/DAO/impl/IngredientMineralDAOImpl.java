/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.IngredientMineralDAO;
import com.bienmesabe.rest.domain.IngredientMineral;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface IngredientMineralDAO (repository)
 * @author RAUL
 * @version 19/05/2021
 */
@Repository
public class IngredientMineralDAOImpl implements IngredientMineralDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    
    /**
     * Implementation of interface method to recover the minerals present in the DB
     * @return a list with the minerals in the DB
     */
    @Override
    @Transactional
    public List<IngredientMineral> findAllMinerals() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<IngredientMineral> query = currentSession.createQuery("FROM IngredientMineral",IngredientMineral.class);
        return query.getResultList();
    }
    
}
