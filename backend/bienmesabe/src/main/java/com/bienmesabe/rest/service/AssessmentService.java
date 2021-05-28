/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.Assessment;
import java.util.List;

/**
 * Inteface with the service methods to retrive the assessments
 * @author RAUL RAMOS CENDRERO
 * @version 20/04/2021
 */
public interface AssessmentService {
    
    /**
     * Method to recover the assessments
     * @return a list with the assessments
     */
    public List<Assessment> findAllAssessments();
    
    /**
     * Method to recover the comments of the recipe
     * @param recipeId long that represents the id of the recipe
     * @return a list with the comments of the recipe
     */
    public List<Assessment> findAllAssessmentsOfRecipe(Long recipeId);
    
    /**
     * Method to create an assessment in the table assessments of the DB
     * @param assessment object that represents the assessment to persist
     * @return a long with the id of the persisted assessment
     */
    public Long createAssessment(Assessment assessment);
    
    /**
     * Method to modify an assessment
     * @param data string that represents the attributes of the assessment
     * @return a boolean that represents if the assessment has been successfully updated or not
     */
    public Boolean modifyAssessment(String data);
    
    /**
     * Method to delete the assessments of a recipe
     * @param recipeId long that represents the id of the recipe
     * @return a boolean that indicates if the assessments are successfully deleted or not
     */
    public boolean deleteAssessments(long recipeId);
}
