/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private List<RecipeIngredients> recipeIngredients;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
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
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "RECIPE_ID")
    private List<Assessment> assessments;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private List<Comment> comments;
    
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

    /**
     * Recipe constructor with the required parameters
     * @param image string that represents the path of the recipe image
     * @param name string that represents the name of the recipe
     * @param preparationVideo string that represents the path of the preparation video of the recipe
     * @param type integer that represents the type of the recipe
     * @param userId long that represents the if of the recipe owner
     * @param recipeIngredients list of the recipe ingredients
     * @param recipeSteps list of the recipe steps
     * @param createdAt date of the recipe creation 
     * @param active boolean that represents if the recipe is active or not
     * @param recipeDinners integer that indicates the dinners of the recipe
     * @param recipeKCAL float that indicates the Kcal of the recipe
     * @param recipeTime integer that represents the time of the recipe preparation
     * @param recipeDifficult integer that represents de difficult of the recipe preparation (1 to 5)
     * @param recipeAssessment integer that represents the value of the recipe assessment
     * @param recipeInitDescription string that represents the initial description of the recipe
     * @param recipeEndingDescription string that represents the ending description of the recipe
     */
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
     * M??thod to recover the type of the recipe
     * @return integer that represents the type of the recipe
     */
    public int getType() {
        return type;
    }

    /**
     * M??thod to asign the type of the recipe
     * @param type integer that represents the type of the user to recipe
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * M??thod to recover the id of recipe's owner user
     * @return a long that represents the id of recipe's owner user
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * M??thod to asign the id of recipe's owner user
     * @param userId long that represents the id of recipe's owner user to asign
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * M??thod to recover the list of recipe's ingredients
     * @return the list with the ingredients of the recipe
     */
    public List<RecipeIngredients> getRecipeIngredients() {
        return recipeIngredients;
    }

    /**
     * M??thod to asign the list of recipe's ingredients
     * @param recipeIngredients list with the ingredients of the recipe to asign
     */
    public void setRecipeIngredients(List<RecipeIngredients> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    /**
     * M??thod to recover the list of recipe's steps
     * @return the list with the steps of the recipe
     */
    public List<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    /**
     * M??thod to asign the list of recipe's steps
     * @param recipeSteps list with the steps of the recipe to asign
     */
    public void setRecipeSteps(List<RecipeStep> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    /**
     * M??thod to recover the created date of the recipe
     * @return a date that represents the date of recipe step creation
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * M??thod to asign the created date of the recipe
     * @param createdAt date that represents the date of recipe step creation to asign
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * M??thod to recover if the recipe step is active or not
     * @return an integer that represents if the recipe step is active or not
     */
    public int getActive() {
        return active;
    }

    /**
     * M??thod to asign if the recipe step is active or not
     * @param active integer that represents if the recipe step is active or not to asign
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     * M??thod to recover if the the number of dinners for the recipe
     * @return an integer that represents the number of dinners for the recipe
     */
    public int getRecipeDinners() {
        return recipeDinners;
    }

    /**
     * M??thod to asign if the the number of dinners for the recipe
     * @param recipeDinners integer that represents the number of dinners for the recipe to asign
     */
    public void setRecipeDinners(int recipeDinners) {
        this.recipeDinners = recipeDinners;
    }

    /**
     * M??thod to recover if the the number of KCAL of the recipe
     * @return a float that represents the number of KCAL of the recipe
     */
    public float getRecipeKCAL() {
        return recipeKCAL;
    }

    /**
     * M??thod to asign if the the number of KCAL of the recipe
     * @param recipeKCAL float that represents the number of KCAL of the recipe to asign
     */
    public void setRecipeKCAL(float recipeKCAL) {
        this.recipeKCAL = recipeKCAL;
    }

    /**
     * M??thod to recover the preparation tipe of the recipe
     * @return an integer that represents the preparation time of the recipe
     */
    public int getRecipeTime() {
        return recipeTime;
    }

    /**
     * M??thod to asign the preparation tipe of the recipe
     * @param recipeTime integer that represents the preparation time of the recipe to asign
     */
    public void setRecipeTime(int recipeTime) {
        this.recipeTime = recipeTime;
    }

    /**
     * M??thod to recover the preparation difficult of the recipe
     * @return an integer that represents the preparation difficult of the recipe
     */
    public int getRecipeDifficult() {
        return recipeDifficult;
    }

    /**
     * M??thod to asign the preparation difficult of the recipe
     * @param recipeDifficult integer that represents the preparation difficult of the recipe to asign
     */
    public void setRecipeDifficult(int recipeDifficult) {
        this.recipeDifficult = recipeDifficult;
    }

    /**
     * M??thod to recover the assessment of the recipe
     * @return an integer that represents the assessment of the recipe
     */
    public int getRecipeAssessment() {
        return recipeAssessment;
    }

    /**
     * M??thod to asign assessment of the recipe
     * @param recipeAssessment integer that represents the assessment of the recipe to asign
     */
    public void setRecipeAssessment(int recipeAssessment) {
        this.recipeAssessment = recipeAssessment;
    }

    /**
     * M??thod to recover the initial description of the recipe
     * @return an string that represents the initial description of the recipe
     */
    public String getRecipeInitDescription() {
        return recipeInitDescription;
    }

    /**
     * M??thod to asign the initial description of the recipe
     * @param recipeInitDescription string that represents the initial description of the recipe to asign
     */
    public void setRecipeInitDescription(String recipeInitDescription) {
        this.recipeInitDescription = recipeInitDescription;
    }

    /**
     * M??thod to recover the ending description of the recipe
     * @return an integer that represents the ending description of the recipe
     */
    public String getRecipeEndingDescription() {
        return recipeEndingDescription;
    }

    /**
     * M??thod to asign the ending description of the recipe
     * @param recipeEndingDescription string that represents the ending description of the recipe to asign
     */
    public void setRecipeEndingDescription(String recipeEndingDescription) {
        this.recipeEndingDescription = recipeEndingDescription;
    }

    /**
     * M??thod to recover the comments of the recipe
     * @return a list with all commments of the recipe
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * M??thod to asign the comments of the recipe
     * @param comments list with all commments of the recipe
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * M??thod to recover the assessments of the recipe
     * @return a list with all assessments of the recipe
     */
    public List<Assessment> getAssessments() {
        return assessments;
    }

    /**
     * M??thod to asign the assessments of the recipe
     * @param assessments list with all assessments of the recipe
     */
    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
    
    

}
