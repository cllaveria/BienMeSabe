/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;

import com.bienmesabe.rest.domain.Nutricionist;
import com.bienmesabe.rest.service.NutricionistService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Nutricionists // url: http://localhost:8080/api/nutricionist
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/nutricionist")
public class NutricionistController {
    
    /**
     * Bean of the nutricionist service (Interface)
     */
    @Autowired
    private NutricionistService nutricionistService;
    
    /**
     * Method to recover the nutricionists  // HTTP verb: GET url: http://localhost:8080/api/nutricionist/getNutricionists
     * @return a list with the nutricionists
     */
    @GetMapping("/getNutricionists")
    public List<Nutricionist> getNutricionists(){
        return nutricionistService.findAllNutricionist();
    }
    
    /**
     * Method to recover the nutricionists by id  // HTTP verb: GET url: http://localhost:8080/api/nutricionist/findNutricionistByCP/{PostalCode}
     * @param id string that represents the id of the nutricionist to search
     * @return the nutricionist filtered by id
     */
    @GetMapping("/getNutricionistById/{id}")
    public Nutricionist findUserById(@PathVariable String id){
        Nutricionist nutricionist = nutricionistService.findNutricionistById(Long.parseLong(id));
        return nutricionist;
    }
    
    /**
     * Method to recover the nutricionists by postal code  // HTTP verb: GET url: http://localhost:8080/api/nutricionist/findNutricionistByCP/{PostalCode}
     * @param cp string that represents the postal code of the nutricionists to search
     * @return a list with the nutricionists filtered by postal code
     */
    @GetMapping("/findNutricionistByCP/{cp}")
    public Nutricionist findNutricionistByCP(@PathVariable String cp){
        Nutricionist nutricionist = nutricionistService.findNutricionistByCP(cp);
        return nutricionist;
    }
    
    /**
     * Method to recover the nutricionists by postal code range  // HTTP verb: GET url: http://localhost:8080/api/nutricionist/findNutricionistByCPRange/{PostalCodeMin}/{PostalCodeMax}
     * @param cpMin string that represents the mimimum postal code of the nutricionists to search
     * @param cpMax string that represents the maximum postal code of the nutricionists to search
     * @return a list with the nutricionists filtered by postal code range
     */
    @GetMapping("/findNutricionistByCPRange/{cpRanges}")
    public List<Nutricionist> findNutricionistByCPRange(@PathVariable String cpRanges){
        String cpMin = cpRanges.split("-")[0];
        String cpMax = cpRanges.split("-")[1];
        List<Nutricionist> nutricionists = nutricionistService.findNutricionistByCPRange(cpMin, cpMax);
        return nutricionists;
    }
    
    /**
     * Method to create a nutricionist  // HTTP verb: POST url: http://localhost:8080/api/nutricionist/addNutricionist
     * @param nutricionist object that represents the nutricionist to create
     * @return object that represents the created nutricionist
     */
    @PostMapping("/addNutricionist")
    public Nutricionist addNutricionist(Nutricionist nutricionist){
        
        Long createdNutricionist = nutricionistService.createNutricionist(nutricionist);
        if(createdNutricionist > 0){
            nutricionist.setId(createdNutricionist);
            return nutricionist;
        }
        return new Nutricionist();
    }
    
    /**
     * Method to modify a nutricionist  // HTTP verb: PUT url: http://localhost:8080/api/nutricionist/modifyNutricionist
     * @param nutricionist object that represents the nutricionist to modify
     * @return object that represents the modified nutricionist
     */
    @PutMapping("/modifyNutricionist")
    public Nutricionist updateNutricionist(Nutricionist nutricionist){
        nutricionistService.modifyNutricionist(nutricionist);
        return nutricionist;
    }
    
    /**
     * Method to delete a nutricionist by id  // HTTP verb: DELETE url: http://localhost:8080/api/nutricionist/deleteNutricionistById/{NutricionistId}
     * @param id long with the id of the nutricionist to delete
     * @return a string informing that the nutricionist id is deleted
     */
    @DeleteMapping("/deleteNutricionistById/{id}")
    public String deleteNutricionistById(@PathVariable String id){
        Nutricionist nutricionist = nutricionistService.findNutricionistById(Long.parseLong(id));
        if(nutricionist != null) {
            nutricionistService.deleteNutricionistById(Long.parseLong(id));
        return "Deleted nutricionist id - "+id;
        }
        return null;
    }
}
