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
 * This class defines the objects of type Mineral
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="minerals")
public class Mineral implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    
    @Column(name="TYPE")
    private String type;
    @Column(name="NAME")
    private String name;
    @Column(name="FUNCTIONS")
    private String functions;
    @Column(name="MIN_DIARY")
    private float dailyQTYMin;
    @Column(name="MAX_DIARY")
    private float dailyQTYMax;
    @Column(name="PRESENCE")
    private String presence;

    /**
     * Empty Constructor
     */
    public Mineral() {
    }

    /**
     * Constructor of the Mineral class with name
     * @param name string that represents the name of the mineral
     */
    public Mineral(String name) {
        this.name = name;
    }

    /**
     * Constructor of the Mineral class with the required parameters
     * @param type string that represents the type of the mineral
     * @param name string that represents the name of the mineral
     * @param functions string that represents the functions of the mineral
     * @param dailyQTYMin float wich indicates the minimum intake recommended of the mineral
     * @param dailyQTYMax float wich indicates the maximum intake recommended of the mineral
     * @param presence string wich indicates the foods on which the mineral is found
     */
    public Mineral(String type, String name, String functions, float dailyQTYMin, float dailyQTYMax, String presence) {
        this.type = type;
        this.name = name;
        this.functions = functions;
        this.dailyQTYMin = dailyQTYMin;
        this.dailyQTYMax = dailyQTYMax;
        this.presence = presence;
    }

    /**
     * Méthod to recover the id of the mineral
     * @return an string that represents the id of the mineral
     */
    public Long getId() {
        return id;
    }

    /**
     * Méthod to asign the id of the mineral
     * @param ID string that represents the id of the mineral
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Méthod to recover the type of the mineral
     * @return an string that represents the type of the mineral
     */
    public String getType() {
        return type;
    }

    /**
     * Méthod to asign the type of the mineral
     * @param type string that represents the type of the mineral to asign
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Méthod to recover the name of the mineral
     * @return an string that represents the name of the mineral
     */
    public String getName() {
        return name;
    }

    /**
     * Méthod to asign the name of the mineral
     * @param name string that represents the name of the mineral to asign
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthod to recover the mineral functions
     * @return an string that represents the mineral functions
     */
    public String getFunctions() {
        return functions;
    }

    /**
     * Méthod to asign the vitamin functions
     * @param functions string that represents the vitamin functions to asign
     */
    public void setFunctions(String functions) {
        this.functions = functions;
    }

    /**
     * Méthod to recover the minimum intake recommended of the mineral
     * @return a float that represents the minimunm intake recommended of the mineral
     */
    public float getDailyQTYMin() {
        return dailyQTYMin;
    }

    /**
     * Méthod to asign the minimum intake recommended of the mineral
     * @param dailyQTYMin float wich indicates the minimum intake recommended of the mineral
     */
    public void setDailyQTYMin(float dailyQTYMin) {
        this.dailyQTYMin = dailyQTYMin;
    }

    /**
     * Méthod to recover the maximum intake recommended of the mineral
     * @return a float that represents the maximum intake recommended of the mineral
     */
    public float getDailyQTYMax() {
        return dailyQTYMax;
    }

     /**
     * Méthod to asign the maximum intake recommended of the mineral
     * @param dailyQTYMax float wich indicates the maximum intake recommended of the mineral
     */
    public void setDailyQTYMax(float dailyQTYMax) {
        this.dailyQTYMax = dailyQTYMax;
    }

    /**
     * Méthod to recover the foods on which the mineral is found
     * @return an string wich indicates the foods on which the mineral is found
     */
    public String getPresence() {
        return presence;
    }

    /**
     * Méthod to asign the foods on which the mineral is found
     * @param presence string wich indicates the foods on which the mineral is found
     */
    public void setPresence(String presence) {
        this.presence = presence;
    }
    
    
}
