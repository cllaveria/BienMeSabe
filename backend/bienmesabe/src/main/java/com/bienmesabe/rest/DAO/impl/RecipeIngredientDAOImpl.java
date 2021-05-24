/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.IngredientDAO;
import com.bienmesabe.rest.DAO.RecipeIngredientDAO;
import com.bienmesabe.rest.domain.Ingredient;
import com.bienmesabe.rest.domain.RecipeIngredients;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface RecipeIngredientDAO (repository)
 * @author RAUL
 * @version 22/05/2021
 */
@Repository
public class RecipeIngredientDAOImpl implements RecipeIngredientDAO{
    
    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private IngredientDAO ingredientDAO;
    
    /**
     * Implementation of interface method to create a recipe ingredient in the table recipeingredients of the DB
     * @param ingredient object that represents the ingredient of the recipe to persist
     * @return boolean that represents if the recipe ingredient has been successfully inserted or not
     */
    @Override
    @Transactional
    public boolean insertRecipeIngredient(RecipeIngredients ingredient) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<RecipeIngredients> query = currentSession.createQuery("FROM RecipeIngredients WHERE ingredientId=:ingredientId and ingredientQTY=:qty and ingredientUnity=:unity and recipeId=:recipe", RecipeIngredients.class);
        query.setParameter("ingredientId", ingredient.getIngredientId());
        query.setParameter("qty", ingredient.getIngredientQTY());
        query.setParameter("unity", ingredient.getIngredientUnity());
        query.setParameter("recipe", ingredient.getRecipeId());
        RecipeIngredients recipeIngredientInDB = new RecipeIngredients();
        try{
             recipeIngredientInDB= query.getSingleResult();
        }catch(Exception e){
            recipeIngredientInDB = null;
        }
        if(recipeIngredientInDB==null){
            currentSession.save(ingredient); 
            return updateKCALOfRecipe(ingredient.getRecipeId());
        }
        return false;
    }
    
    /**
     * Method to retrieve all the recipe ingredients by id
     * @param id long that represents the id of the recipe
     * @return a list with all of the ingredients of the indicated recipe
     */
    @Override
    @Transactional
    public List<RecipeIngredients> getRecipeIngredientsById(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<RecipeIngredients> query = currentSession.createQuery("FROM RecipeIngredients WHERE recipeId=:recipeId", RecipeIngredients.class);
        query.setParameter("recipeId", id);
        return query.getResultList();
        
    }
    
    /**
     * Method to retrieve the total amount Kcal of the recipe
     * @param recipeIngredients list with all of the ingredients of the recipe
     * @return a float with the total amount of Kcal of the recipe
     */
    @Transactional
    private float getKCalByIngredients(List<RecipeIngredients> recipeIngredients){
        float kcal = 0F;
        if(recipeIngredients.size() > 0){
            for(RecipeIngredients ingredient : recipeIngredients){
                kcal+=ingredient.getIngredientKCAL();
            }
            return kcal;
        }else{
            return 0F;
        }
    }
    
    /**
     * Method to update the total amount Kcal of the recipe
     * @param kcal float with the total amount of Kcal of the recipe
     * @param recipeId long that represents the id of the recipe
     * @return a boolean that indicates if the total amount of the Kcal of the recipe has been updated successfully or not
     */
    @Transactional
    private boolean updateRecipeKcal(float kcal, long recipeId){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<RecipeIngredients> query = currentSession.createQuery("Update Recipe set recipeKCAL=:kcals WHERE id=:recipeId");
        query.setParameter("kcals", kcal);
        query.setParameter("recipeId", recipeId);
        try{
            query.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
        
    }

    /**
     * Implementation of interface method to modify the recipe ingredients in the table recipeingredients of the DB by recipe id
     * @param recipeIngredient  object that represents the ingredient of the recipe to persist
     * @return boolean that represents if the recipe ingredient has been successfully updated or not
     */
    @Override
    @Transactional
    public boolean updateRecipeIngredient(RecipeIngredients recipeIngredient){
        Session currentSession = entityManager.unwrap(Session.class);
        try{
            Ingredient ingredientR = ingredientDAO.findIngredientById(recipeIngredient.getIngredientId());
            recipeIngredient.setIngredientKCAL(ingredientR.getKcal()*recipeIngredient.getIngredientQTY() /100);
            Query<RecipeIngredients> query = currentSession.createQuery("Update RecipeIngredients set ingredientQTY=:qty,ingredientUnity=:unity, ingredientKCAL=:kcal WHERE ingredientId=:ingredientId");
            query.setParameter("qty", recipeIngredient.getIngredientQTY());
            query.setParameter("unity", recipeIngredient.getIngredientUnity());
            query.setParameter("kcal", recipeIngredient.getIngredientKCAL());
            query.setParameter("ingredientId", recipeIngredient.getIngredientId());
            query.executeUpdate();
            return true;
        }catch(Exception ee){
            return false;
        }
        
    }
    
    /**
     * Implementation of interface method to update the recipe Kcal in the table recipes of the DB
     * @param recipeId  long that represents the id of the recipe to update
     * @return boolean that represents if the recipe KCal has been successfully updated or not
     */
    @Override
    @Transactional
    public boolean updateKCALOfRecipe(long recipeId){
        List<RecipeIngredients> ingredients = getRecipeIngredientsById(recipeId);
        float kcal = getKCalByIngredients(ingredients);
        return updateRecipeKcal(kcal,recipeId);
    }
}
