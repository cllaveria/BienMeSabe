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

    public Mineral() {
    }

    public Mineral(String name) {
        this.name = name;
    }

    public Mineral(String type, String name, String functions, float dailyQTYMin, float dailyQTYMax, String presence) {
        this.type = type;
        this.name = name;
        this.functions = functions;
        this.dailyQTYMin = dailyQTYMin;
        this.dailyQTYMax = dailyQTYMax;
        this.presence = presence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public float getDailyQTYMin() {
        return dailyQTYMin;
    }

    public void setDailyQTYMin(float dailyQTYMin) {
        this.dailyQTYMin = dailyQTYMin;
    }

    public float getDailyQTYMax() {
        return dailyQTYMax;
    }

    public void setDailyQTYMax(float dailyQTYMax) {
        this.dailyQTYMax = dailyQTYMax;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }
    
    
}
