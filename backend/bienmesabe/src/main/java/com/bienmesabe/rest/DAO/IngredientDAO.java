/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.Ingredient;
import java.util.List;


/**
 * Inteface with the methods to retrive the DB data of ingredients
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
public interface IngredientDAO {
    
    /**
     * Method to recover the ingredients present in the DB
     * @return a list with the ingredients in the DB
     */
    public List<Ingredient> findAllIngredients();
    
    /**
     * Method to recover the ingredient present in the DB by name
     * @param name string that represents the name of the ingredient to search
     * @return the ingredient in the DB filtered by name
     */
    public Ingredient findIngredientByName(String name);
    
    /**
     * Method to recover the ingredient present in the DB by id
     * @param id long that represents the id of the ingredient to search
     * @return the ingredient in the DB filtered by id
     */
    public Ingredient findIngredientById(Long id);
    
    /**
     * Method to create an ingredient in the table ingredients of the DB
     * @param ingredient object that represents the ingredient to persist
     * @return a long with the id of the persisted ingredient
     */
    public Long createIngredient(Ingredient ingredient);
    
    /**
     * Method to modify an ingredient in the table ingredients of the DB
     * @param ingredient object that represents the ingredient to modify
     */
    public void modifyIngredient(Ingredient ingredient);
    
    /**
     * Method to delete an ingredient in the table ingredients of the DB by id
     * @param id long with the id of the ingredient to delete
     */
    public void deleteIngredientById(Long id);
    
    /**
     * Method to delete an ingredient in the table ingredients of the DB by name
     * @param name string with the name of the ingredient to delete
     */
    public void deleteIngredientByName(String name);
    
}
