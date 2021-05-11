/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

/**
 * This class defines the recipes
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="recipe")
public class Recipe implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    @Column(name="IMAGE")
    private String image;
    @Column(name="NAME")
    private String name;
    @Column(name="PREPARATION_VIDEO")
    private String preparationVideo;
    @Column(name="TYPE")
    private int type;
    @Column(name="USER_ID")
    private Long userId;
    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredients> recipeIngredients;
    @OneToMany(mappedBy="recipe")
    private List<RecipeStep> recipeSteps;
    @Column(name="RECIPE_CREATEDAT")
    @CreationTimestamp
    private Date createdAt;
    @Column(name="RECIPE_ACTIVE")
    private int active;
    @Column(name="RECIPE_DINNERS")
    private int recipeDinners;
    @Column(name="RECIPE_KCAL")
    private float recipeKCAL;
    @Column(name="RECIPE_TIME")
    private int recipeTime;
    @Column(name="RECIPE_DIFFICULT")
    private int recipeDifficult;
    @Column(name="RECIPE_ASSESSMENT")
    private int recipeAssessment;
    @Column(name="recipeInitDescription")
    private String recipeInitDescription;
    @Column(name="recipeEndingDescription")
    private String recipeEndingDescription;
    
    /**
     * Empty Constructor
     */
    public Recipe() {}    
    
    /**
     * Recipe constructor with the required parameters
     * @param name string that represents the name of the recipe
     * @param userId long that represents the if of the recipe owner
     */
    public Recipe(String name, Long userId) {
        this.name = name;
        this.userId = userId;
    }

    public Recipe(String image, String name, String preparationVideo, int type, Long userId, List<RecipeIngredients> recipeIngredients, List<RecipeStep> recipeSteps, Date createdAt, int active, int recipeDinners, float recipeKCAL, int recipeTime, int recipeDifficult, int recipeAssessment, String recipeInitDescription, String recipeEndingDescription) {
        this.image = image;
        this.name = name;
        this.preparationVideo = preparationVideo;
        this.type = type;
        this.userId = userId;
        this.recipeIngredients = recipeIngredients;
        this.recipeSteps = recipeSteps;
        this.createdAt = createdAt;
        this.active = active;
        this.recipeDinners = recipeDinners;
        this.recipeKCAL = recipeKCAL;
        this.recipeTime = recipeTime;
        this.recipeDifficult = recipeDifficult;
        this.recipeAssessment = recipeAssessment;
        this.recipeInitDescription = recipeInitDescription;
        this.recipeEndingDescription = recipeEndingDescription;
    }

    
    /**
     * Method to recover the id of the recipe
     * @return a long that represents the id of the recipe
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to asign the id of the recipe
     * @param id long that represents the id of the recipe to asign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to recover the path for the image of the recipe
     * @return a long that represents the path for the image of the recipe
     */
    public String getImage() {
        return image;
    }

    /**
     * Method to asign the path for the image of the recipe
     * @param image long that represents the path for the image of the recipe to asign
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Method to recover the name of the recipe
     * @return a string that represents the name of the recipe
     */
    public String getName() {
        return name;
    }

    /**
     * Method to asign the name of the recipe
     * @param name string that represents the name of the recipe to asing
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to recover the path for the preparation's video of the recipe
     * @return a string that represents the path for the preparation's video of the recipe
     */
    public String getPreparationVideo() {
        return preparationVideo;
    }

    /**
     * Method to asign the path for the preparation's video of the recipe
     * @param preparationVideo string that represents the path for the preparation's video of the recipe to asign
     */
    public void setPreparationVideo(String preparationVideo) {
        this.preparationVideo = preparationVideo;
    }

    /**
     * Méthod to recover the type of the recipe
     * @return integer that represents the type of the recipe
     */
    public int getType() {
        return type;
    }

    /**
     * Méthod to asign the type of the recipe
     * @param type integer that represents the type of the user to recipe
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Méthod to recover the id of recipe's owner user
     * @return a long that represents the id of recipe's owner user
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Méthod to asign the id of recipe's owner user
     * @param userId long that represents the id of recipe's owner user to asign
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Méthod to recover the list of recipe's ingredients
     * @return the list with the ingredients of the recipe
     */
    public List<RecipeIngredients> getRecipeIngredients() {
        return recipeIngredients;
    }

    /**
     * Méthod to asign the list of recipe's ingredients
     * @param recipeIngredients list with the ingredients of the recipe to asign
     */
    public void setRecipeIngredients(List<RecipeIngredients> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    /**
     * Méthod to recover the list of recipe's steps
     * @return the list with the steps of the recipe
     */
    public List<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    /**
     * Méthod to asign the list of recipe's steps
     * @param recipeSteps list with the steps of the recipe to asign
     */
    public void setRecipeSteps(List<RecipeStep> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    /**
     * Méthod to recover the created date of the recipe
     * @return a date that represents the date of recipe step creation
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Méthod to asign the created date of the recipe
     * @param createdAt date that represents the date of recipe step creation to asign
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Méthod to recover if the recipe step is active or not
     * @return an integer that represents if the recipe step is active or not
     */
    public int getActive() {
        return active;
    }

    /**
     * Méthod to asign if the recipe step is active or not
     * @param active integer that represents if the recipe step is active or not to asign
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     * Méthod to recover if the the number of dinners for the recipe
     * @return an integer that represents the number of dinners for the recipe
     */
    public int getRecipeDinners() {
        return recipeDinners;
    }

    /**
     * Méthod to asign if the the number of dinners for the recipe
     * @param recipeDinners integer that represents the number of dinners for the recipe to asign
     */
    public void setRecipeDinners(int recipeDinners) {
        this.recipeDinners = recipeDinners;
    }

    /**
     * Méthod to recover if the the number of KCAL of the recipe
     * @return a float that represents the number of KCAL of the recipe
     */
    public float getRecipeKCAL() {
        return recipeKCAL;
    }

    /**
     * Méthod to asign if the the number of KCAL of the recipe
     * @param recipeKCAL float that represents the number of KCAL of the recipe to asign
     */
    public void setRecipeKCAL(float recipeKCAL) {
        this.recipeKCAL = recipeKCAL;
    }

    /**
     * Méthod to recover the preparation tipe of the recipe
     * @return an integer that represents the preparation time of the recipe
     */
    public int getRecipeTime() {
        return recipeTime;
    }

    /**
     * Méthod to asign the preparation tipe of the recipe
     * @param recipeTime integer that represents the preparation time of the recipe to asign
     */
    public void setRecipeTime(int recipeTime) {
        this.recipeTime = recipeTime;
    }

    /**
     * Méthod to recover the preparation difficult of the recipe
     * @return an integer that represents the preparation difficult of the recipe
     */
    public int getRecipeDifficult() {
        return recipeDifficult;
    }

    /**
     * Méthod to asign the preparation difficult of the recipe
     * @param recipeDifficult integer that represents the preparation difficult of the recipe to asign
     */
    public void setRecipeDifficult(int recipeDifficult) {
        this.recipeDifficult = recipeDifficult;
    }

    /**
     * Méthod to recover the assessment of the recipe
     * @return an integer that represents the assessment of the recipe
     */
    public int getRecipeAssessment() {
        return recipeAssessment;
    }

    /**
     * Méthod to asign assessment of the recipe
     * @param recipeAssessment integer that represents the assessment of the recipe to asign
     */
    public void setRecipeAssessment(int recipeAssessment) {
        this.recipeAssessment = recipeAssessment;
    }

    /**
     * Méthod to recover the initial description of the recipe
     * @return an string that represents the initial description of the recipe
     */
    public String getRecipeInitDescription() {
        return recipeInitDescription;
    }

    /**
     * Méthod to asign the initial description of the recipe
     * @param recipeInitDescription string that represents the initial description of the recipe to asign
     */
    public void setRecipeInitDescription(String recipeInitDescription) {
        this.recipeInitDescription = recipeInitDescription;
    }

    /**
     * Méthod to recover the ending description of the recipe
     * @return an integer that represents the ending description of the recipe
     */
    public String getRecipeEndingDescription() {
        return recipeEndingDescription;
    }

    /**
     * Méthod to asign the ending description of the recipe
     * @param recipeEndingDescription string that represents the ending description of the recipe to asign
     */
    public void setRecipeEndingDescription(String recipeEndingDescription) {
        this.recipeEndingDescription = recipeEndingDescription;
    }
    
    

}
