/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.IngredientDAO;
import com.bienmesabe.rest.domain.Ingredient;
import com.bienmesabe.rest.domain.IngredientMineral;
import com.bienmesabe.rest.domain.IngredientVitamin;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface IngredientDAO (repository)
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Repository
public class IngredientDAOImpl implements IngredientDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    /**
     * Implementation of interface method to recover the ingredients present in the DB
     * @return a list with the ingredients in the DB
     */
    @Override
    @Transactional
    public List<Ingredient> findAllIngredients() {
        Session currentSession = entityManager.unwrap(Session.class);
        
        Query<Ingredient> query = currentSession.createQuery("from Ingredient", Ingredient.class);
        
        List<Ingredient> ingredients = query.getResultList();
        
        return ingredients;
    }

    /**
     * Implementation of interface method to recover the ingredient present in the DB by name
     * @param name string that represents the name of the ingredient to search
     * @return the ingredient in the DB
     */
    @Override
    @Transactional
    public Ingredient findIngredientByName(String name) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Ingredient> query = currentSession.createQuery("FROM Ingredient WHERE name=:name", Ingredient.class);
        query.setParameter("name", name);
        Ingredient ingredient = new Ingredient();
        try{
             ingredient= query.getSingleResult();
        }catch(Exception e){
            return ingredient;
        }
        return ingredient;
    }

    /**
     * Implementation of interface method to recover the ingredient present in the DB by id
     * @param id long that represents the id of the ingredient to search
     * @return the ingredient in the DB
     */
    @Override
    @Transactional
    public Ingredient findIngredientById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Ingredient ingredient = currentSession.get(Ingredient.class, id);
        return ingredient;
    }

    /**
     * Implementation of interface method to create an ingredient in the table ingredients of the DB
     * @param ingredient object that represents the ingredient to persist
     * @return a long with the id of the persisted ingredient
     */
    @Override
    @Transactional
    public Long createIngredient(Ingredient ingredient) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Ingredient> query = currentSession.createQuery("FROM Ingredient WHERE name=:name and carbohidrates=:carbs and proteins=:prot and fat=:fats and saturedFats=:satured"
                + " and fiber=:fiber and sodium=:sodium and sugars=:sugars", Ingredient.class);
        query.setParameter("name", ingredient.getName());
        query.setParameter("carbs", ingredient.getCarbohidrates());
        query.setParameter("prot", ingredient.getProteins());
        query.setParameter("fats", ingredient.getFat());
        query.setParameter("satured", ingredient.getSaturedFats());
        query.setParameter("fiber", ingredient.getFiber());
        query.setParameter("sodium", ingredient.getSodium());
        query.setParameter("sugars", ingredient.getSugars());
        Ingredient ingredientInDB = new Ingredient();
        try{
             ingredientInDB= query.getSingleResult();
        }catch(Exception e){
            ingredientInDB = null;
        }
        if(ingredientInDB==null){
            Long idGenerado = (Long) currentSession.save(ingredient); 
        return idGenerado;
        }
        return 0L;
    }

    /**
     * Implementation of interface method to modify an ingredient in the table ingredients of the DB
     * @param ingredient object that represents the ingredient to modify
     */
    @Override
    @Transactional
    public void modifyIngredient(Ingredient ingredient) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(ingredient);
    }

    /**
     * Implementation of interface method to delete an ingredient in the table ingredients of the DB by id
     * @param id long with the id of the ingredient to delete
     */
    @Override
    @Transactional
    public void deleteIngredientById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Ingredient> query = currentSession.createQuery("delete from Ingredient where id=:ingredientId");

        query.setParameter("ingredientId", id);
        query.executeUpdate();
    }

    /**
     * Implementation of interface method to delete an ingredient in the table ingredients of the DB by name
     * @param name string with the name of the ingredient to delete
     */
    @Override
    @Transactional
    public void deleteIngredientByName(String name) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Ingredient> query = currentSession.createQuery("delete from Ingredient where name=:ingredientName");

        query.setParameter("ingredientName", name);
        query.executeUpdate();
    }

}
