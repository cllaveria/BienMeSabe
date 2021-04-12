/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.RecipeTypeDAO;
import com.bienmesabe.rest.domain.RecipeTypes;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface RecipeTypeDAO (repository)
 * @author RAUL RAMOS CENDRERO
 * @version 12/04/2021
 */
@Repository
public class RecipeTypeDAOImpl implements RecipeTypeDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    /**
     * Implementation of interface method to recover the recipes present in the DB
     * @return a list with the recipes in the DB
     */
    @Override
    @Transactional
    public List<RecipeTypes> getRecipeTypes() {
        Session currentSession = entityManager.unwrap(Session.class);
        
        Query<RecipeTypes> query = currentSession.createQuery("from RecipeTypes", RecipeTypes.class);
        
        List<RecipeTypes> recipeTypes = query.getResultList();
        
        return recipeTypes;
    }
    
}
