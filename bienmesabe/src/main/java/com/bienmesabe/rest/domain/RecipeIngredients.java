/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This class defines the ingredients of the recipes
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="recipeingredients")
public class RecipeIngredients implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    @JoinColumn(name="id")
    private Long id;
    
    @OneToOne
    @JoinColumn(name="INGREDIENT_ID")
    private Ingredient ingredient;
    
    @Column(name="INGREDIENT_QTY")
    private float ingredientQTY;
    @Column(name="INGREDIENT_UNITY")
    private String ingredientUnity;

    @ManyToOne
    @JoinColumn(name="RECIPE_ID")
    private Recipe recipe;
    
    /**
     * Empty Constructor
     */
    public RecipeIngredients() {
    }

    /**
     * RecipeIngredients Constructor with the required parameters
     * @param recipeId long that represents the id of the recipe to asign
     * @param ingredientId long that represents the id of the class ingredient to asign
     * @param ingredientQTY float that represents the quantity of the recipe ingredient to asign
     * @param ingredientUnity string that represents the measurement unit of the recipe ingredient to asign
     */
    public RecipeIngredients(long recipeId, long ingredientId, float ingredientQTY, String ingredientUnity) {
        this.recipe.setId(recipeId);
        this.ingredient.setId(ingredientId);
        this.ingredientQTY = ingredientQTY;
        this.ingredientUnity = ingredientUnity;
    }
      
    /**
     * Method to recover the id of the recipe ingredient
     * @return a long that represents the id of the recipe ingredient
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to asign the id of the recipe step
     * @param id long that represents the id of the recipe ingredient to asign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to recover the id of the recipe
     * @return a long that represents the id of the recipe
     */
    public long getRecipeId() {
        return recipe.getId();
    }

    /**
     * Method to asign the id of the recipe
     * @param recipeId long that represents the id of the recipe to asign
     */
    public void setRecipeId(long recipeId) {
        this.recipe.setId(recipeId);
    }

    /**
     * Method to recover the id of the class ingredient
     * @return a long that represents the id of the class ingredient
     */
    public long getIngredientId() {
        return ingredient.getId();
    }
    /**
     * Method to asign the id of the class ingredient
     * @param ingredientId long that represents the id of the class ingredient to asign
     */
    public void setIngredientId(long ingredientId) {
        this.ingredient.setId(ingredientId);
    }

    /**
     * Method to recover the quantity of the recipe ingredient
     * @return a float that represents the quantity of the recipe ingredient
     */
    public float getIngredientQTY() {
        return ingredientQTY;
    }

    /**
     * Method to recover the quantity of the recipe ingredient
     * @param ingredientQTY float that represents the quantity of the recipe ingredient to asign
     */
    public void setIngredientQTY(float ingredientQTY) {
        this.ingredientQTY = ingredientQTY;
    }

    /**
     * Method to recover the measurement unit of the recipe ingredient
     * @return a string that represents the measurement unit of the recipe ingredient
     */
    public String getIngredientUnity() {
        return ingredientUnity;
    }

    /**
     * Method to asign the measurement unit of the recipe ingredient
     * @param ingredientUnity string that represents the measurement unit of the recipe ingredient to asign
     */
    public void setIngredientUnity(String ingredientUnity) {
        this.ingredientUnity = ingredientUnity;
    }
    
    
}
