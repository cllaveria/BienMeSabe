/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.RecipeDAO;
import com.bienmesabe.rest.domain.Recipe;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author RAUL
 */
@Repository
public class RecipeDAOImpl implements RecipeDAO {

    @Autowired
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public List<Recipe> getAllRecipes() {
        Session currentSession = entityManager.unwrap(Session.class);
        
        Query<Recipe> query = currentSession.createQuery("from Recipe", Recipe.class);
        
        List<Recipe> recipes = query.getResultList();
        
        return recipes;
    }

    @Override
    @Transactional
    public List<Recipe> getRecipeByIngredients(List<String> ingredientsForFilter) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Recipe> query = currentSession.createQuery("from Recipe where ", Recipe.class);
        List<Recipe> recipes = query.getResultList();
        return recipes;
    }

    @Override
    @Transactional
    public List<Recipe> getRecipeByKCal(Long kcal) {
        /*TODO
        *REALIZAR LA IMPLEMENTACIÓN DE ESTE MÉTODO REALIZANDO LA LLAMADA A LA STORED PROCEDURE
        */
        List<Recipe> recipes = new ArrayList<>();
        return recipes;
    }

    @Override
    @Transactional
    public List<Recipe> getRecipeByType(int type) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Recipe> query = currentSession.createQuery("from Recipe where type=:recipeType");
        query.setParameter("recipeType", type);
        List<Recipe> recipes = query.getResultList();
        return recipes;
    }

    @Override
    public List<Recipe> getRecipesByDinners(int dinners) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Recipe> query = currentSession.createQuery("from Recipe where recipeDinners=:dinners");
        query.setParameter("recipeDinners", dinners);
        List<Recipe> recipes = query.getResultList();
        return recipes;
    }
    
    @Override
    @Transactional
    public Recipe getRecipeById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Recipe recipe = currentSession.get(Recipe.class, id);
        return recipe;
    }

    @Override
    @Transactional
    public Long createRecipe(Recipe recipe) {
        Session currentSession = entityManager.unwrap(Session.class);
        Long idGenerado = (Long) currentSession.save(recipe); 
        return idGenerado;
    }

    @Override
    @Transactional
    public void modifyRecipe(Recipe recipe) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(recipe); 
    }

    @Override
    @Transactional
    public void deleteRecipe(long id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Recipe> query = currentSession.createQuery("delete from Recipe where id=:recipeId");

        query.setParameter("recipeId", id);
        query.executeUpdate();
    }

}
