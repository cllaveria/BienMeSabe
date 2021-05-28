/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.AssessmentDAO;
import com.bienmesabe.rest.domain.Assessment;
import com.bienmesabe.rest.service.AssessmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface IngredientService (service)
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Service
public class AssessmentServiceImpl implements AssessmentService{

    /**
     * Bean of the assessment repository (Interface)
     */
    @Autowired
    private AssessmentDAO assessmentDAO;
    
    /**
     * Implementation of interface method to recover the assessments present in the DB
     * @return a list with the assessments in the DB
     */
    @Override
    public List<Assessment> findAllAssessments() {
        return assessmentDAO.findAllAssessments();
    }
    
    /**
     * Implementation of interface method to recover the recipe assessments
     * @param recipeId long that represents the id of the recipe
     * @return a list of the recipe assessments
     */
    @Override
    public List<Assessment> findAllAssessmentsOfRecipe(Long recipeId) {
        return assessmentDAO.findAllAssessmentsOfRecipe(recipeId);
    }
    
    /**
     * Implementation of interface method to create an assessment
     * @param assessment object that represents the assessment to persist
     * @return a long with the id of the persisted assessment
     */
    @Override
    public Long createAssessment(Assessment assessment) {
        return assessmentDAO.createAssessment(assessment);
    }
    
    /**
     * Implementation of interface method to modify an assessment
     * @param data string that represents the attributes of the assessment
     * @return a boolean that represents if the assessment has been successfully updated or not
     */
    @Override
    public Boolean modifyAssessment(String data){
        Assessment newAssessment = new Assessment();
        String[] splittedData = data.split("___");
        for (int i = 0; i<splittedData.length;i++){
            String[] spplitedValues = splittedData[i].split("---");
            String key = spplitedValues[0];
            String values =  spplitedValues[1];
            if(key.equals("recipe") && values != ""){
                newAssessment.setRecipeId(Long.parseLong(values));
            }else if(key.equals("user") && values != ""){
               newAssessment.setUserId(Long.parseLong(values));
            }else if(key.equals("value") && values != ""){
                newAssessment.setAssessmentValue(Integer.parseInt(values));
            }
        }
        
        return assessmentDAO.modifyAssessment(newAssessment);
        
    }

    /**
     * Implementation of interface method to delete the assessments of a recipe
     * @param recipeId long that represents the id of the recipe
     * @return a boolean that indicates if the assessments are successfully deleted or not
     */
    @Override
    public boolean deleteAssessments(long recipeId) {
        return assessmentDAO.deleteAssessments(recipeId);
    }
    
    
}
