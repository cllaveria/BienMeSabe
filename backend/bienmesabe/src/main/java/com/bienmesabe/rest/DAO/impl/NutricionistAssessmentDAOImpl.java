/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.NutricionistAssessmentDAO;
import com.bienmesabe.rest.domain.NutricionistAssessment;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface NutricionistAssessmentDAO (repository)
 * @author RAUL
 * @version 23/05/2021
 */
@Repository
public class NutricionistAssessmentDAOImpl implements NutricionistAssessmentDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    /**
     * Implementation of interface method to update a nutricionist assessment in the table nutricionist assessments of the DB
     * @param assessment object that represents the nutricionist assessment to modify
     * @return a boolean that indicates if the nutricionist assessment is successfully updated or not
     */
    @Override
    @Transactional
    public boolean modifyAssessment(NutricionistAssessment assessment) {
        Session currentSession = entityManager.unwrap(Session.class);
        try{
            currentSession.saveOrUpdate(assessment);
            return true;
        }catch(Exception ee){
            return false;
        }
    }
    
}
