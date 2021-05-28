/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.Recipe;
import java.util.List;

/**
 * Inteface with the service methods to retrive the recipes
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
public interface RecipeService {
    
    /**
     * Method to recover the recipes
     * @return a list with the recipes
     */
    public List<Recipe> getAllRecipes();
    
    /**
     * Method to recover the recipes present in the DB ordered by assessment
     * @return a list with the recipes in the DB ordered by assessment
     */
    public List<Recipe> getAllRecipesByAssessment();
    
    /**
     * Method to recover the ingredient by ingredients
     * @param ingredientsForFilter list with the ingredients that must have the recipe
     * @return a list with the recipes filtered by ingredients
     */
    public List<Recipe> getRecipeByIngredients(String ingredientsForFilter);
    
    /**
     * Method to recover the recipes by kcal range
     * @param kcalMin integer that represents the mimimum kcal of the recipe to search
     * @param kcalMax integer that represents the maximum kcal of the recipe to search
     * @return a list with the recipes filtered by kcal range
     */
    public List<Recipe> getRecipeByKCal(int kcalMin, int kcalMax);
    
    /**
     * Method to recover the recipes by type
     * @param type integer that represents the type of the recipe to search
     * @return a list with the recipes filtered by type
     */
    public List<Recipe> getRecipeByType(int type);
    
    /**
     * Method to recover the recipes by dinners
     * @param dinners integer that represents the dinners of the recipe to search
     * @return a list with the recipes filtered by dinners
     */
    public List<Recipe> getRecipesByDinners(int dinners);
    
    /**
     * Method to recover the recipes by the filters recived by parameter
     * @param data string with the filters
     * @return  a list with the recipes filtered by the filtered recived by parameter
     */
    public List<Recipe> getRecipesByFilters(String data);
    
    /**
     * Method to recover the recipe by id
     * @param id long that represents the id of the recipe to search
     * @return the recipe filtered by id
     */
    public Recipe getRecipeById(Long id);
    
    /**
     * Method to recover the recipes of the user
     * @param userId long that represents the id of the user that creates the recipes
     * @return a list with the recipes in the DB filtered by user id
     */
    public List<Recipe> getRecipesOfUser(Long userId);
    
    /**
     * Method to recover the recipes of the other users
     * @param userId long that represents the id of the user wich doesn't create the recipes
     * @return a list with the recipes in the DB filtered by user id
     */
    public List<Recipe> getRecipesOfOtherUsers(Long userId);
    
    
    /**
     * Method to recover the recipes that are not active
     * @return a list with the recipes that are not active
     */
    public List<Recipe> getRecipesNotActive();
            
            
    /**
     * Method to create a recipe
     * @param recipe object that represents the recipe to persist
     * @return a long with the id of the persisted recipe
     */
    public Long createRecipe(Recipe recipe);
    
    /**
     * Method to modify an recipe
     * @param recipe object that represents the recipe to modify
     */
    public void modifyRecipe(Recipe recipe);
    
    /**
     * Method to update the image path of a recipe in the table recipes of the DB
     * @param path string with the path of the recipe image
     * @param recipeId long that represents the id of the recipe
     * @return a boolean that represents if the path of the recipe image has been successfully updated or not
     */
    public boolean updateImageRecipePath(String path, long recipeId);
    
    /**
     * Method to asign a recipe as active
     * @param id long that represents the id of the recipe
     * @return a boolean that indicates if the recipe has been successfully set as active or not
     */
    public boolean setRecipeAsActive(Long id);
    
    /**
     * Method to delete an recipe
     * @param id long with the id of the recipe to delete
     */
    public void deleteRecipeById(long id);
}
