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
@Table(name="ingredients")
public class Ingredient implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    @Column(name="NAME")
    private String name;
    @Column(name="PROTEINS")
    private float proteins;
    @Column(name="SATURED")
    private float saturedFats;
    @Column(name="FAT")
    private float fat;
    @Column(name="CARBOHIDRATES")
    private float carbohidrates;
    @Column(name="FIBER")
    private float fiber;
    @Column(name="SODIUM")
    private float sodium;
    @Column(name="SUGARS")
    private float sugars;

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public float getSaturedFats() {
        return saturedFats;
    }

    public void setSaturedFats(float saturedFats) {
        this.saturedFats = saturedFats;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getCarbohidrates() {
        return carbohidrates;
    }

    public void setCarbohidrates(float carbohidrates) {
        this.carbohidrates = carbohidrates;
    }

    public float getFiber() {
        return fiber;
    }

    public void setFiber(float fiber) {
        this.fiber = fiber;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    public float getSugars() {
        return sugars;
    }

    public void setSugars(float sugars) {
        this.sugars = sugars;
    }
    
    
    
    
}
