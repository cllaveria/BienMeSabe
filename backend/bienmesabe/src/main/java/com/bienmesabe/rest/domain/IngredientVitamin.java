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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * This class defines the vitamins of an ingredient
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="ingredients_vitamins")
public class IngredientVitamin implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;

    @Column(name="VITAMIN_QTY")
    private float qty;
    //@ManyToOne
    @Column(name="VITAMIN_ID")
    private String vitaminId;

    @ManyToOne
    @JoinColumn(name="INGREDIENT_ID")
    private Ingredient ingredient;
    
    /**
     * Empty Constructor
     */
    public IngredientVitamin() {
    }

    /**
     * IngredientVitamin Constructor with the required parameters
     * @param ingredientId long that represents the id of the ingredient
     * @param vitaminId long that represents the id of the vitamin
     */
    public IngredientVitamin(Long ingredientId,String vitaminId) {
        this.vitaminId = vitaminId;
        this.ingredient.setId(ingredientId);
    }

    
    /**
     * IngredientVitamin Constructor with the required parameters
     * @param ingredientId long that represents the id of the ingredient
     * @param qty float that represents the quantity of the ingredient vitamin
     * @param vitaminId long that represents the id of the vitmain
     */
    public IngredientVitamin(Long ingredientId, float qty, String vitaminId) {
        this.ingredient.setId(ingredientId);
        this.qty = qty;
        this.vitaminId = vitaminId;
    }

    /**
     * Method to recover the id of the ingredient vitamin
     * @return a long that represents the id of the ingredient vitamin
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to asign the id of the ingredient vitamin
     * @param id long that represents the id of the ingredient vitamin to asign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to recover the id of the ingredient
     * @return a long that represents the id of the ingredient
     */
    public float getIngredientId() {
        return ingredient.getId();
    }

    /**
     * Method to asign the id of the ingredient
     * @param ingredientId long that represents the id of the ingredient to asign
     */
    public void setIngredientId(Long ingredientId) {
        this.ingredient.setId(ingredientId);
    }

    /**
     * Method to recover the quantity of the vitmain for the ingredient
     * @return a long that represents the quantity of the vitmain for the ingredient
     */
    public float getQty() {
        return qty;
    }

    /**
     * Method to asign the quantity of the vitmain for the ingredient
     * @param qty long that represents the quantity of the vitmain for the ingredient to asign
     */
    public void setQty(float qty) {
        this.qty = qty;
    }

    /**
     * Method to recover the id of the vitmain
     * @return a long that represents the id of the vitmain
     */
    public String getVitaminId() {
        return vitaminId;
    }

    /**
     * Method to asign the id of the vitmain
     * @param vitaminId long that represents the id of the vitmain to asign
     */
    public void setVitaminId(String vitaminId) {
        this.vitaminId = vitaminId;
    }
    
    
}
