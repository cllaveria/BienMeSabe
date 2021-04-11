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
 * This class defines the type of the users
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="usertypes")
public class UserTypes implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    @Column(name="NAME")
    private String name;

    /**
     * Empty Constructor
     */
    public UserTypes() {
    }

    /**
     * UserTypes Constructor with name
     * @param name string that represents the name of the user's type
     */
    public UserTypes(String name) {
        this.name = name;
    }

    /**
     * Method to recover the id of the user's type
     * @return a long that represents the id of the user's type
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to asign the id of the user's type
     * @param id long that represents the id of the user's type to asign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to recover the name of the user's type
     * @return a string that represents the name of the user's type
     */
    public String getName() {
        return name;
    }

    /**
     * Method to asign the name of the user's type
     * @param name string that represents the name of the user's type to asign
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
