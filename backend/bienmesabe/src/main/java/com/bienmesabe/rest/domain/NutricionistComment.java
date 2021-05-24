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
 * This class defines the comments of the nutricionist
 * @author RAUL
 * @version 24/05/2021
 */
@Entity
@Table(name="nutricionistComments")
public class NutricionistComment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    @Column(name="COMMENT")
    private String commentValue;
    @Column(name="NUTRICIONIST_ID")
    private Long nutricionistId;
    @Column(name="USER_ID")
    private Long userId;

    /**
     * Empty Constructor
     */
    public NutricionistComment() {
    }
    /**
     * Nutricionist Comment Constructor with the required parameters
     * @param id long that represents the id of the nutricionist comment to asign
     * @param commentValue string that represents the comment of the nutricionist to asign
     * @param nutricionistId long that represents the id of the nutricionist to asign
     * @param userId long that represents the id of the comment's owner to asign
     */
    public NutricionistComment(Long id, String commentValue, Long nutricionistId, Long userId) {
        this.id = id;
        this.commentValue = commentValue;
        this.nutricionistId = nutricionistId;
        this.userId = userId;
    }

    /**
     * Method to recover the id of the nutricionist comment
     * @return a long that represents the id of the nutricionist comment
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to asign the id of the nutricionist comment
     * @param id long that represents the id of the nutricionist comment to asign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to recover the comment value
     * @return a string that represents the comment value
     */
    public String getCommentValue() {
        return commentValue;
    }

    /**
     * Method to asign the comment value
     * @param commentValue string that represents the comment value to asign
     */
    public void setCommentValue(String commentValue) {
        this.commentValue = commentValue;
    }

    /**
     * Method to recover the id of the nutricionist
     * @return a long that represents the id of the nutricionist
     */
    public Long getNutricionistId() {
        return nutricionistId;
    }

    /**
     * Method to asign the id of the nutricionist
     * @param nutricionistId long that represents the id of the nutricionist to asign
     */
    public void setNutricionistId(Long nutricionistId) {
        this.nutricionistId = nutricionistId;
    }

    /**
     * Méthod to recover the id of recipe's owner user
     * @return a long that represents the id of recipe's owner user
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Méthod to asign the id of recipe's owner user
     * @param userId long that represents the id of recipe's owner user to asign
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    
    
}
