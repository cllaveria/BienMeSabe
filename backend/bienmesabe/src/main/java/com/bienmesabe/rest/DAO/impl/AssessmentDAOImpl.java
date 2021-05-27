/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.AssessmentDAO;
import com.bienmesabe.rest.domain.Assessment;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface AssessmentDAO (repository)
 * @author RAUL RAMOS CENDRERO
 * @version 20/04/2021
 */
@Repository
public class AssessmentDAOImpl implements AssessmentDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    /**
     * Implementation of interface method to recover the assessments
     * @return a list with the assessments
     */
    @Override
    @Transactional
    public List<Assessment> findAllAssessments() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Assessment> query = currentSession.createQuery("FROM Assessment",Assessment.class);
        return query.getResultList();
    }
    
    /**
     * Implementation of interface method to recover the assessments of a recipe present in the DB
     * @param recipeId long that represents the id of the recipe
     * @return a list with the assessments of the indicated recipe
     */
    @Override
    @Transactional
    public List<Assessment> findAllAssessmentsOfRecipe(Long recipeId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Assessment> query = currentSession.createQuery("FROM Assessment WHERE recipeId=:id", Assessment.class);
        query.setParameter("id", recipeId);
        List<Assessment> assessment = query.getResultList();
        return assessment;
    }
    
    /**
     * Implementation of interface method to create an assessment in the table assessments of the DB
     * @param assessment object that represents the assessment to persist
     * @return a long with the id of the persisted assessment
     */
    @Override
    @Transactional
    public Long createAssessment(Assessment assessment) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Assessment> query = currentSession.createQuery("FROM Assessment WHERE recipeId=:recipe and userId=:user", Assessment.class);
        query.setParameter("recipe", assessment.getRecipeId());
        query.setParameter("user", assessment.getUserId());
        Assessment assessmentInDB = new Assessment();
        try{
             assessmentInDB= query.getSingleResult();
        }catch(Exception e){
            assessmentInDB = null;
        }
        if(assessmentInDB==null){
            try{
                Long idGenerado = (Long) currentSession.save(assessment); 
                List<Assessment> assessmentList = findAllAssessmentsOfRecipe(assessment.getRecipeId());
                int av = getRecipeAssessment(assessmentList);
                updateRecipeAssessmentValue(assessment.getRecipeId(), av);
                return idGenerado;
            }catch(Exception ee){
                return null;
            }
            
        }
        return 0L;
    }
    
    /**
     * Method to retrieve all the assessments of the recipe
     * @param assessments lits of the all assessments of the recipe
     * @return an integer that represents the medium value of the recipe assessments
     */
    @Transactional
    public int getRecipeAssessment(List<Assessment> assessments){
        float res = 0F;
        if(assessments.size() > 0){
            for(Assessment assessment : assessments){
                res+= assessment.getAssessmentValue();
            }
            res = res / assessments.size();
            return Math.round(res);
        }else{
            return 0;
        }
    }
    
    /**
     * Method to modify an assessment value in the table assessments of the DB
     * @param recipeId long that represents the id of the recipe to update
     * @param assessmentValue integer that represents the value of the assessment to update
     */
    @Transactional
    public void updateRecipeAssessmentValue(long recipeId, int assessmentValue){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Assessment> query = currentSession.createQuery("Update Recipe set recipeAssessment=:assessment WHERE id=:recipeId");
        query.setParameter("recipeId", recipeId);
        query.setParameter("assessment", assessmentValue);
        query.executeUpdate();
//        List<Assessment> assessmentList = findAllAssessmentsOfRecipe(recipeId);
//        int av = getRecipeAssessment(assessmentList);
//        updateRecipeAssessmentValue(recipeId, av);
    }
    
    /**
     * Implementation of interface method to modify an assessment in the table assessments of the DB
     * @param assessment object that represents the assessment to modify
     * @return a boolean that indicates if the assessment has been updated successfully or not
     */
    @Override
    @Transactional
    public boolean modifyAssessment(Assessment assessment){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Assessment> query = currentSession.createQuery("update Assessment set assessmentValue=:newValue WHERE recipeId=:recipe and userId=:user");
        query.setParameter("recipe", assessment.getRecipeId());
        query.setParameter("user", assessment.getUserId());
        query.setParameter("newValue", assessment.getAssessmentValue());
        try{
            query.executeUpdate();
            List<Assessment> assessmentList = findAllAssessmentsOfRecipe(assessment.getRecipeId());
            int av = getRecipeAssessment(assessmentList);
            updateRecipeAssessmentValue(assessment.getRecipeId(), av);
            return true;
        }catch(Exception ee){
            return false;
        }
    }

    /**
     * Implementation of interface method to delete the assessments of a recipe in the table assessments of the DB
     * @param recipeId long that represents the id of the recipe
     * @return a boolean that indicates if the assessments are successfully deleted or not
     */
    @Override
    @Transactional
    public boolean deleteAssessments(long recipeId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Assessment> query = currentSession.createQuery("DELETE FROM Assessment WHERE recipeId=:recipe");
        query.setParameter("recipe", recipeId);
        try{
            query.executeUpdate();
            return true;
        }catch(Exception ee){
            return false;
        }
    }
}
