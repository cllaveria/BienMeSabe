/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;

import com.bienmesabe.rest.domain.IngredientMineral;
import com.bienmesabe.rest.service.IngredientMineralService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for IngredientMinerals // url: http://localhost:8080/api/mineral
 * @author RAUL
 * @version 19/05/2021
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/mineral")
public class IngredientMineralController {
    /**
     * Bean of the mineral service (Interface)
     */
    @Autowired
    private IngredientMineralService ingredientMineralService;
    
    /**
     * Method to recover all the vitamins  // HTTP verb: GET url: http://localhost:8080/api/mineral/getMinerals
     * @return a list of all minerals
     */
    @GetMapping("/getMinerals")
    public List<IngredientMineral> getMinerals(){
        return ingredientMineralService.findAllMinerals();
    }
}
