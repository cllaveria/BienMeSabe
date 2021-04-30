/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.Assessment;

/**
 * Inteface with the methods to retrive the DB data of assessments
 * @author RAUL RAMOS CENDRERO
 * @version 20/04/2021
 */
public interface AssessmentDAO {
    /**
     * Method to create an assessment in the table assessments of the DB
     * @param assessment object that represents the assessment to persist
     * @return a long with the id of the persisted assessment
     */
    public Long createAssessment(Assessment assessment);
}
