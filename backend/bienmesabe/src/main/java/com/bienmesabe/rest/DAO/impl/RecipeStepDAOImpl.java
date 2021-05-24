/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.RecipeStepDAO;
import com.bienmesabe.rest.domain.RecipeStep;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface RecipeStepDAO (repository)
 * @author RAUL
 * @version 22/05/2021
 */
@Repository
public class RecipeStepDAOImpl implements RecipeStepDAO{
    
    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;

    
    /**
     * Implementation of interface method to create a recipe step in the table recipesteps of the DB
     * @param step object that represents the step of the recipe to persist
     * @return boolean that represents if the recipe ingredient has been successfully inserted or not
     */
    @Override
    public Boolean insertRecipeStep(RecipeStep step) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<RecipeStep> query = currentSession.createQuery("FROM RecipeStep WHERE stepDescription=:description and recipe=:recipeId and image=:stepImage", RecipeStep.class);
        query.setParameter("description", step.getStepDescription());
        query.setParameter("recipeId", step.getRecipeId());
        query.setParameter("stepImage", step.getImage());
        RecipeStep recipeStepInDB = new RecipeStep();
        try{
             recipeStepInDB= query.getSingleResult();
        }catch(Exception e){
            recipeStepInDB = null;
        }
        if(recipeStepInDB==null){
            currentSession.save(step); 
            return true;
        }
        return false;
    }

    /**
     * Implementation of interface method to modify the recipe step in the table recipe step of the DB by recipe id
     * @param step  object that represents the step of the recipe to persist
     * @return boolean that represents if the recipe step has been successfully updated or not
     */
    @Override
    @Transactional
    public boolean updateRecipeStep(RecipeStep step) {
         Session currentSession = entityManager.unwrap(Session.class);
        try{
            currentSession.saveOrUpdate(step);
            return true;
        }catch(Exception ee){
            return false;
        }
    }
}
