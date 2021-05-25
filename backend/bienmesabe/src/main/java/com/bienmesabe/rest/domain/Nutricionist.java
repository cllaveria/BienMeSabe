/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.domain;

import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * This class defines the nutricionists (inherits from User class)
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="nutricionists")
@PrimaryKeyJoinColumn(name="ID")
public class Nutricionist extends User{
    @Column(name="COMPANY_NAME")
    private String companyName;
    @Column(name="COMPANY_DIRECTION")
    private String companyDirection;
    @Column(name="COMPANY_POSTAL_CODE")
    private String companyPostalCode;
    @Column(name="COMPANY_CITY")
    private String companyCity;
    @Column(name="COMPANY_TELEPHONE")
    private String companyPhone;
    @Column(name="NUTRICIONIST_DESCRIPTION")
    private String nutricionistDescription = "";
    
    @Column(name="NUTRICIONIST_ASSESSMENT")
    private int nutricionistAssessment = 0;
    
    @OneToMany(mappedBy = "nutricionist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NutricionistDegree> nutricionistDegree = Collections.EMPTY_LIST;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "NUTRICIONIST_ID")
    private List<NutricionistAssessment> assessments = Collections.EMPTY_LIST;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "NUTRICIONIST_ID")
    private List<NutricionistComment> comment = Collections.EMPTY_LIST;
    
    /**
     * Empty Constructor
     */
    public Nutricionist() {
    }

    /**
     * Nutricionist Constructor with the required parameters
     * @param companyName string that represents the name of the nutricionist company 
     * @param companyDirection string that represents the direction of the nutricionist company 
     * @param companyPostalCode string that represents the postal code of the nutricionist company 
     * @param companyCity string that represents the city of the nutricionist company 
     * @param companyPhone string that represents the phone of the nutricionist company 
     */
    public Nutricionist(String companyName, String companyDirection, String companyPostalCode, String companyCity, String companyPhone) {
        this.companyName = companyName;
        this.companyDirection = companyDirection;
        this.companyPostalCode = companyPostalCode;
        this.companyCity = companyCity;
        this.companyPhone = companyPhone;
    }

    /**
     * Method to recover the name of the nutricionist company 
     * @return an string that represents the name of the nutricionist company 
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Method to asign the name of the nutricionist company 
     * @param companyName string that represents the name of the nutricionist company to asign
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Method to recover the direction of the nutricionist company 
     * @return an string that represents the direction of the nutricionist company 
     */
    public String getCompanyDirection() {
        return companyDirection;
    }

    /**
     * Method to asign the direction of the nutricionist company 
     * @param companyDirection string that represents the direction of the nutricionist company to asign
     */
    public void setCompanyDirection(String companyDirection) {
        this.companyDirection = companyDirection;
    }

    /**
     * Method to recover the postal code of the nutricionist company 
     * @return an string that represents the postal code of the nutricionist company 
     */
    public String getCompanyPostalCode() {
        return companyPostalCode;
    }

    /**
     * Method to asign the postal code of the nutricionist company 
     * @param companyPostalCode string that represents the postal code of the nutricionist company to asign
     */
    public void setCompanyPostalCode(String companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }

    /**
     * Method to recover the city of the nutricionist company 
     * @return an string that represents the city of the nutricionist company 
     */
    public String getCompanyCity() {
        return companyCity;
    }

    /**
     * Method to asign the city of the nutricionist company 
     * @param companyCity string that represents the city of the nutricionist company to asign
     */
    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    /**
     * Method to recover the phone of the nutricionist company 
     * @return an string that represents the phone of the nutricionist company 
     */
    public String getCompanyPhone() {
        return companyPhone;
    }

    /**
     * Method to asign the phone of the nutricionist company 
     * @param companyPhone string that represents the phone of the nutricionist company to asign      
     */
    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    /**
     * Method to recover the list of the degrees of the nutricionist  
     * @return a list that represents the degrees of the nutricionist  
     */
    public List<NutricionistDegree> getNutricionistDegree() {
        return nutricionistDegree;
    }

    /**
     * Method to asign the list of the degrees of the nutricionist  
     * @param nutricionistDegree list that represents the degrees of the nutricionist  
     */
    public void setNutricionistDegree(List<NutricionistDegree> nutricionistDegree) {
        this.nutricionistDegree = nutricionistDegree;
    }

    /**
     * Method to recover the list of the assessments of the nutricionist  
     * @return a list that represents the assessments of the nutricionist  
     */
    public List<NutricionistAssessment> getAssessments() {
        return assessments;
    }

    /**
     * Method to asign the list of the assessments of the nutricionist  
     * @param assessments list that represents the assessments of the nutricionist  
     */
    public void setAssessments(List<NutricionistAssessment> assessments) {
        this.assessments = assessments;
    }
    
    /**
     * Method to recover the nutricionist assessment
     * @return an integer that represents the assessment of the nutricionist  
     */
    public int getNutricionistAssessment() {
        return nutricionistAssessment;
    }

    /**
     * Method to asign the nutricionist assessment
     * @param nutricionistAssessment integer that represents the assessment of the nutricionist to set
     */
    public void setNutricionistAssessment(int nutricionistAssessment) {
        this.nutricionistAssessment = nutricionistAssessment;
    }

    /**
     * Method to recover the nutricionist comments
     * @return an integer that represents the comments of the nutricionist  
     */
    public List<NutricionistComment> getComment() {
        return comment;
    }

    /**
     * Method to asign the nutricionist comments
     * @param comment integer that represents the comments of the nutricionist to set
     */
    public void setComment(List<NutricionistComment> comment) {
        this.comment = comment;
    }
    
    /**
     * Method to recover the nutricionist description
     * @return an string that represents the description of the nutricionist  
     */
    public String getNutricionistDescription() {
        return nutricionistDescription;
    }

    /**
     * Method to asign the nutricionist description
     * @param nutricionistDescription string that represents the description of the nutricionist  
     */
    public void setNutricionistDescription(String nutricionistDescription) {
        this.nutricionistDescription = nutricionistDescription;
    }

    
    
}
