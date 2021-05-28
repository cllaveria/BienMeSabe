/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.IngredientVitaminDAO;
import com.bienmesabe.rest.domain.IngredientVitamin;
import com.bienmesabe.rest.service.IngredientVitaminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface IngredientVitaminService (service)
 * @author RAUL RAMOS CENDRERO
 * @version 22/05/2021
 */
@Service
public class IngredientVitaminServiceImpl implements IngredientVitaminService{
    /**
     * Bean of the ingredientmineral repository (Interface)
     */
    @Autowired
    private IngredientVitaminDAO ingredientVitaminDAO;
    
    /**
     * Implementation of interface method to recover the minerals present in the DB
     * @return a list with the minerals in the DB
     */
    @Override
    public List<IngredientVitamin> findAllVitamins() {
        return ingredientVitaminDAO.findAllVitamins();
    }
}
