/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.IngredientMineralDAO;
import com.bienmesabe.rest.domain.IngredientMineral;
import com.bienmesabe.rest.service.IngredientMineralService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface IngredientMineralService (service)
 * @author RAUL RAMOS CENDRERO
 * @version 22/05/2021
 */
@Service
public class IngredientMineralServiceImpl implements IngredientMineralService{

    /**
     * Bean of the ingredientmineral repository (Interface)
     */
    @Autowired
    private IngredientMineralDAO ingredientMineralDAO;
    
    /**
     * Implementation of interface method to recover the minerals present in the DB
     * @return a list with the minerals in the DB
     */
    @Override
    public List<IngredientMineral> findAllMinerals() {
        return ingredientMineralDAO.findAllMinerals();
    }
    
}
