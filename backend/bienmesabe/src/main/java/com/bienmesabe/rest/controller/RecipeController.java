/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;

import com.bienmesabe.rest.domain.Recipe;
import com.bienmesabe.rest.domain.RecipeIngredients;
import com.bienmesabe.rest.domain.RecipeStep;
import com.bienmesabe.rest.service.RecipeIngredientService;
import com.bienmesabe.rest.service.RecipeService;
import com.bienmesabe.rest.service.RecipeStepService;
import com.bienmesabe.rest.service.UploadFileService;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import javax.activation.FileTypeMap;
import javax.ws.rs.Consumes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for Recipes // url: http://localhost:8080/api/recipe
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/recipe")
public class RecipeController {
    
    /**
     * Bean of the recipe service (Interface)
     */
    @Autowired
    private RecipeService recipeService;
    
    /**
     * Bean of the recipe step service (Interface)
     */
    @Autowired
    private RecipeStepService recipeStepService;
    
    /**
     * Bean of the recipe ingredient service (Interface)
     */
    @Autowired
    private RecipeIngredientService recipeIngredientService;
    
    /**
     * Bean of the upload file service (Interface)
     */
    @Autowired
    private UploadFileService uploadFileService;
    
    /**
     * Method to recover the recipes  // HTTP verb: GET url: http://localhost:8080/api/recipe/getRecipes
     * @return a list with the recipes
     */
    @GetMapping("/getRecipes")
    public List<Recipe> getRecipes(){
        return recipeService.getAllRecipes();
    }
    
    /**
     * Method to recover the recipes ordered by assessment desc // HTTP verb: GET url: http://localhost:8080/api/recipe/getRecipesByAssessment
     * @return a list with the recipes ordered by assessment desc
     */
    @GetMapping("/getRecipesByAssessment")
    public List<Recipe> getRecipesByAssessment(){
        return recipeService.getAllRecipesByAssessment();
    }
    
    /**
     * Method to recover the recipes by ingredients // HTTP verb: GET url: http://localhost:8080/api/recipe/getRecipeByIngredients/{IngredientsList}
     * @param ingredientsForFilter  list with the ingredients that must have the recipe
     * @return  a list with the recipes in the DB filtered by ingredients
     */
    @GetMapping("/getRecipeByIngredients/{ingredients}")
    public List<Recipe> getRecipeByIngredients(@PathVariable String ingredientsForFilter){
        return recipeService.getRecipeByIngredients(ingredientsForFilter);
    }
    
    /**
     * Method to recover the recipes by KCAL range // HTTP verb: GET url: http://localhost:8080/api/recipe/getRecipesByKCal/{kcalMin}/{kcalMax}
     * @param kcalMin integer that represents the mimimum kcal of the recipe to search
     * @param kcalMax integer that represents the maximum kcal of the recipe to search 
     * @return a list with the recipes in the DB filtered by kcal range
     */
    @GetMapping("/getRecipesByKCal/{kcalMin}/{kcalMax}")
    public List<Recipe> getRecipeByKCal(@PathVariable int kcalMin, @PathVariable int kcalMax){
        return recipeService.getRecipeByKCal(kcalMin, kcalMax);
    }
    
    /**
     * Method to recover the recipes by type // HTTP verb: GET url: http://localhost:8080/api/recipe/getRecipesByType/{RecipeType}
     * @param type integer that represents the type of the recipe to search
     * @return a list with the recipes in the DB filtered by type
     */
    @GetMapping("/getRecipesByType/{type}")
    public List<Recipe> getRecipesByType(@PathVariable int type){
        return recipeService.getRecipeByType(type);
    }
    
    /**
     * Method to recover the recipes by dinners // HTTP verb: GET url: http://localhost:8080/api/recipe/getRecipesByDinners/{dinners}
     * @param dinners integer that represents the dinners of the recipe to search
     * @return a list with the recipes in the DB filtered by dinners 
     */
    @GetMapping("/getRecipesByDinners/{dinners}")
    public List<Recipe> getRecipesByDinners(@PathVariable int dinners){
        return recipeService.getRecipesByDinners(dinners);
    }
    
