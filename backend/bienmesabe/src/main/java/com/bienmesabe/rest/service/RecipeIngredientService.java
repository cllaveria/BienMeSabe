/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.RecipeIngredients;

/**
 * Inteface with the service methods to insert the recipe ingredients
 * @author RAUL
 * @version 22/05/2021
 */
public interface RecipeIngredientService {
    /**
     * Method to create a recipe ingredient
     * @param ingredient string that represents the ingredient of the recipe to persist
     * @return boolean that represents if the recipe ingredient has been successfully inserted or not
     */
    public Boolean insertRecipeIngredient(String ingredient);
    
    /**
     * Method to modify the recipe ingredients in the table recipeingredients of the DB by recipe id
     * @param recipeIngredient  object that represents the ingredient of the recipe to persist
     * @return boolean that represents if the recipe ingredient has been successfully updated or not
     */
    public boolean updateRecipeIngredient(RecipeIngredients recipeIngredient);
}