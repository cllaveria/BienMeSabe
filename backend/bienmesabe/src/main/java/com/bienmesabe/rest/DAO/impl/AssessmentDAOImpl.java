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

/**
 * Class for implementation of Inteface CommentDAO (repository)
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
     * Method to recover the comments
     * @return a list with the comments
     */
    @Override
    public List<Assessment> findAllAssessments() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Assessment> query = currentSession.createQuery("FROM Assessment",Assessment.class);
        return query.getResultList();
    }
    
    /**
     * Method to recover the comments of the recipe present in the DB by name
     * @param name string that represents the id of the recipe to search
     * @return the user in the DB filtered by name
     */
    @Override
    public List<Assessment> findAllAssessmentsOfRecipe(Long recipeId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Assessment> query = currentSession.createQuery("FROM Assessment WHERE recipeId=:id", Assessment.class);
        query.setParameter("id", recipeId);
        List<Assessment> assessment = query.getResultList();
        return assessment;
    }
    /**
     * Implementation of interface method to create a assessment in the table assessments of the DB
     * @param assessment object that represents the assessment to persist
     * @return a long with the id of the persisted assessment
     */
    @Override
    public Long createAssessment(Assessment assessment) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Assessment> query = currentSession.createQuery("FROM Assessment WHERE assessmentValue=:value and recipeId=:recipe and userId=:user", Assessment.class);
        query.setParameter("value", assessment.getAssessmentValue());
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
                return idGenerado;
            }catch(Exception ee){
                return null;
            }
            
        }
        return 0L;
    }
    
}
