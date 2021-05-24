/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.RecipeDAO;
import com.bienmesabe.rest.domain.Recipe;
import com.bienmesabe.rest.domain.RecipeIngredients;
import com.bienmesabe.rest.domain.RecipeStep;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface RecipeDAO (repository)
 * @author RAUL RAMOS CENDRERO
 * @version 12/04/2021
 */
@Repository
public class RecipeDAOImpl implements RecipeDAO {

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    /**
     * Implementation of interface method to recover the recipes present in the DB
     * @return a list with the recipes in the DB
     */
    @Override
    @Transactional
    public List<Recipe> getAllRecipes() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Recipe> query = currentSession.createQuery("from Recipe r where r.active=1 order by createdAt desc", Recipe.class);       
        List<Recipe> recipes = query.getResultList();
        return recipes;
    }
    
    /**
     * Implementation of interface method to recover the recipes present in the DB ordered by assessment
     * @return a list with the recipes in the DB ordered by assessment
     */
    @Override
    @Transactional
    public List<Recipe> getAllRecipesByAssessment(){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Recipe> query = currentSession.createQuery("from Recipe order by recipeAssessment desc", Recipe.class);
        List<Recipe> recipes = query.getResultList();
        return recipes;
    }
    
    /**
     * Implementation of interface method to recover the ingredient present in the DB by ingredients
     * @param ingredientsForFilter list with the ingredients that must have the recipe
     * @return a list with the recipes in the DB filtered by ingredients
     */
    @Override
    @Transactional
    public List<Recipe> getRecipeByIngredients(List<Long> ingredientsForFilter) {
        List<Recipe> recipes = new ArrayList<Recipe>();
        List<Recipe> recipesInDB = getAllRecipes();
        for(int i=0;i<recipesInDB.size();i++){
            int findedIngredients = 0;
            List<Boolean> hasIngredients = new ArrayList<Boolean>();
            for(int s=0;s<ingredientsForFilter.size();s++){
                hasIngredients.add(false);
            }
            List<RecipeIngredients> recipeIngredients = recipesInDB.get(i).getRecipeIngredients();
            List<Long> recipeIngredientsIds = new ArrayList<Long>();
            for(RecipeIngredients ingredient:recipeIngredients){
                recipeIngredientsIds.add(ingredient.getIngredientId());
            }
            for(int x=0;x<ingredientsForFilter.size();x++){
                if(recipeIngredientsIds.contains(ingredientsForFilter.get(x))){
                    hasIngredients.set(x, Boolean.TRUE);
                    findedIngredients++;
                }
            }
            if(((hasIngredients.contains(false) || !hasIngredients.contains(false)) && recipesInDB.get(i).getRecipeIngredients().size() <= findedIngredients)){
                recipes.add(recipesInDB.get(i));
            }
        }
        Collections.sort(recipes, (Recipe o1, Recipe o2) -> o1.getRecipeAssessment() - o2.getRecipeAssessment());
        return recipes;
    }

    /**
     * Implementation of interface method to recover the recipes present in the DB by kcal range
     * @param kcalMin integer that represents the mimimum kcal of the recipe to search
     * @param kcalMax integer that represents the maximum kcal of the recipe to search
     * @return a list with the recipes in the DB filtered by kcal range
     */
    @Override
    @Transactional
    public List<Recipe> getRecipeByKCal(int kcalMin, int kcalMax) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Recipe> query = currentSession.createQuery("from Recipe where recipeKCAL between :kMin  and :kMax ORDER BY recipeKCAL ASC", Recipe.class);
        query.setParameter("kMin", (float)kcalMin);
        query.setParameter("kMax", (float)kcalMax);
        List<Recipe> recipes = query.getResultList();
        return recipes;
    }

    /**
     * Implementation of interface method to recover the recipes present in the DB by type
     * @param type integer that represents the type of the recipe to search
     * @return a list with the recipes in the DB filtered by type
     */
    @Override
    @Transactional
    public List<Recipe> getRecipeByType(int type) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Recipe> query = currentSession.createQuery("from Recipe where type=:recipeType order by recipeAssessment DESC");
        query.setParameter("recipeType", type);
        List<Recipe> recipes = query.getResultList();
        return recipes;
    }

    /**
     * Implementation of interface method to recover the recipes present in the DB by dinners
     * @param dinners integer that represents the dinners of the recipe to search
     * @return a list with the recipes in the DB filtered by dinners
     */
    @Override
    @Transactional
    public List<Recipe> getRecipesByDinners(int dinners) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Recipe> query = currentSession.createQuery("from Recipe where recipeDinners=:dinners");
        query.setParameter("dinners", dinners);
        List<Recipe> recipes = query.getResultList();
        return recipes;
    }
    
    /**
     * Implementation of interface method to recover the recipe present in the DB by id
     * @param id long that represents the id of the recipe to search
     * @return the recipe in the DB filtered by id
     */
    @Override
    @Transactional
    public Recipe getRecipeById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Recipe recipe = currentSession.get(Recipe.class, id);
        return recipe;
    }
    
    /**
     * Implementation of interface method to recover the recipes present in the DB by user id
     * @param userId long that represents the id of the user to not search
     * @return the recipes in the DB filtered by user id
     */
    @Override
    @Transactional
    public List<Recipe> getRecipesOfOtherUsers(Long userId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Recipe> query = currentSession.createQuery("from Recipe where userId<>:userId");
        query.setParameter("userId", userId);
        List<Recipe> recipes = query.getResultList();
        return recipes;
    }
    
    /**
     * Implementation of interface method to recover the recipes present in the DB by user id
     * @param userId long that represents the id of the user to search
     * @return the recipes in the DB filtered by user id
     */
    @Override
    @Transactional
    public List<Recipe> getRecipesOfUser(Long userId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Recipe> query = currentSession.createQuery("from Recipe where userId=:userId");
        query.setParameter("userId", userId);
        List<Recipe> recipes = query.getResultList();
        return recipes;
    }
    
    /**
     * Implementation of interface method to recover the recipes that are not active
     * @return a list with the recipes that not are active in the DB
     */
    @Override
    @Transactional
    public List<Recipe> getRecipesNotActive(){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Recipe> query = currentSession.createQuery("from Recipe r where r.active=0");
        List<Recipe> recipes = query.getResultList();
        return recipes;
    }

    /**
     * Implementation of interface method to create a recipe in the table recipes of the DB
     * @param recipe object that represents the recipe to persist
     * @return a long with the id of the persisted recipe
     */
    @Override
    @Transactional
    public Long createRecipe(Recipe recipe) {
        Session currentSession = entityManager.unwrap(Session.class);
        Long idGenerado = (Long) currentSession.save(recipe); 
        return idGenerado;
    }

    /**
     * Implementation of interface method to modify an recipe in the table recipes of the DB
     * @param recipe object that represents the recipe to modify
     */
    @Override
    @Transactional
    public void modifyRecipe(Recipe recipe) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(recipe); 
    }

    /**
     * Implementation of interface method to set active a recipe in the table recipes by id
     * @param id long with the id of the recipe to activate
     * @return boolean that represents if the recipe is correctly activated or not
     */
    @Override
    @Transactional
    public Boolean setRecipeAsActive(Long id){
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Recipe> query = currentSession.createQuery("update from Recipe r set active=1 where id=:recipeId");

        query.setParameter("recipeId", id);
        try{
            query.executeUpdate();
            return true;
        }catch(Exception ee){
            return false;
        }
        
    }

    /**
     * Implementation of interface method to delete an recipe in the table recipes of the DB by id
     * @param id long with the id of the recipe to delete
     */
    @Override
    @Transactional
    public void deleteRecipeById(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Recipe> query = currentSession.createQuery("delete from Recipe where id=:recipeId");
        query.setParameter("recipeId", id);
        query.executeUpdate();
    }

    

    
}
