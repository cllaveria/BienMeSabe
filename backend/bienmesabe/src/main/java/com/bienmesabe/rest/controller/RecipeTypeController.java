/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;

import com.bienmesabe.rest.domain.RecipeTypes;
import com.bienmesabe.rest.service.RecipeTypesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Recipe types // url: http://localhost:8080/api/recipeTypes
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/recipeTypes")
public class RecipeTypeController {
    /**
     * Bean of the recipe types service (Interface)
     */
    @Autowired
    private RecipeTypesService recipeTypesService;
    
    /**
     * Method to recover the recipes  // HTTP verb: GET url: http://localhost:8080/api/recipeTypes/getRecipeTypes
     * @return a list with the recipes
     */
    @GetMapping("/getRecipeTypes")
    public List<RecipeTypes> getRecipeTypes(){
        return recipeTypesService.getRecipeTypes();
    }
}
