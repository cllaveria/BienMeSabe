/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.RecipeTypeDAO;
import com.bienmesabe.rest.domain.RecipeTypes;
import com.bienmesabe.rest.service.RecipeTypesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface RecipeTypesService (service)
 * @author RAUL RAMOS CENDRERO
 * @version 12/04/2021
 */
@Service
public class RecipeTypesServiceImpl implements RecipeTypesService{

    /**
     * Bean of the nutricionist repository (Interface)
     */
    @Autowired
    private RecipeTypeDAO recipeTypeDAO;
    
    /**
     * Implementation of interface method to recover the recipe types present in the DB
     * @return a list with the recipe types present in the DB
     */
    @Override
    public List<RecipeTypes> getRecipeTypes() {
        return recipeTypeDAO.getRecipeTypes();
    }
    
}
