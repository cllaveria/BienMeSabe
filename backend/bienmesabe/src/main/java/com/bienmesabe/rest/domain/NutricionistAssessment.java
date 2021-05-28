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
@Table(name="nutricionistassessments")
public class NutricionistAssessment implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    
    @Column(name="assessment")
    private int assessmentValue;
    
    @Column(name="NUTRICIONIST_ID")
    private Long nutricionistId;
    
    @Column(name="USER_ID")
    private long userId;

    /**
     * Empty constructor
     */
    public NutricionistAssessment() {
    }

    /**
     * NutricionistAssessment Constructor with the required parameters
     * @param assessmentValue integer that represents the value of the assessment
     * @param nutricionistId long that represents the nutricionist id
     * @param userId long that represents the id of the user
     */
    public NutricionistAssessment(int assessmentValue, Long nutricionistId, long userId) {
        this.assessmentValue = assessmentValue;
        this.nutricionistId = nutricionistId;
        this.userId = userId;
    }

    /**
     * Method to recover the id of the nutricionist assessment
     * @return a long that represents the id of the nutricionist assessment
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to asign the id of the nutricionist assessment
     * @param id long that represents the id of the nutricionist assessment to asign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to recover the value of the nutricionist assessment
     * @return an integer with the value of the assessment
     */
    public int getAssessmentValue() {
        return assessmentValue;
    }

    /**
     * Method to asign the value of the nutricionist assessment
     * @param assessmentValue integer with the value of the assessment
     */
    public void setAssessmentValue(int assessmentValue) {
        this.assessmentValue = assessmentValue;
    }

    /**
     * Method to recover the id of the nutricionist 
     * @return a long that represents the id of the nutricionist 
     */
    public Long getNutricionistId() {
        return nutricionistId;
    }

    /**
     * Method to asign the id of the nutricionist
     * @param nutricionistId long that represents the id of the nutricionist 
     */
    public void setNutricionistId(Long nutricionistId) {
        this.nutricionistId = nutricionistId;
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
     * @param userId long that represents the id of the user 
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }
    
    
}
