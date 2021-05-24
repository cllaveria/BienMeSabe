/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.Recipe;
import java.util.List;

/**
 * Inteface with the methods to retrive the DB data of recipes
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
public interface RecipeDAO {
    
    /**
     * Method to recover the recipes present in the DB
     * @return a list with the recipes in the DB
     */
    public List<Recipe> getAllRecipes();
    
    /**
     * Method to recover the recipes present in the DB ordered by assessment
     * @return a list with the recipes in the DB ordered by assessment
     */
    public List<Recipe> getAllRecipesByAssessment();
    
    /**
     * Method to recover the ingredient present in the DB by ingredients
     * @param ingredientsForFilter string that represents the list of the ingredients that must have the recipe
     * @return a list with the recipes in the DB filtered by ingredients
     */
    public List<Recipe> getRecipeByIngredients(List<Long> ingredientsForFilter);
    
    /**
     * Method to recover the recipes present in the DB by kcal range
     * @param kcalMin integer that represents the mimimum kcal of the recipe to search
     * @param kcalMax integer that represents the maximum kcal of the recipe to search
     * @return a list with the recipes in the DB filtered by kcal range
     */
    public List<Recipe> getRecipeByKCal(int kcalMin, int kcalMax);
    
    /**
     * Method to recover the recipes present in the DB by type
     * @param type integer that represents the type of the recipe to search
     * @return a list with the recipes in the DB filtered by type
     */
    public List<Recipe> getRecipeByType(int type);
    
    /**
     * Method to recover the recipes present in the DB by dinners
     * @param dinners integer that represents the dinners of the recipe to search
     * @return a list with the recipes in the DB filtered by dinners
     */
    public List<Recipe> getRecipesByDinners(int dinners);
    
    /**
     * Method to recover the recipe present in the DB by id
     * @param id long that represents the id of the recipe to search
     * @return the recipe in the DB filtered by id
     */
    public Recipe getRecipeById(Long id);
    
    /**
     * Method to recover the recipes of the user
     * @param userId long that represents the id of the user wich doesn't create the recipes
     * @return a list with the recipes in the DB filtered by user
     */
    public List<Recipe> getRecipesOfUser(Long userId);
    
    /**
     * Method to recover the recipes of the other users
     * @param userId long that represents the id of the user wich doesn't create the recipes
     * @return a list with the recipes in the DB filtered by user
     */
    public List<Recipe> getRecipesOfOtherUsers(Long userId);
    
    /**
     * Method to recover the recipes that are not active
     * @return a list with the recipes that not are active in the DB
     */
    public List<Recipe> getRecipesNotActive();
    
    /**
     * Method to create a recipe in the table recipes of the DB
     * @param recipe object that represents the recipe to persist
     * @return a long with the id of the persisted recipe
     */
    public Long createRecipe(Recipe recipe);
    
    /**
     * Method to modify an recipe in the table recipes of the DB
     * @param recipe object that represents the recipe to modify
     */
    public void modifyRecipe(Recipe recipe);
    
    /**
     * Method to asign a recipe as active
     * @param id long that represents the id of the recipe to activate
     * @return boolean that represents if the recipe has been successfully activated or not
     */
    public Boolean setRecipeAsActive(Long id);
    
    /**
     * Method to delete an recipe in the table recipes of the DB by id
     * @param id long with the id of the recipe to delete
     */
    public void deleteRecipeById(long id);
}
