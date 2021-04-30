/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.AssessmentDAO;
import com.bienmesabe.rest.domain.Assessment;
import com.bienmesabe.rest.service.AssessmentService;
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
     * Implementation of interface method to create an assessment
     * @param assessment object that represents the assessment to persist
     * @return a long with the id of the persisted assessment
     */
    @Override
    public Long createAssessment(Assessment assessment) {
        return assessmentDAO.createAssessment(assessment);
    }
}
