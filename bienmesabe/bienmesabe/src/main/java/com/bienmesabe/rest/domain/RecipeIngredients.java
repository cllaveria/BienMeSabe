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
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author RAUL
 */
@Entity
@Table(name="recipeingredients")
public class RecipeIngredients implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    
    @Column(name="RECIPE_ID")
    //@ManyToOne
    private long recipeId;
    
    @Column(name="INGREDIENT_ID")
    //@ManyToOne
    private long ingredientId;
    @Column(name="INGREDIENT_QTY")
    private float ingredientQTY;
    @Column(name="INGREDIENT_UNITY")
    private String ingredientUnity;

    public RecipeIngredients() {
    }

    public RecipeIngredients(long recipeId, long ingredientId, float ingredientQTY, String ingredientUnity) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredientQTY = ingredientQTY;
        this.ingredientUnity = ingredientUnity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public float getIngredientQTY() {
        return ingredientQTY;
    }

    public void setIngredientQTY(float ingredientQTY) {
        this.ingredientQTY = ingredientQTY;
    }

    public String getIngredientUnity() {
        return ingredientUnity;
    }

    public void setIngredientUnity(String ingredientUnity) {
        this.ingredientUnity = ingredientUnity;
    }
    
    
}
