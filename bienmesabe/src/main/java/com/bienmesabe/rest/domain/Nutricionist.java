/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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

}
