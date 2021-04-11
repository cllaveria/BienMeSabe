/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.IngredientDAO;
import com.bienmesabe.rest.domain.Ingredient;
import com.bienmesabe.rest.service.IngredientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface IngredientService (service)
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Service
public class IngredientServiceImpl implements IngredientService{
    
    /**
     * Bean of the ingredient repository (Interface)
     */
    @Autowired
    private IngredientDAO ingredientDAO;

    /**
     * Implementation of interface method to recover the ingredients
     * @return a list with the ingredients
     */
    @Override
    public List<Ingredient> findAllIngredients() {
        return ingredientDAO.findAllIngredients();
    }

    /**
     * Implementation of interface method to recover the ingredient by name
     * @param name string that represents the name of the ingredient to search
     * @return the ingredient filtered by name
     */
    @Override
    public Ingredient findIngredientByName(String name) {
        return ingredientDAO.findIngredientByName(name);
    }

    /**
     * Implementation of interface method to recover the ingredient by id
     * @param id long that represents the id of the ingredient to search
     * @return the ingredient filtered by id
     */
    @Override
    public Ingredient findIngredientById(Long id) {
        return ingredientDAO.findIngredientById(id);
    }

    /**
     * Implementation of interface method to create an ingredient
     * @param ingredient object that represents the ingredient to persist
     * @return a long with the id of the persisted ingredient
     */
    @Override
    public Long createIngredient(Ingredient ingredient) {
        return ingredientDAO.createIngredient(ingredient);
    }

    /**
     * Implementation of interface method to modify an ingredient
     * @param ingredient object that represents the ingredient to modify
     */
    @Override
    public void modifyIngredient(Ingredient ingredient) {
        ingredientDAO.modifyIngredient(ingredient);
    }

    /**
     * Implementation of interface method to delete an ingredient by id
     * @param id long with the id of the ingredient to delete
     */
    @Override
    public void deleteIngredientById(Long id) {
        ingredientDAO.deleteIngredientById(id);
    }

    /**
     * Implementation of interface method to delete an ingredient by name
     * @param name string with the name of the ingredient to delete
     */
    @Override
    public void deleteIngredientByName(String name) {
        ingredientDAO.deleteIngredientByName(name);
    }
    
    
}
