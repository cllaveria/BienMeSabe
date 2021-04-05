/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.Ingredient;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author RAUL
 */
@Repository
public interface IngredientsDAO {
    public List<Ingredient> getAllIngredients();
    public Ingredient getIngredientById(Long id);
    public Ingredient getIngredientByName(String name);
    public List<Long> getIngredientsIdByNames(List<String> names);
    public List<Ingredient> getIngredientsByNames(List<String> names);
    public Long createIngredient(Ingredient ingredient);
    public void modifyIngredient(Ingredient ingredient);
    public void deleteIngredientById(Long id);
}