    /**
     * Method to recover the recipe by id // HTTP verb: GET url: http://localhost:8080/api/recipe/getRecipesById/{id}
     * @param id string that represents the id of the recipe to search
     * @return the recipe in the DB filtered by id
     */
    @GetMapping("/getRecipesById/{id}")
    public Recipe getRecipesById(@PathVariable String id){
        return recipeService.getRecipeById(Long.parseLong(id));
    }
    
    /**
     * Method to recover the recipe by received filters // HTTP verb: GET url: http://localhost:8080/api/recipe/getRecipesByFilters/{data}
     * format of filter [typeOfFilter-filterValues]_[typeOfFilter-filterValues] ex: ingredients-10,11_kcal-200,250_type-21_dinners-1
     * @param data string that contains all the filters to apply
     * @return a list of recipes that complies with the filters recived by parameter
     */
    @GetMapping("/getRecipesByFilters/{data}")
    public List<Recipe> getRecipesByFilters(@PathVariable String data){
        return recipeService.getRecipesByFilters(data);
    }
    
    /**
     * Method to recover the recipes by user id // HTTP verb: GET url: http://localhost:8080/api/recipe/getRecipesOfOtherUsers/{userId}
     * @param userId string that represents the id of the user to search
     * @return the list of recipes in the DB filtered by user id
     */
    @GetMapping("/getRecipesOfUser/{userId}")
    public List<Recipe> getRecipesOfUser(@PathVariable String userId){
        return recipeService.getRecipesOfOtherUsers(Long.parseLong(userId));
    }
    
    /**
     * Method to recover the recipes by user id // HTTP verb: GET url: http://localhost:8080/api/recipe/getRecipesOfOtherUsers/{userId}
     * @param userId string that represents the id of the user not to search
     * @return the list of recipes in the DB filtered by user id
     */
    @GetMapping("/getRecipesOfOtherUsers/{userId}")
    public List<Recipe> getRecipesOfOtherUsers(@PathVariable String userId){
        return recipeService.getRecipesOfOtherUsers(Long.parseLong(userId));
    }
    
    /**
     * Method to recover the recipes that are not active // HTTP verb: GET url: http://localhost:8080/api/recipe/getRecipesNotActive
     * @return a list of recipes that are not activated
     */
    @GetMapping("/getRecipesNotActive")
    public List<Recipe> getRecipesNotActive(){
        return recipeService.getRecipesNotActive();
    }
    
    @GetMapping("/retrieveImage/{path}")
    public ResponseEntity<byte[]> getImageOfRecipe(@PathVariable String path){
        File img = new File(path);
        try{
            return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
        }catch(Exception ee){
            return null;
        }
        
    }
    /**
     * Method to create the recipe // HTTP verb: POST url: http://localhost:8080/api/recipe/addRecipe
     * @param recipe object that represents the recipe to persist
     * @return long that represents the id of the inserted recipe
     */
    
    @PostMapping("/addRecipe")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public Long addRecipe(@RequestBody Recipe recipe){
        Long createdRecipe = recipeService.createRecipe(recipe);
        if(createdRecipe > 0){
            return createdRecipe;
        }
        return 0L;
    }
    
    /**
     * Method to create the recipe // HTTP verb: POST url: http://localhost:8080/api/recipe/uploadImageFile
     * @param file object that represents the image of the recipe to upload
     * @return a boolean that indicates if the image has been successfully uploaded or not
     */
    @PostMapping("/uploadImageFile")
    @Consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImageFile(@RequestParam("file") MultipartFile file){
        try{
            String path = uploadFileService.saveImageFile(file);
            if(path != ""){
                return path.replace("E:\\Google Drive\\CURSO CFGS DAW\\Practicas\\6 - Semestre 6\\0 - Proyecto\\00_proyecto\\Sergio\\BienMeSabe\\frontend\\public\\","");
            }else{
                return "";
            }
        }catch(NumberFormatException ee){
            return "";
        }
    }
    
    /**
     * Method to create the recipe ingredient // HTTP verb: POST url: http://localhost:8080/api/recipe/addIngredient/{ingredient}
     * @param ingredient string that represents the recipe ingredient to persist
     * @return a boolean that indicates if the recipe ingredient has been successfully inserted into the DB or not
     */
    @PostMapping("/addIngredient/{ingredient}")
    public Boolean addRecipeIngredient(@PathVariable String ingredient){
        return recipeIngredientService.insertRecipeIngredient(ingredient);
    }
    
