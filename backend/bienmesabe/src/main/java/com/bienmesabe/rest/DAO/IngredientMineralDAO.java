/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.IngredientMineral;
import java.util.List;

/**
 * Inteface with the methods to retrive the DB data of minerals
 * @author RAUL
 * @version 19/05/2021
 */
public interface IngredientMineralDAO {
    
    /**
     * Method to recover the minerals present in the DB
     * @return a list with the minerals in the DB
     */
    public List<IngredientMineral> findAllMinerals();
    
}
