/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;



import com.bienmesabe.rest.domain.Assessment;
import com.bienmesabe.rest.service.AssessmentService;
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
 * Controller for Assessments // url: http://localhost:8080/api/assessments
 * @author RAUL RAMOS CENDRERO
 * @version 20/04/2021
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/assessment")
public class AssessmentController {
    
    /**
     * Bean of the assessment service (Interface)
     */
    @Autowired
    private AssessmentService assessmentService;
    
    /**
     * Method to recover all the assessments // HTTP verb: GET url: http://localhost:8080/api/assessment/getAssessments
     * @return a list with all recipe assessments
     */
    @GetMapping("/getAssessments")
    public List<Assessment> getAssessments(){
        return assessmentService.findAllAssessments();
    }
    
    /**
     * Method to recover all the assessments of a recipe // HTTP verb: GET url: http://localhost:8080/api/assessment/getAssessmentsByRecipeId/{id}
     * @param id string that represents the id of the recipe
     * @return a list with all of the assessments of the recipe
     */
    @GetMapping("/getAssessmentsByRecipeId/{id}")
    public List<Assessment> getAssessmentsById(@PathVariable String id){
        return assessmentService.findAllAssessmentsOfRecipe(Long.parseLong(id));
    }
    
    /**
     * Method to create the assessment // HTTP verb: POST url: http://localhost:8080/api/assessment/addAssessment/{info}
     * @param info string with all the information of the assessment to persist
     * @return the created assessment
     */
    @PostMapping("/addAssessment/{info}")
    public Assessment addAssessment(@PathVariable String info){
       Assessment assessment = new Assessment();
       assessment.setRecipeId(Long.parseLong(info.split("___")[0]));
       assessment.setAssessmentValue(Integer.parseInt(info.split("___")[1]));
       assessment.setUserId(Long.parseLong(info.split("___")[2]));
       Long id = assessmentService.createAssessment(assessment);
       if(id == 0L)
           return null;
       assessment.setId(id);
       return assessment;
    }
    
    /**
     * Method to update the assessment // HTTP verb: PUT url: http://localhost:8080/api/assessment/modifyAssessment/{info}
     * @param info string with all the information of the assessment to persist
     * @return the created assessment
     */
    @PutMapping("/modifyAssessment/{info}")
    public boolean modifyAssessment(@PathVariable String info){
        return assessmentService.modifyAssessment(info);
    }
    
    /**
     * Method to delete the assessments of a recipe // HTTP verb: DELETE url: http://localhost:8080/api/assessment/deleteAssessments/{recipeId}
     * @param recipeId string that represents the id of the recipe
     * @return a boolean that indicates if the assessments has been deleted or not 
     */
    @DeleteMapping("/deleteAssessments/{recipeId}")
    public boolean deleteAssessments(@PathVariable String recipeId){
        return assessmentService.deleteAssessments(Long.parseLong(recipeId));
    }
}
