/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.RecipeTypes;
import java.util.List;

/**
 * Inteface with the service methods to retrive the recipe types
 * @author RAUL RAMOS CENDRERO
 * @version 12/04/2021
 */
public interface RecipeTypesService {
    /**
     * Method to recover the recipe types
     * @return a list with the recipe types
     */
    public List<RecipeTypes> getRecipeTypes();
}
