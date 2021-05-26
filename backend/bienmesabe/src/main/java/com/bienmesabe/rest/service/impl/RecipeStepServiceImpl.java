/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.RecipeStepDAO;
import com.bienmesabe.rest.domain.RecipeStep;
import com.bienmesabe.rest.service.RecipeStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface RecipeStepService (service)
 * @author RAUL
 * @version 22/05/2021
 */
@Service
public class RecipeStepServiceImpl implements RecipeStepService{
    
    /**
     * Bean of the recipe step repository (Interface)
     */
    @Autowired
    private RecipeStepDAO recipeStepDAO;
    
    /**
     * Implementation of interface method to create a recipe step
     * @param step object that represents the step of the recipe to persist
     * @return boolean that represents if the recipe ingredient has been successfully inserted or not
     */
    @Override
    public Boolean insertRecipeStep(RecipeStep step) {
        return recipeStepDAO.insertRecipeStep(step);
    }
    
    /**
     * Implementation of interface method to modify the recipe step in the table recipe step of the DB by recipe id
     * @param step  object that represents the step of the recipe to persist
     * @return boolean that represents if the recipe step has been successfully updated or not
     */
    @Override
    public boolean updateRecipeStep(RecipeStep step) {
        return recipeStepDAO.updateRecipeStep(step);
    }

    /**
     * Implementation of interface method to delete the recipe steps of the recipe
     * @param recipeId  long that represents the id of the recipe to update
     * @return boolean that represents if the recipe steps has been successfully deleted or not
     */
    @Override
    public boolean deleteRecipeSteps(long recipeId) {
        return recipeStepDAO.deleteRecipeSteps(recipeId);
    }
}
