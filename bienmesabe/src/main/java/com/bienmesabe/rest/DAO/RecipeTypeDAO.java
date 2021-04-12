/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.RecipeTypes;
import java.util.List;

/**
 * Inteface with the methods to retrive the DB data of recipe types
 * @author RAUL RAMOS CENDRERO
 * @version 12/04/2021
 */
public interface RecipeTypeDAO {
    /**
     * Method to recover the recipe types present in the DB
     * @return a list with the recipe types in the DB
     */
    public List<RecipeTypes> getRecipeTypes();
}
