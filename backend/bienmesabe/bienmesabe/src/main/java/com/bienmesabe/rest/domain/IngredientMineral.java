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
@Table(name="ingredient_minerals")
public class IngredientMineral implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    //@ManyToOne
    @Column(name="INGREDIENT_ID")
    private float ingredientId;
    @Column(name="MINERAL_QTY")
    private float qty;
    //@ManyToOne
    @Column(name="MINERAL_ID")
    private String mineralId;

    public IngredientMineral() {
    }

    public IngredientMineral(float ingredientId, String mineralId) {
        this.ingredientId = ingredientId;
        this.mineralId = mineralId;
    }

    public IngredientMineral(float ingredientId, float qty, String mineralId) {
        this.ingredientId = ingredientId;
        this.qty = qty;
        this.mineralId = mineralId;
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

    public String getMineralId() {
        return mineralId;
    }

    public void setMineralId(String mineralId) {
        this.mineralId = mineralId;
    }
    
    
}
