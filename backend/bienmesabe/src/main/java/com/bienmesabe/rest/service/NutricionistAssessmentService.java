/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.NutricionistAssessment;

/**
 * Inteface with the service methods to modify the nutricionist assessments
 * @author RAUL
 * @version 23/05/2021
 */
public interface NutricionistAssessmentService {
    
    /**
     * Method to create an nutricionist assessment in the table nutricionist assessments of the DB
     * @param assessment object that represents the nutricionist assessment to persist
     * @return a boolean that indicates if the nutricionist assessment is successfully inserted or not
     */
    public boolean createNutricionistAssessment(NutricionistAssessment assessment);
    
    /**
     * Method to update a nutricionist assessment in the table nutricionist assessments of the DB
     * @param assessment object that represents the nutricionist assessment to modify
     * @return a boolean that indicates if the nutricionist assessment is successfully updated or not
     */
    public boolean modifyAssessment(NutricionistAssessment assessment);
    
    /**
     * Method to update the assessment value of the nutricionist
     * @param nutricionistId long that represents the id of the nutricionist to update
     * @return a boolean that indicates if the assessment has been updated or not
     */
    public boolean updateNutricionistAssessment(long nutricionistId);
}
