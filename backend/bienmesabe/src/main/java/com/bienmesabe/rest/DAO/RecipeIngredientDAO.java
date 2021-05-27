/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.RecipeIngredients;
import java.util.List;


/**
 * Inteface with the methods to insert the DB data of recipe ingredients
 * @author RAUL
 * @version 22/05/2021
 */
public interface RecipeIngredientDAO {
    /**
     * Method to create a recipe ingredient in the table recipeingredients of the DB
     * @param ingredient object that represents the ingredient of the recipe to persist
     * @return boolean that represents if the recipe ingredient has been successfully inserted or not
     */
    public boolean insertRecipeIngredient(RecipeIngredients ingredient);
    
    /**
     * Method to retrieve the recipe ingredients in the table recipeingredients of the DB by recipe id
     * @param id long that represents the id of the recipe to search
     * @return the list of the recipe ingredients of the indicated recipe
     */
    public List<RecipeIngredients> getRecipeIngredientsById(long id);
    
    /**
     * Method to modify the recipe ingredients in the table recipeingredients of the DB by recipe id
     * @param recipeIngredient  object that represents the ingredient of the recipe to persist
     * @return boolean that represents if the recipe ingredient has been successfully updated or not
     */
    public boolean updateRecipeIngredient(RecipeIngredients recipeIngredient);
    
    
    /**
     * Method to update the recipe Kcal in the table recipes of the DB
     * @param recipeId  long that represents the id of the recipe to update
     * @return boolean that represents if the recipe KCal has been successfully updated or not
     */
    public boolean updateKCALOfRecipe(long recipeId);
    
    /**
     * Method to delete the recipe ingredients of the recipe in the table recipe ingredient of the DB
     * @param recipeId  long that represents the id of the recipe to update
     * @return boolean that represents if the recipe ingredients has been successfully deleted or not
     */
    public boolean deleteRecipeIngredients(long recipeId);
}
