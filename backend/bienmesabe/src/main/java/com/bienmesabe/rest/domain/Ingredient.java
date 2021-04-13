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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * This class defines the ingredients
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="ingredients")
public class Ingredient implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    @Column(name="NAME")
    private String name;
    @Column(name="PROTEINS")
    private float proteins;
    @Column(name="SATURED")
    private float saturedFats;
    @Column(name="FAT")
    private float fat;
    @Column(name="MONOINSATURATED")
    private float monoinsaturatedFats;
    @Column(name="POLYINSATURATED")
    private float polyinsaturatedFats;
    @Column(name="CARBOHIDRATES")
    private float carbohidrates;
    @Column(name="FIBER")
    private float fiber;
    @Column(name="SODIUM")
    private float sodium;
    @Column(name="SUGARS")
    private float sugars;
    @Column(name="KCALX100G")
    private float kcal;

    @OneToOne(mappedBy="ingredient")
    private RecipeIngredients recipeIngredient;
    
    @OneToMany(mappedBy="ingredient")
    private List<IngredientMineral> ingredientMinerals;
    
    @OneToMany(mappedBy="ingredient")
    private List<IngredientVitamin> ingredientVitamins;
    
    /**
     * Empty Constructor
     */
    public Ingredient() {
    }

    /**
     * Ingredient Constructor with name
     * @param name string that represents the name of the ingredient
     */
    public Ingredient(String name) {
        this.name = name;
    }

    /**
     * Method to recover the id of the ingredient
     * @return a long that represents the id of the ingredient
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to asign the id of the ingredient
     * @param id long that represents the id of the ingredient to asign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to recover the name of the ingredient
     * @return a string that represents the name of the ingredient
     */
    public String getName() {
        return name;
    }

    /**
     * Method to asign the name of the ingredient
     * @param name string that represents the name of the ingredient to asign
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to recover the proteins of the ingredient
     * @return a float that represents the proteins of the ingredient
     */
    public float getProteins() {
        return proteins;
    }

    /**
     * Method to asign the proteins of the ingredient
     * @param proteins float that represents the proteins of the ingredient to asign
     */
    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    /**
     * Method to recover the satured fats of the ingredient
     * @return a float that represents the satured fats of the ingredient
     */
    public float getSaturedFats() {
        return saturedFats;
    }

    /**
     * Method to asign the satured fats of the ingredient
     * @param saturedFats float that represents the satured fats of the ingredient to asign
     */
    public void setSaturedFats(float saturedFats) {
        this.saturedFats = saturedFats;
    }

    /**
     * Method to recover the fats of the ingredient
     * @return a float that represents the fats of the ingredient
     */
    public float getFat() {
        return fat;
    }
    
    /**
     * Method to asign the fats of the ingredient
     * @param fat float that represents the fats of the ingredient to asign
     */
    public void setFat(float fat) {
        this.fat = fat;
    }

    /**
     * Method to recover the monoinsaturated fats of the ingredient
     * @return a float that represents the monoinsaturated fats of the ingredient
     */
    public float getMonoinsaturatedFats() {
        return monoinsaturatedFats;
    }

    /**
     * Method to asign the monoinsaturated fats of the ingredient
     * @param monoinsaturatedFats float that represents the monoinsaturated fats of the ingredient to asign
     */
    public void setMonoinsaturatedFats(float monoinsaturatedFats) {
        this.monoinsaturatedFats = monoinsaturatedFats;
    }

    /**
     * Method to recover the polyinsaturated fats of the ingredient
     * @return a float that represents the polyinsaturated fats of the ingredient
     */
    public float getPolyinsaturatedFats() {
        return polyinsaturatedFats;
    }

    /**
     * Method to asign the polyinsaturated fats of the ingredient
     * @param polyinsaturatedFats float that represents the polyinsaturated fats of the ingredient to asign
     */
    public void setPolyinsaturatedFats(float polyinsaturatedFats) {
        this.polyinsaturatedFats = polyinsaturatedFats;
    }
    
    /**
     * Method to recover the carbohidrates of the ingredient
     * @return a float that represents the carbohidrates of the ingredient
     */
    public float getCarbohidrates() {
        return carbohidrates;
    }

    /**
     * Method to asign the carbohidrates of the ingredient
     * @param carbohidrates float that represents the carbohidrates of the ingredient to asign
     */
    public void setCarbohidrates(float carbohidrates) {
        this.carbohidrates = carbohidrates;
    }

    /**
     * Method to recover the fiber of the ingredient
     * @return a float that represents the fiber of the ingredient
     */
    public float getFiber() {
        return fiber;
    }

    /**
     * Method to asign the fiber of the ingredient
     * @param fiber float that represents the fiber of the ingredient to asign
     */
    public void setFiber(float fiber) {
        this.fiber = fiber;
    }

    /**
     * Method to recover the sodium of the ingredient
     * @return a float that represents the sodium of the ingredient
     */
    public float getSodium() {
        return sodium;
    }

    /**
     * Method to asign the sodium of the ingredient
     * @param sodium float that represents the sodium of the ingredient to asign
     */
    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    /**
     * Method to recover the sugars of the ingredient
     * @return a float that represents the sugars of the ingredient
     */
    public float getSugars() {
        return sugars;
    }

    /**
     * Method to asign the sugars of the ingredient
     * @param sugars float that represents the sugars of the ingredient to asign
     */
    public void setSugars(float sugars) {
        this.sugars = sugars;
    }

    /**
     * Method to recover the kcal of the ingredient
     * @return a float that represents the kcal of the ingredient
     */
    public float getKcal() {
        return kcal;
    }

    /**
     * Method to asign the kcal of the ingredient
     * @param kcal float that represents the kcal of the ingredient to asign
     */
    public void setKcal(float kcal) {
        this.kcal = kcal;
    }
        
    /**
     * Method to recover the list of minerals of the ingredient
     * @return a List with the minerals of the ingredient
     */
    public List<IngredientMineral> getIngredientMinerals() {
        return ingredientMinerals;
    }

    /**
     * Method to asign the list of minerals of the ingredient
     * @param ingredientMinerals List with the minerals of the ingredient to asign
     */
    public void setIngredientMinerals(List<IngredientMineral> ingredientMinerals) {
        this.ingredientMinerals = ingredientMinerals;
    }

    /**
     * Method to recover the list of vitamins of the ingredient
     * @return a List with the vitamins of the ingredient
     */
    public List<IngredientVitamin> getIngredientVitamins() {
        return ingredientVitamins;
    }

    /**
     * Method to asign the list of vitamins of the ingredient
     * @param ingrtedientVitamins List with the vitamins of the ingredient to asign
     */
    public void setIngredientVitamins(List<IngredientVitamin> ingrtedientVitamins) {
        this.ingredientVitamins = ingrtedientVitamins;
    }
    
    
    
    
}
