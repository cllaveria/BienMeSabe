/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;

import com.bienmesabe.rest.domain.Nutricionist;
import com.bienmesabe.rest.domain.NutricionistDegree;
import com.bienmesabe.rest.service.NutricionistDegreeService;
import com.bienmesabe.rest.service.NutricionistService;
import java.util.List;
import javax.ws.rs.Consumes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * Bean of the nutricionist degree service (Interface)
     */
    @Autowired
    private NutricionistDegreeService nutricionistDegreeService;
    
    /**
     * Method to recover the nutricionists  // HTTP verb: GET url: http://localhost:8080/api/nutricionist/getNutricionists
     * @return a list with the nutricionists
     */
    @GetMapping("/getNutricionists")
    public List<Nutricionist> getNutricionists(){
        return nutricionistService.findAllNutricionist();
    }
    
    /**
     * Method to recover the nutricionist by id  // HTTP verb: GET url: http://localhost:8080/api/nutricionist/getNutricionistById/{id}
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
    public List<Nutricionist> findNutricionistByCP(@PathVariable String cp){
        List<Nutricionist> nutricionists = nutricionistService.findNutricionistByCP(cp);
        return nutricionists;
    }
    
    /**
     * Method to recover the nutricionists by postal code range  // HTTP verb: GET url: http://localhost:8080/api/nutricionist/findNutricionistByCPRange/{PostalCodeRanges}
     * @param cpRanges string that represents the mimimum and maximum postal code to search
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
     * Method to recover the nutricionists assessment  // HTTP verb: GET url: http://localhost:8080/api/nutricionist/getNutricionistAssessment/{id}
     * @param id string that represents the id of the nutricionist to search
     * @return the assessment of the nutricionist
     */
    @GetMapping("/getNutricionistAssessment/{id}")
    public int getNutricionistAssessment(@PathVariable String id){
        return nutricionistService.getNutricionistAssessment(id);
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
     * Method to create a nutricionist degree // HTTP verb: POST url: http://localhost:8080/api/nutricionist/addNutricionistDegree
     * @param degree object that represents the nutricionist degree to create
     * @return boolean that indicates if the degree has been successfully inserted into DB
     */
    @PostMapping("/addNutricionistDegree")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public boolean addNutricionist(@RequestBody NutricionistDegree degree){
        return nutricionistDegreeService.insertNutricionistDegree(degree);
    }
    
    /**
     * Method to modify a nutricionist  // HTTP verb: PUT url: http://localhost:8080/api/nutricionist/modifyNutricionist
     * @param nutricionist string that represents the nutricionist to modify
     * @return object that represents the modified nutricionist
     */
    @PutMapping("/modifyNutricionist/{nutricionist}")
    public Boolean updateNutricionist(@PathVariable String nutricionist){
        try{
            nutricionistService.modifyNutricionist(nutricionist);
            return true;
        }catch(Exception e){
            return false;
        }
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
            boolean res = false;
            res = nutricionistService.deleteNutricionistAdminContactsMade(Long.parseLong(id));
            if(res) res = nutricionistService.deleteNutricionistDegrees(Long.parseLong(id));
            if(res) res = nutricionistService.deleteNutricionistAssessments(Long.parseLong(id));
            if(res) res = nutricionistService.deleteNutricionistComments(Long.parseLong(id));
            if(res) res = nutricionistService.deleteNutricionistAssessmentsMade(Long.parseLong(id));
            if(res) res = nutricionistService.deleteNutricionistCommentsMade(Long.parseLong(id));      
            if(res) nutricionistService.deleteNutricionistById(Long.parseLong(id));
            if(res) return "Deleted nutricionist id - "+id;
        }
        return null;
    }
}