    /**
     * Method to create the recipe step // HTTP verb: POST url: http://localhost:8080/api/recipe/addRecipeStep
     * @param step object that represents the recipe step to persist
     * @return a boolean that indicates if the recipe step has been successfully inserted into DB or not
     */
    @PostMapping("/addRecipeStep")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public Boolean addRecipeStep(@RequestBody RecipeStep step){
        return recipeStepService.insertRecipeStep(step);
    }

    /**
     * Method to modify the recipe // HTTP verb: PUT url: http://localhost:8080/api/recipe/modifyRecipe
     * @param recipe object that represents the recipe to modify
     * @return the modified recipe
     */
    @PutMapping("/modifyRecipe")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateRecipe(@RequestBody Recipe recipe){
        try{
            recipeService.modifyRecipe(recipe);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    /**
     * Method to modify the recipe ingredient // HTTP verb: PUT url: http://localhost:8080/api/recipe/modifyRecipeIngredient
     * @param ingredient object that represents the recipe ingredient to modify
     * @return a boolean that indicates if the recipe ingredient has been successfully updated in the DB or not
     */
    @PutMapping("/modifyRecipeIngredient")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public boolean updateRecipeIngredient(@RequestBody RecipeIngredients ingredient){
        boolean res =  recipeIngredientService.updateRecipeIngredient(ingredient);
        if(res){
            return recipeIngredientService.updateKCALOfRecipe(ingredient.getRecipeId());
        }
        return res;
    }
    
    /**
     * Method to modify the recipe step // HTTP verb: PUT url: http://localhost:8080/api/recipe/modifyRecipeStep
     * @param step object that represents the recipe step to modify
     * @return a boolean that indicates if the recipe step has been successfully updated in the DB or not
     */
    @PutMapping("/modifyRecipeStep")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public boolean updateRecipeStep(@RequestBody RecipeStep step){
        return recipeStepService.updateRecipeStep(step);
    }

    /**
     * Method to set the indicated recipe as active // HTTP verb: PUT url: http://localhost:8080/api/recipe/setRecipeAsActive/{id}
     * @param id string that represents the id of the recipe to activate
     * @return a boolean that represents if the recipe has been successfully activated or not
     */
    @PutMapping("/setRecipeAsActive/{id}")
    public Boolean setRecipeAsActive(@PathVariable String id){
        return recipeService.setRecipeAsActive(Long.parseLong(id));
    }
    
    /**
     * Method to delete the recipe by id // HTTP verb: DELETE url: http://localhost:8080/api/recipe/deleteRecipeById/{RecipeId}
     * @param id string with the id of the recipe to delete
     * @return an string that informs the id of the deleted recipe
     */
    @DeleteMapping("deleteRecipeById/{id}")
    public String deleteRecipeById(@PathVariable String id){
        Recipe recipe = recipeService.getRecipeById(Long.parseLong(id));
        if(recipe != null) {
           recipeService.deleteRecipeById(Long.parseLong(id));
           return "Deleted recipe id - "+id;
        }
        
        return null;
    }
    
    /**
     * Method to delete the recipe ingredients // HTTP verb: DELETE url: http://localhost:8080/api/recipe/deleteRecipeIngredients/{id}
     * @param id string with the id of the recipe to delete
     * @return an string that informs the id of the deleted recipe
     */
    @DeleteMapping("deleteRecipeIngredients/{id}")
    public boolean deleteRecipeIngredients(@PathVariable String id){
        return recipeIngredientService.deleteRecipeIngredients(Long.parseLong(id));
    }
    
    /**
     * Method to delete the recipe steps // HTTP verb: DELETE url: http://localhost:8080/api/recipe/deleteRecipeSteps/{id}
     * @param id string with the id of the recipe to delete
     * @return an string that informs the id of the deleted recipe
     */
    @DeleteMapping("deleteRecipeSteps/{id}")
    public boolean deleteRecipeSteps(@PathVariable String id){
        return recipeStepService.deleteRecipeSteps(Long.parseLong(id));
    }
    
}
