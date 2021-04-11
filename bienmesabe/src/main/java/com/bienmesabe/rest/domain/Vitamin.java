/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class defines the objects of type Vitamin
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="vitamins")
public class Vitamin implements Serializable {
    @Id
    @Column(name="ID")
    private String ID;
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
    public Vitamin() {
    }
    /**
     * Constructor of the Vitamin class with name
     * @param name string that represents the name of the vitamin
     */
    public Vitamin(String name) {
        this.name = name;
    }

    /**
     * Constructor of the Vitamin class with the required parameters
     * @param type string that represents the type of the vitamin
     * @param name string that represents the name of the vitamin
     * @param functions string that represents the functions of the vitamin
     * @param dailyQTYMin float wich indicates the minimum intake recommended of the vitamin
     * @param dailyQTYMax float wich indicates the maximum intake recommended of the vitamin
     * @param presence string wich indicates the foods on which the vitamin is found
     */
    public Vitamin(String type, String name, String functions, float dailyQTYMin, float dailyQTYMax, String presence) {
        this.type = type;
        this.name = name;
        this.functions = functions;
        this.dailyQTYMin = dailyQTYMin;
        this.dailyQTYMax = dailyQTYMax;
        this.presence = presence;
    }

    /**
     * Méthod to recover the id of the vitamin
     * @return an string that represents the id of the vitamin
     */
    public String getID() {
        return ID;
    }
    
    /**
     * Méthod to asign the id of the vitamin
     * @param ID string that represents the id of the vitamin
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Méthod to recover the type of the vitamin
     * @return an string that represents the type of the vitamin
     */
    public String getType() {
        return type;
    }

    /**
     * Méthod to asign the type of the vitamin
     * @param type string that represents the type of the vitamin to asign
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Méthod to recover the name of the vitamin
     * @return an string that represents the name of the vitamin
     */
    public String getName() {
        return name;
    }

    /**
     * Méthod to asign the name of the vitamin
     * @param name string that represents the name of the vitamin to asign
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthod to recover the vitamin functions
     * @return an string that represents the vitamin functions
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
     * Méthod to recover the minimum intake recommended of the vitamin
     * @return a float that represents the minimunm intake recommended of the vitamin
     */
    public float getDailyQTYMin() {
        return dailyQTYMin;
    }

    /**
     * Méthod to asign the minimum intake recommended of the vitamin
     * @param dailyQTYMin float wich indicates the minimum intake recommended of the vitamin
     */
    public void setDailyQTYMin(float dailyQTYMin) {
        this.dailyQTYMin = dailyQTYMin;
    }

    /**
     * Méthod to recover the maximum intake recommended of the vitamin
     * @return a float that represents the maximum intake recommended of the vitamin
     */
    public float getDailyQTYMax() {
        return dailyQTYMax;
    }

    /**
     * Méthod to asign the maximum intake recommended of the vitamin
     * @param dailyQTYMax float wich indicates the maximum intake recommended of the vitamin
     */
    public void setDailyQTYMax(float dailyQTYMax) {
        this.dailyQTYMax = dailyQTYMax;
    }

    /**
     * Méthod to recover the foods on which the vitmain is found
     * @return an string wich indicates the foods on which the vitamin is found
     */
    public String getPresence() {
        return presence;
    }

    /**
     * Méthod to asign the foods on which the vitmain is found
     * @param presence string wich indicates the foods on which the vitamin is found
     */
    public void setPresence(String presence) {
        this.presence = presence;
    }
    
}
