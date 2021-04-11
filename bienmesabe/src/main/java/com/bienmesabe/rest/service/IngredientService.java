/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.Ingredient;
import java.util.List;

/**
 * Inteface with the service methods to retrive the ingredients
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
public interface IngredientService {
    
    /**
     * Method to recover the ingredients
     * @return a list with the ingredients
     */
    public List<Ingredient> findAllIngredients();
    
    /**
     * Method to recover the ingredient by name
     * @param name string that represents the name of the ingredient to search
     * @return the ingredient filtered by name
     */
    public Ingredient findIngredientByName(String name);
    
    /**
     * Method to recover the ingredient by id
     * @param id long that represents the id of the ingredient to search
     * @return the ingredient filtered by id
     */
    public Ingredient findIngredientById(Long id);
    
    /**
     * Method to create an ingredient
     * @param ingredient object that represents the ingredient to persist
     * @return a long with the id of the persisted ingredient
     */
    public Long createIngredient(Ingredient ingredient);
    
    /**
     * Method to modify an ingredient
     * @param ingredient object that represents the ingredient to modify
     */
    public void modifyIngredient(Ingredient ingredient);
    
    /**
     * Method to delete an ingredient by id
     * @param id long with the id of the ingredient to delete
     */
    public void deleteIngredientById(Long id);
    
    /**
     * Method to delete an ingredient by name
     * @param name string with the name of the ingredient to delete
     */
    public void deleteIngredientByName(String name);
}
