/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.NutricionistAssessmentDAO;
import com.bienmesabe.rest.domain.NutricionistAssessment;
import com.bienmesabe.rest.service.NutricionistAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface NutricionistAssessmentService (service)
 * @author RAUL
 * @version 23/05/2021
 */
@Service
public class NutricionistAssessmentServiceImpl implements NutricionistAssessmentService{

    /**
     * Bean of the rnutricionist assessment repository (Interface)
     */
    @Autowired
    private NutricionistAssessmentDAO nutricionistAssessmentDAO;
    
    /**
     * Implementation of interface method to update a nutricionist assessment in the table nutricionist assessments of the DB
     * @param assessment object that represents the nutricionist assessment to modify
     * @return a boolean that indicates if the nutricionist assessment is successfully updated or not
     */
    @Override
    public boolean modifyAssessment(NutricionistAssessment assessment) {
        return nutricionistAssessmentDAO.modifyAssessment(assessment);
    }
    
    /**
     * Implementation of interface method to create an nutricionist assessment
     * @param assessment object that represents the nutricionist assessment to persist
     * @return a boolean that indicates if the nutricionist assessment is successfully inserted or not
     */
    @Override
    public boolean createNutricionistAssessment(NutricionistAssessment assessment) {
        return nutricionistAssessmentDAO.createNutricionistAssessment(assessment);
    }
    
    /**
     * Method to update the assessment value of the nutricionist
     * @param nutricionistId long that represents the id of the nutricionist to update
     * @return a boolean that indicates if the assessment has been updated or not
     */
    @Override
    public boolean updateNutricionistAssessment(long nutricionistId){
        return nutricionistAssessmentDAO.updateNutricionistAssessment(nutricionistId);
    }
}
