/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class defines the steps of the recipes
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="recipesteps")
public class RecipeStep implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="STEP_ID", updatable = false, nullable = false)
    private Long id;

    @Column(name="STEP_DESCRIPTION")
    private String stepDescription;
    
    @Column(name="RECIPE_ID")
    private long recipeId;

    @Column(name="STEP_IMAGE")
    private String image;
    /**
     * Empty Constructor
     */
    public RecipeStep() {
    }

    /**
     * RecipeStep Constructor with the required parameters
     * @param recipeId long that represents the id of the recipe
     * @param stepDescription string that represents the description of the recipe's step
     */
    public RecipeStep(Long recipeId, String stepDescription) {
        this.recipeId = recipeId;
        this.stepDescription = stepDescription;
    }

    /**
     * RecipeStep Constructor with the required parameters
     * @param id  long that represents the id of the recipe step
     * @param recipeId long that represents the id of the recipe
     * @param stepDescription string that represents the description of the recipe's step
     * @param image string that represents teh path of the step image
     */
    public RecipeStep(Long id, String stepDescription, long recipeId, String image) {
        this.id = id;
        this.stepDescription = stepDescription;
        this.recipeId = recipeId;
        this.image = image;
    }

    
    /**
     * Method to recover the id of the recipe step
     * @return a long that represents the id of the recipe step
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to asign the id of the recipe step
     * @param id long that represents the id of the recipe step to asign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to recover the id of the recipe
     * @return a long that represents the id of the recipe
     */
    public Long getRecipeId() {
        return this.recipeId ;
    }
    
    /**
     * Method to asign the id of the recipe
     * @param recipeId long that represents the id of the recipe to asign
     */
    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * Method to recover the description of the recipe step
     * @return a string that represents the description of the recipe step
     */
    public String getStepDescription() {
        return stepDescription;
    }
    
    /**
     * Method to asign the description of the recipe step
     * @param stepDescription string that represents the description of the recipe step to asign
     */
    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    /**
     * Method to recover the image path of the recipe step
     * @return a string that represents the image path of the recipe step
     */
    public String getImage() {
        return image;
    }

    /**
     * Method to asign the image path of the recipe step
     * @param image string that represents the image pathn of the recipe step to asign
     */
    public void setImage(String image) {
        this.image = image;
    }
    
    
}
