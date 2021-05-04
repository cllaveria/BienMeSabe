/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;



import com.bienmesabe.rest.domain.Assessment;
import com.bienmesabe.rest.domain.Comment;
import com.bienmesabe.rest.service.AssessmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
      @GetMapping("/getAssessments")
    public List<Assessment> getAssessments(){
        return assessmentService.findAllAssessments();
    }
    
    @GetMapping("/getAssessmentsByRecipeId/{id}")
    public List<Assessment> getAssessmentsById(@PathVariable String id){
        return assessmentService.findAllAssessmentsOfRecipe(Long.parseLong(id));
    }
    
    /**
     * Method to create the assessment // HTTP verb: POST url: http://localhost:8080/api/assessment/addAssessment/{recipeId}/{assessment}/{userId}
     * @param recipeId string that represents the id of the recipe
     * @param assessmentValue string that represents the assessment value
     * @param userId string that represents the id of the user
     * @return the created user
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
}
