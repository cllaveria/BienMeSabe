/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.RecipeStep;

/**
 * Inteface with the service methods to insert the recipe steps
 * @author RAUL
 * @version 22/05/2021
 */
public interface RecipeStepService {
    /**
     * Method to create a recipe step
     * @param step object that represents the step of the recipe to persist
     * @return boolean that represents if the recipe ingredient has been successfully inserted or not
     */
    public Boolean insertRecipeStep(RecipeStep step);
    
    /**
     * Method to modify the recipe step in the table recipe steps of the DB by recipe id
     * @param step  object that represents the step of the recipe to persist
     * @return boolean that represents if the recipe step has been successfully updated or not
     */
    public boolean updateRecipeStep(RecipeStep step);
}
