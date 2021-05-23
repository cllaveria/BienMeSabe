/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.IngredientMineral;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Inteface with the service methods to retrive the ingredient minerals
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Service
public interface IngredientMineralService {
    
    /**
     * Method to recover the minerals
     * @return a list with the minerals
     */
    public List<IngredientMineral> findAllMinerals();
}
