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
import javax.persistence.Table;

/**
 *
 * @author RAUL
 */
@Entity
@Table(name="ingredients_vitamins")
public class IngredientVitamin implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    //@ManyToOne
    @Column(name="INGREDIENT_ID")
    private float ingredientId;
    @Column(name="VITAMIN_QTY")
    private float qty;
    //@ManyToOne
    @Column(name="VITAMIN_ID")
    private String vitaminId;

    public IngredientVitamin() {
    }

    public IngredientVitamin(float ingredientId, float qty, String vitaminId) {
        this.ingredientId = ingredientId;
        this.qty = qty;
        this.vitaminId = vitaminId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(float ingredientId) {
        this.ingredientId = ingredientId;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public String getVitaminId() {
        return vitaminId;
    }

    public void setVitaminId(String vitaminId) {
        this.vitaminId = vitaminId;
    }
    
    
}
