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
 *
 * @author RAUL
 */
@Entity
@Table(name="assessments")
public class Assessment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    
    @Column(name="assessment")
    private float assessmentValue;
    
    @Column(name="RECIPE_ID")
    private Long recipeId;
    @Column(name="USER_TYPE")
    private int type;

    public Assessment() {
    }

    public Assessment(float assessmentValue, Long recipeId, int type) {
        this.assessmentValue = assessmentValue;
        this.recipeId = recipeId;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAssessmentValue() {
        return assessmentValue;
    }

    public void setAssessmentValue(float assessmentValue) {
        this.assessmentValue = assessmentValue;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
}
