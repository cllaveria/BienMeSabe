/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.Assessment;
import java.util.List;

/**
 * Inteface with the methods to retrive the DB data of assessments
 * @author RAUL RAMOS CENDRERO
 * @version 20/04/2021
 */
public interface AssessmentDAO {
    
    /**
     * Method to recover the assessments
     * @return a list with the assessments
     */
    public List<Assessment> findAllAssessments();
    
    /**
     * Method to recover the assessments of the recipe
     * @param recipeId long that represents the id of the recipe
     * @return a list with the assessments of the recipe
     */
    public List<Assessment> findAllAssessmentsOfRecipe(Long recipeId);
    
    /**
     * Method to create an assessment in the table assessments of the DB
     * @param assessment object that represents the assessment to persist
     * @return a long with the id of the persisted assessment
     */
    public Long createAssessment(Assessment assessment);
    
    /**
     * Method to update an assessment in the table assessments of the DB
     * @param assessment object that represents the assessment to modify
     * @return a boolean that indicates if the assessment is successfully updated or not
     */
    public boolean modifyAssessment(Assessment assessment);
    
    /**
     * Method to delete the assessments of a recipe in the table assessments of the DB
     * @param recipeId long that represents the id of the recipe
     * @return a boolean that indicates if the assessments are successfully deleted or not
     */
    public boolean deleteAssessments(long recipeId);
}
