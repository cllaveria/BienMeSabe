/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;

import com.bienmesabe.rest.domain.IngredientVitamin;
import com.bienmesabe.rest.service.IngredientVitaminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for IngredientVitamins // url: http://localhost:8080/api/vitamin
 * @author RAUL
 * @version 19/05/2021
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/vitamin")
public class IngredientVitaminController {
    
    /**
     * Bean of the vitamin service (Interface)
     */
    @Autowired
    private IngredientVitaminService ingredientVitaminService;
    
     /**
     * Method to recover all the vitamins  // HTTP verb: GET url: http://localhost:8080/api/vitamin/getVitamins
     * @return a list of all vitamins
     */
    @GetMapping("/getVitamins")
    public List<IngredientVitamin> getVitamins(){
        return ingredientVitaminService.findAllVitamins();
    }
}
