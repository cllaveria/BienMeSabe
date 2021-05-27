/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.NutricionistAssessmentDAO;
import com.bienmesabe.rest.domain.Assessment;
import com.bienmesabe.rest.domain.Nutricionist;
import com.bienmesabe.rest.domain.NutricionistAssessment;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
    
    public List<NutricionistAssessment> findAllNutricionistAssessmentsOfNutricionist(long nutricionistId){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NutricionistAssessment> query = currentSession.createQuery("FROM NutricionistAssessment WHERE nutricionistId=:nutricionist", NutricionistAssessment.class);
        query.setParameter("nutricionist", nutricionistId);
        List<NutricionistAssessment> assessments = query.getResultList();
        return assessments;
    }
    
    /**
     * Implementation of interface method to create an nutricionist assessment in the table nutricionist assessments of the DB
     * @param assessment object that represents the nutricionist assessment to persist
     * @return a boolean that indicates if the nutricionist assessment is successfully inserted or not
     */
    @Override
    @Transactional
    public boolean createNutricionistAssessment(NutricionistAssessment assessment){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NutricionistAssessment> query = currentSession.createQuery("FROM NutricionistAssessment WHERE nutricionistId=:nutricionist and userId=:user", NutricionistAssessment.class);
        query.setParameter("nutricionist", assessment.getNutricionistId());
        query.setParameter("user", assessment.getUserId());
        NutricionistAssessment assessmentInDB = new NutricionistAssessment();
        try{
             assessmentInDB= query.getSingleResult();
        }catch(Exception e){
            assessmentInDB = null;
        }
        if(assessmentInDB==null){
            try{
                currentSession.save(assessment); 
                
                return true;
            }catch(Exception ee){
                return false;
            }
            
        }
        return false;
    }
    
    /**
     * Method to retrieve all the assessments of the recipe
     * @param assessments lits of the all assessments of the recipe
     * @return an integer that represents the medium value of the recipe assessments
     */
    @Transactional
    public int getNutricionistAssessment(List<NutricionistAssessment> assessments){
        float res = 0F;
        if(assessments.size() > 0){
            for(NutricionistAssessment assessment : assessments){
                res+= assessment.getAssessmentValue();
            }
            res = res / assessments.size();
            return Math.round(res);
        }else{
            return 0;
        }
    }
    
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
    /**
     * Method to modify the assessment value of the nutricionist
     * @param nutricionistId long that represents the id of the nutricionist to update
     * @param assessmentValue integer that represents the value of the assessment to update
     */
    @Transactional
    public boolean updateRecipeAssessmentValue(long nutricionistId, int assessmentValue){
        
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NutricionistAssessment> query = currentSession.createQuery("Update Nutricionist set nutricionistAssessment=:assessment WHERE id=:nutricionist");
        query.setParameter("nutricionist", nutricionistId);
        query.setParameter("assessment", assessmentValue);
        try{
            query.executeUpdate();
            return true;
        }catch(Exception ee){
            return false;
        }
        
    }
    
    
    /**
     * Implementation of interface method to update the assessment value of the nutricionist
     * @param nutricionistId long that represents the id of the nutricionist to update
     * @return a boolean that indicates if the assessment has been updated or not
     */
    @Override
    @Transactional
    public boolean updateNutricionistAssessment(long nutricionistId){
        List<NutricionistAssessment> assessmentList = findAllNutricionistAssessmentsOfNutricionist(nutricionistId);
        int av = getNutricionistAssessment(assessmentList);
        return updateRecipeAssessmentValue(nutricionistId, av);
    }
}
