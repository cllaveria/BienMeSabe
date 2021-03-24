/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author RAUL
 */
@Entity
@Table(name="recipe")
public class Recipe implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    @Column(name="IMAGE")
    private String image;
    @Column(name="NAME")
    private String name;
    @Column(name="PREPARATION_VIEDO")
    private String preparationVideo;
    @Column(name="TYPE")
    private int type;
    @Column(name="USER_ID")
    private Long userId;
    @OneToMany
    private List<RecipeIngredients> recipeIngredients;
    @OneToMany
    private List<RecipeStep> recipeSteps;

        public Recipe() {
        }

        public Recipe(String name, Long userId) {
            this.name = name;
            this.userId = userId;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPreparationVideo() {
            return preparationVideo;
        }

        public void setPreparationVideo(String preparationVideo) {
            this.preparationVideo = preparationVideo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public List<RecipeIngredients> getRecipeIngredients() {
            return recipeIngredients;
        }

        public void setRecipeIngredients(List<RecipeIngredients> recipeIngredients) {
            this.recipeIngredients = recipeIngredients;
        }

        public List<RecipeStep> getRecipeSteps() {
            return recipeSteps;
        }

        public void setRecipeSteps(List<RecipeStep> recipeSteps) {
            this.recipeSteps = recipeSteps;
        }

}
