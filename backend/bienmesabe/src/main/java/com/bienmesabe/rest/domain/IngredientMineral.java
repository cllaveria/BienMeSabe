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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This class defines the minerals of an ingredient
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="ingredient_minerals")
public class IngredientMineral implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    //@JoinColumn(name="ID")
    private Long id;
    
    @Column(name="MINERAL_QTY")
    private float qty;
    
    @OneToOne
    @JoinColumn(name="mineral_id")
    private Mineral mineral;

    @ManyToOne
    @JoinColumn(name="INGREDIENT_ID")
    private Ingredient ingredient;
    
    /**
     * Empty Constructor
     */
    public IngredientMineral() {
    }

    /**
     * IngredientVitamin Constructor with the required parameters
     * @param ingredientId long that represents the id of the ingredient
     */
    public IngredientMineral(Long ingredientId) {
        this.ingredient.setId(ingredientId);
    }

    /**
     * IngredientVitamin Constructor with the required parameters
     * @param ingredientId long that represents the id of the ingredient
     * @param qty float that represents the quantity of the ingredient mineral
     */
    public IngredientMineral(Long ingredientId, float qty) {
        this.ingredient.setId(ingredientId);
        this.qty = qty;
    }

    /**
     * Method to recover the id of the ingredient mineral
     * @return a long that represents the id of the ingredient mineral
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to asign the id of the ingredient mineral
     * @param id long that represents the id of the ingredient mineral to asign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to recover the id of the ingredient
     * @return a long that represents the id of the ingredient
     */
    public Long getIngredientId() {
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
     * Method to recover the quantity of the mineral for the ingredient
     * @return a long that represents the quantity of the mineral for the ingredient
     */
    public float getQty() {
        return qty;
    }
    
    /**
     * Method to asign the quantity of the mineral for the ingredient
     * @param qty long that represents the quantity of the mineral for the ingredient to asign
     */
    public void setQty(float qty) {
        this.qty = qty;
    }
    
    /**
     * Method to recover the mineral for the ingredient
     * @return an object that represents the mineral for the ingredient
     */
    public Mineral getMineral() {
        return mineral;
    }

    /**
     * Method to asign the mineral for the ingredient
     * @param mineral object that represents the mineral for the ingredient
     */
    public void setMineral(Mineral mineral) {
        this.mineral = mineral;
    }

    
    
    
}
