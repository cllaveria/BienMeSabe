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
 * This class defines the objects of type Recipe
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="recipetypes")
public class RecipeTypes implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    @Column(name="NAME")
    private String name;

    /**
     * Empty Constructor
     */
    public RecipeTypes() {
    }

    /**
     * RecipeTypes Constructor with name
     * @param name string that represents the name of the recipe type
     */
    public RecipeTypes(String name) {
        this.name = name;
    }

    /**
     * Method to recover the id of the recipe type
     * @return a long that represents the id of the recipe type
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Method to asign the id of the recipe type
     * @param id long that represents the id of the recipe type to asign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to recover the name of the recipe type
     * @return a string that represents the name of the recipe type
     */
    public String getName() {
        return name;
    }

    /**
     * Method to asign the name of the recipe type
     * @param name string that represents the name of the recipe type to asign
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
