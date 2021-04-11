/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.Recipe;
import java.util.List;

/**
 *
 * @author RAUL
 */
public interface RecipeDAO {
    public List<Recipe> getAllRecipes();
    public List<Recipe> getRecipeByIngredients();
    public List<Recipe> getRecipeByKCal();
    public List<Recipe> getRecipeByType();
    public Recipe getRecipeById();
    public void createRecipe(Recipe recipe);
    public void modifyRecipe(Recipe recipe);
    public void deleteRecipe(long id);
}
