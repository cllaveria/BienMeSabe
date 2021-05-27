/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.IngredientDAO;
import com.bienmesabe.rest.DAO.RecipeIngredientDAO;
import com.bienmesabe.rest.domain.Ingredient;
import com.bienmesabe.rest.domain.RecipeIngredients;
import com.bienmesabe.rest.service.RecipeIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface RecipeIngredientService (service)
 * @author RAUL
 * @version 22/05/2021
 */
@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService{
    
    /**
     * Bean of the recipe ingredient repository (Interface)
     */
    @Autowired
    private RecipeIngredientDAO recipeIngredientDAO;
    
    /**
     * Bean of the ingredient repository (Interface)
     */
    @Autowired
    private IngredientDAO ingredientDAO;
    
    /**
     * Implementation of interface method to create a recipe ingredient
     * @param ingredient object that represents the ingredient of the recipe to persist
     * @return boolean that represents if the recipe ingredient has been successfully inserted or not
     */
    @Override
    public Boolean insertRecipeIngredient(String ingredient) {
        String[] splittedData = ingredient.split("___");
        long recipeId = 0L, ingredientId = 0L;
        float ingredientQTY = 0F, ingredientKCAL = 0F;
        String ingredientUnity = "";
        for (int i = 0; i<splittedData.length;i++){
            String[] spplitedValues = splittedData[i].split("---");
            String key = spplitedValues[0];
            String values = spplitedValues[1];
            if(key.equals("recipe") && values != ""){
                recipeId = Long.parseLong(values);
            }else if(key.equals("id") && values != ""){
               ingredientId = Long.parseLong(values); 
            }else if(key.equals("qty") && values != ""){
               ingredientQTY = Float.parseFloat(values);
            }else if(key.equals("unity") && values != ""){
               ingredientUnity = values;
            }
        }
        Ingredient ingredientR = ingredientDAO.findIngredientById(ingredientId);
        RecipeIngredients newIngredient = new RecipeIngredients();
        try{
            newIngredient.setRecipeId(recipeId);
            newIngredient.setIngredientId(ingredientId);
            if(ingredientR != null){
                newIngredient.setIngredientUnity(ingredientUnity);
                ingredientKCAL = ingredientR.getKcal()*ingredientQTY /100;
                newIngredient.setIngredientQTY(ingredientQTY);
                newIngredient.setIngredientKCAL(ingredientKCAL);
                return recipeIngredientDAO.insertRecipeIngredient(newIngredient);
            }else{
                return false;
            }
        }catch(Exception ee){
            String errore = ee.toString();
            return false;
        }
       
    }

    /**
     * Implementation of interface method to modify the recipe ingredients in the table recipeingredients of the DB by recipe id
     * @param recipeIngredient  object that represents the ingredient of the recipe to persist
     * @return boolean that represents if the recipe ingredient has been successfully updated or not
     */
    @Override
    public boolean updateRecipeIngredient(RecipeIngredients recipeIngredient) {
        return recipeIngredientDAO.updateRecipeIngredient(recipeIngredient);
    }
    /**
     * Implementation of interface method to update the recipe Kcal in the table recipes of the DB
     * @param recipeId  long that represents the id of the recipe to update
     * @return boolean that represents if the recipe KCal has been successfully updated or not
     */
    @Override
    public boolean updateKCALOfRecipe(long recipeId){
        return recipeIngredientDAO.updateKCALOfRecipe(recipeId);
    }

    /**
     * Implementation of interface method to delete the recipe ingredients of the recipe
     * @param recipeId  long that represents the id of the recipe to update
     * @return boolean that represents if the recipe ingredients has been successfully deleted or not
     */
    @Override
    public boolean deleteRecipeIngredients(long recipeId) {
        return recipeIngredientDAO.deleteRecipeIngredients(recipeId);
    }
    
    
}
