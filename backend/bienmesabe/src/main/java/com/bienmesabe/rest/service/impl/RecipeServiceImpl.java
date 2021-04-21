/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.RecipeDAO;
import com.bienmesabe.rest.domain.Recipe;
import com.bienmesabe.rest.domain.RecipeIngredients;
import com.bienmesabe.rest.service.RecipeService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface RecipeService (service)
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Service
public class RecipeServiceImpl implements RecipeService{

    /**
     * Bean of the recipe repository (Interface)
     */
    @Autowired
    private RecipeDAO recipeDAO;
    
    /**
     * Implementation of interface method to recover the recipes
     * @return a list with the recipes
     */
    @Override
    public List<Recipe> getAllRecipes() {
        return recipeDAO.getAllRecipes();
    }

    /**
     * Implementation of interface  to recover the recipes ordered by assessment
     * @return a list with the recipes ordered by assessment
     */
    public List<Recipe> getAllRecipesByAssessment(){
        return recipeDAO.getAllRecipesByAssessment();
    }
    /**
     * Implementation of interface method to recover the ingredient by ingredients
     * @param ingredientsForFilter list with the ingredients that must have the recipe
     * @return a list with the recipes filtered by ingredients
     */
    @Override
    public List<Recipe> getRecipeByIngredients(String ingredientsForFilter) {
        List<Long> listIngredientsForFilter = new ArrayList<Long>();
        String[] splittedIngredients = ingredientsForFilter.split(",");
        for(int i=0;i<splittedIngredients.length;i++){
            listIngredientsForFilter.add(Long.parseLong(splittedIngredients[i]));
        }
        return recipeDAO.getRecipeByIngredients(listIngredientsForFilter);
    }

    /**
     * Implementation of interface method to recover the recipes by kcal range
     * @param kcalMin integer that represents the mimimum kcal of the recipe to search
     * @param kcalMax integer that represents the maximum kcal of the recipe to search
     * @return a list with the recipes filtered by kcal range
     */
    @Override
    public List<Recipe> getRecipeByKCal(int kcalMin, int kcalMax) {
        return recipeDAO.getRecipeByKCal(kcalMin, kcalMax);
    }

    /**
     * Implementation of interface method to recover the recipes by type
     * @param type integer that represents the type of the recipe to search
     * @return a list with the recipes filtered by type
     */
    @Override
    public List<Recipe> getRecipeByType(int type) {
        return recipeDAO.getRecipeByType(type);
    }

    /**
     * Implementation of interface method to recover the recipes by dinners
     * @param dinners integer that represents the dinners of the recipe to search
     * @return a list with the recipes filtered by dinners
     */
    @Override
    public List<Recipe> getRecipesByDinners(int dinners) {
        return recipeDAO.getRecipesByDinners(dinners);
    }

    /**
     * Implementation of interface method to recover the recipes by the filters recived by parameter
     * @param data string with the filters
     * @return  a list with the recipes filtered by the filtered recived by parameter
     */
    @Override
    public List<Recipe> getRecipesByFilters(String data) {
        List<Recipe> recipesByFilters = new ArrayList<>();
        List<Recipe> recipesByIngredients = new ArrayList<>();
        List<Recipe> recipesByKcal = new ArrayList<>();
        List<Recipe> recipesByType = new ArrayList<>();
        int dinners = 1;
        boolean filterByIngredients = false, filterByTypes = false, filterByKCAL = false;
        String[] splittedData = data.split("_");
        for (int i = 0; i<splittedData.length;i++){
            String[] spplitedValues = splittedData[i].split("-");
            String key = spplitedValues[0];
            String values = spplitedValues[1];
            if(key.equals("ingredients")){
                filterByIngredients = true;
                List<Long> ingredientsId = new ArrayList<Long>();
                String[]splittedValueData = values.split(",");
                for(String ingredientId:splittedValueData){
                    ingredientsId.add(Long.parseLong(ingredientId));
                }
                recipesByIngredients = recipeDAO.getRecipeByIngredients(ingredientsId);
            }
            if(key.equals("kcal")){
                filterByKCAL = true;
                String[]splittedValueData = values.split(",");
                recipesByKcal = recipeDAO.getRecipeByKCal(Integer.parseInt(splittedValueData[0]), Integer.parseInt(splittedValueData[1]));
            }
            if(key.equals("type")){
                filterByTypes = true;
                recipesByType = recipeDAO.getRecipeByType(Integer.parseInt(values));
            }
            if(key.equals("dinners")){
                dinners = Integer.parseInt(values);
            }
        }
        
        if(filterByIngredients){
            recipesByFilters = recipesByIngredients;
        }else if (filterByKCAL){
            recipesByFilters = recipesByKcal;
        }else if(filterByTypes){
            recipesByFilters = recipesByType;
        }
            
        
        if(filterByKCAL)
            recipesByFilters = addRecipesNotExists(recipesByFilters, recipesByKcal);
        if(filterByTypes)
            recipesByFilters = addRecipesNotExists(recipesByFilters, recipesByType);

        for(Recipe recipe:recipesByFilters){
            for(RecipeIngredients recipeIngredient:recipe.getRecipeIngredients()){
                recipeIngredient.setIngredientQTY(((recipeIngredient.getIngredientQTY()/recipe.getRecipeDinners())* dinners));
            }
            recipe.setRecipeKCAL(((recipe.getRecipeKCAL()/recipe.getRecipeDinners())* dinners));
        }
        
        return recipesByFilters;

    }

    /**
     * Implementation of interface method to recover the recipe by id
     * @param id long that represents the id of the recipe to search
     * @return the recipe filtered by id
     */
    @Override
    public Recipe getRecipeById(Long id) {
        return recipeDAO.getRecipeById(id);
    }

    /**
     * Implementation of interface method to recover the recipes of the other users
     * @param userId long that represents the id of the user wich doesn't create the recipes
     * @return a list with the recipes in the DB filtered by user id
     */
    @Override
    public List<Recipe> getRecipesOfOtherUsers(Long userId) {
        return recipeDAO.getRecipesOfOtherUsers(userId);
    }
    
    /**
     * Implementation of interface method to create a recipe
     * @param recipe object that represents the recipe to persist
     * @return a long with the id of the persisted recipe
     */
    @Override
    public Long createRecipe(Recipe recipe) {
        return recipeDAO.createRecipe(recipe);
    }

    /**
     * Implementation of interface method to modify an recipe
     * @param recipe object that represents the recipe to modify
     */
    @Override
    public void modifyRecipe(Recipe recipe) {
        recipeDAO.modifyRecipe(recipe);
    }

    /**
     * Implementation of interface method to delete an recipe
     * @param id long with the id of the recipe to delete
     */
    @Override
    public void deleteRecipeById(long id) {
        recipeDAO.deleteRecipeById(id);
    }
    /**
     * Implementation of interface method to retian the coincident recipes of both lists
     * @param masterList list of recipes where retain all coincident recipes of the secondary list
     * @param secondaryList list of recipes to retain in the masterList
     * @return a list with the coincident recipes
     */
    private List<Recipe> addRecipesNotExists(List<Recipe> masterList, List<Recipe> secondaryList){
        masterList.retainAll(secondaryList);
        return masterList;
    }

    
}
