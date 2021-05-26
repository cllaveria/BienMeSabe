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
 * This class defines the comments of the recipes
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="comments")
public class Comment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    @Column(name="comment")
    private String commentValue;
    @Column(name="RECIPE_ID")
    private Long recipeId;
    @Column(name="USER_ID")
    private Long userId;

    /**
     * Empty Constructor
     */
    public Comment() {
    }

    /**
     * Comment Constructor with the required parameters
     * @param id long that represents the id of the comment to asign
     * @param commentValue string that represents the comment of the recipe to asign
     * @param recipeId long that represents the id of the recipe to asign
     * @param userId long that represents the id of the recipe's owner to asign
     */
    public Comment(Long id, String commentValue, Long recipeId, Long userId) {
        this.id = id;
        this.commentValue = commentValue;
        this.recipeId = recipeId;
        this.userId = userId;
    }

    /**
     * Method to recover the id of the comment
     * @return a long that represents the id of the comment
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to asign the id of the comment
     * @param id long that represents the id of the comment to asign
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
     * Method to recover the id of the recipe
     * @return a long that represents the id of the recipe
     */
    public Long getRecipeId() {
        return recipeId;
    }

    /**
     * Method to asign the id of the recipe
     * @param recipeId long that represents the id of the recipe to asign
     */
    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
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
