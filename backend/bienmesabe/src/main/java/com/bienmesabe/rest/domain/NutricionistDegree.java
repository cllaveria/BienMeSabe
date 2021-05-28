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
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * This class defines the titulation of the nutricionists
 * @author RAUL
 * @version 19/05/2021
 */
@Entity
@Table(name="nutricionistdegree")
public class NutricionistDegree implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    @JoinColumn(name="id")
    private Long degreeid;
    @Column(name="degree_name")
    private String name;
    @Column(name="degree_year")
    private int year;
    @Column(name="degree_scool")
    private String school;
    @Column(name="degree_description")
    private String description;

    @Column(name="NUTRICIONIST_ID")
    private long nutricionistId;
    
    
    /**
     * Empty constructor
     */
    public NutricionistDegree() {
    }

    /**
     * NutricionistDegree Constructor with the required parameters
     * @param name string that represents the name of the degree
     * @param year integer that represents the year of the degree
     * @param school string that represents the entity that issues the degree
     * @param description string that represents the description of the degree
     */
    public NutricionistDegree(String name, int year, String school, String description) {
        this.name = name;
        this.year = year;
        this.school = school;
        this.description = description;
    }

    /**
     * Méthod to recover the id of the degree
     * @return a long that represents the id of the degree
     */
    public Long getId() {
        return degreeid;
    }

    /**
     * Méthod to asign the id of the degree
     * @param id long that represents the id of the degree
     */
    public void setId(Long id) {
        this.degreeid = id;
    }

    /**
     * Méthod to recover the name of the degree
     * @return a string that represents the name of the degree
     */
    public String getName() {
        return name;
    }

    /**
     * Méthod to asign the name of the degree
     * @param name string that represents the name of the degree to asign
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthod to recover the year of the degree
     * @return an integer that represents the year of the degree
     */
    public int getYear() {
        return year;
    }

    /**
     * Méthod to asign the year of the degree
     * @param year integer that represents the year of the degree
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Méthod to recover the entity that issues the degree
     * @return an string that represents the entity that issues the degree
     */
    public String getSchool() {
        return school;
    }

    /**
     * Méthod to asign the entity that issues the degree
     * @param school string that represents the entity that issues the degree
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * Méthod to recover the description of the degree
     * @return an string that represents the description of the degree
     */
    public String getDescription() {
        return description;
    }

    /**
     * Méthod to asign the description of the degree
     * @param description string that represents the description of the degree
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Méthod to recover the nutricionist id of the degree
     * @return an long that represents the nutricionist id of the degree
     */
    public long getNutricionistId() {
        return nutricionistId;
    }

    /**
     * Méthod to asign the id of the nutricionist
     * @param nutricionistId long that represents the id of the nutricionist
     */
    public void setNutricionistId(long nutricionistId) {
        this.nutricionistId = nutricionistId;
    }

    
}
