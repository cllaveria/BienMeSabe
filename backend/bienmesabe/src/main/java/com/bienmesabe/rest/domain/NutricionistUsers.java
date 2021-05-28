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
 * This class defines the asigned users to a nutricionist
 * @author RAUL
 * @version 24/05/2021
 */
@Entity
@Table(name="nutricionistUsers")
public class NutricionistUsers implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    @Column(name="NUTRICIONIST_ID")
    private long nutricionistId;
    @Column(name="USER_ID")
    private long userId;

    public NutricionistUsers() {
    }

    public NutricionistUsers(long nutricionistId, long userId) {
        this.nutricionistId = nutricionistId;
        this.userId = userId;
    }

    public NutricionistUsers(Long id, long nutricionistId, long userId) {
        this.id = id;
        this.nutricionistId = nutricionistId;
        this.userId = userId;
    }
     

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getNutricionistId() {
        return nutricionistId;
    }

    public void setNutricionistId(long nutricionistId) {
        this.nutricionistId = nutricionistId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    
    
}
