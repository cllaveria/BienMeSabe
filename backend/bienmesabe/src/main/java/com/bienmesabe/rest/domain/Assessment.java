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
import javax.persistence.Table;

/**
 * This class defines the assessment of the recipes
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="assessments")
public class Assessment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    
    @Column(name="assessment")
    private int assessmentValue;
    
    @Column(name="RECIPE_ID")
    private Long recipeId;
    @Column(name="USER_ID")
    private long userId;

    /**
     * Empty Constructor
     */
    public Assessment() {
    }

    /**
     * Assessment Constructor with the required parameters
     * @param assessmentValue float that represents the assessment of the recipe to asign
     * @param recipeId long that represents the id of the recipe to asign
     * @param userId long that represents the id of the user
     */
    public Assessment(int assessmentValue, Long recipeId, long userId) {
        this.assessmentValue = assessmentValue;
        this.recipeId = recipeId;
        this.userId = userId;
    }

    /**
     * Method to recover the id of the assessment
     * @return a long that represents the id of the assessment
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to asign the id of the assessment
     * @param id long that represents the id of the assessment to asign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to recover the assessment value
     * @return a string that represents the assessment value
     */
        public int getAssessmentValue() {
        return assessmentValue;
    }

    /**
     * Method to asign the assessment value
     * @param assessmentValue string that represents the assessment value to asign
     */
    public void setAssessmentValue(int assessmentValue) {
        this.assessmentValue = assessmentValue;
    }

    /**
     * Method to recover the id of the recipe
     * @return a long that represents the id of the recipe
     */
    public Long getRecipeId() {
        return recipeId;
    }

    /**
     * Method to asign the id of the recipe
     * @param recipeId long that represents the id of the recipe to asign
     */
    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }
    
    /**
     * Method to recover the id of the user
     * @return a long that represents the id of the user
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Method to asign the id of the user
     * @param userId a long that represents the id of the user
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    
    
}
