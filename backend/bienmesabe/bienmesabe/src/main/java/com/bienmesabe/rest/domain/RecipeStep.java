/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author RAUL
 */
public class RecipeStep implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="STEP_ID", updatable = false, nullable = false)
    private Long id;
    @Column(name="RECIPE_ID")
    private Long recipeId;
    @Column(name="STEP_DESCRIPTION")
    private String stepDescription;

    public RecipeStep() {
    }

    public RecipeStep(Long recipeId, String stepDescription) {
        this.recipeId = recipeId;
        this.stepDescription = stepDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }
    
    
}
