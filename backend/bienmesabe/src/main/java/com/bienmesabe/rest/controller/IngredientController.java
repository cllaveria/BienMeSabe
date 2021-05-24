/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;

import com.bienmesabe.rest.domain.Ingredient;
import com.bienmesabe.rest.service.IngredientService;
import java.util.List;
import javax.ws.rs.Consumes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Ingredients // url: http://localhost:8080/api/ingredient
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/ingredient")
public class IngredientController {
    
    /**
     * Bean of the ingredient service (Interface)
     */
    @Autowired
    private IngredientService ingredientService;
    
    /**
     * Method to recover all the ingredients  // HTTP verb: GET url: http://localhost:8080/api/ingredient/getIngredients
     * @return a list of all ingredients
     */
    @GetMapping("/getIngredients")
    public List<Ingredient> getIngredients(){
        return ingredientService.findAllIngredients();
    }
    
    /**
     * Method to recover the ingredient by id  // HTTP verb: GET url: http://localhost:8080/api/ingredient/getIngredientById/{IngredientId}
     * @param id string that represents the id of the ingredient to search
     * @return an ingredient filtered by id
     */
    @GetMapping("/getIngredientById/{ID}")
    public Ingredient findIngredientById(@PathVariable String id){
        Ingredient ingredient = ingredientService.findIngredientById(Long.parseLong(id));
        return ingredient;
    }
    
    /**
     * Method to recover the ingredient by name  // HTTP verb: GET url: http://localhost:8080/api/ingredient/findIngredientByName/{name}
     * @param name string that represents the name of the ingredient to search
     * @return an ingredient filtered by name
     */
    @GetMapping("/findIngredientByName/{name}")
    public Ingredient findIngredientByName(@PathVariable String name){
        Ingredient ingredient = ingredientService.findIngredientByName(name);
        return ingredient;
    }
    
    /**
     * Method to create an ingredient  // url: HTTP verb: POST http://localhost:8080/api/ingredient/addIngredient
     * @param ingredient object that represents the ingredient to persist
     * @return a long with the id of the persisted ingredient
     */
    @PostMapping("/addIngredient")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public Ingredient addIngredient(@RequestBody Ingredient ingredient){
        ingredient.setId(0L);
        Long createdIngredient = ingredientService.createIngredient(ingredient);
        if(createdIngredient > 0){
            ingredient.setId(createdIngredient);
            return ingredient;
        }
        return new Ingredient();
    }
    
    /**
     * Method to modify an ingredient  // HTTP verb: PUT url: http://localhost:8080/api/ingredient/modifyIngredient
     * @param ingredient object that represents the ingredient to modify
     * @return the modified ingredient
     */
    @PutMapping("/modifyIngredient")
    public Ingredient updateIngredient(Ingredient ingredient){
        ingredientService.modifyIngredient(ingredient);
        return ingredient;
    }
    
    /**
     * Method to delete an ingredient by id  // HTTP verb: DELETE url: http://localhost:8080/api/ingredient/deleteIngredientById/{IngredientId}
     * @param id string with the id of the ingredient to delete
     * @return a string informing that the ingredient id is deleted
     */
    @DeleteMapping("/deleteIngredientById/{id}")
    public String deleteIngredientById(@PathVariable String id){
        Ingredient ingredient = ingredientService.findIngredientById(Long.parseLong(id));
        if(ingredient != null) {
           ingredientService.deleteIngredientById(Long.parseLong(id));
           return "Deleted ingredient id - "+id;
        }
        
        return null;
    }
    
    /**
     * Method to delete an ingredient by name  // HTTP verb: DELETE url: http://localhost:8080/api/ingredient/deleteIngredientByName/{name}
     * @param name string with the name of the ingredient to delete
     * @return a string informing that the ingredient id is deleted
     */
    @DeleteMapping("/deleteIngredientByName/{name}")
    public String deleteIngredientByname(@PathVariable String name){
        Ingredient ingredient = ingredientService.findIngredientByName(name);
        if(ingredient != null) {
            ingredientService.deleteIngredientByName(name);
            return "Deleted ingredient id - " + name;
        }
        return null;
    }

}
