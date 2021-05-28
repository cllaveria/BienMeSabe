/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.IngredientVitamin;
import java.util.List;

/**
 * Inteface with the methods to retrive the DB data of vitamins
 * @author RAUL
 * @version 19/05/2021 
 */
public interface IngredientVitaminDAO {
    
    /**
     * Method to recover the vitamins present in the DB
     * @return a list with the vitamins in the DB
     */
    public List<IngredientVitamin> findAllVitamins();
}
