/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.NutricionistDegreeDAO;
import com.bienmesabe.rest.domain.NutricionistDegree;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface NutricionistDegreeDAO (repository)
 * @author RAUL
 * @version 19/05/2021
 */
@Repository
public class NutricionistDegreeDAOImpl implements NutricionistDegreeDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    /**
     * Implementation of interface method to recover the degrees of the nutricionist present in the DB
     * @param nutricionistId long that represents the id of the nutricionist
     * @return a list with the degrees of the nutricionist present in the DB
     */
    @Override
    @Transactional
    public List<NutricionistDegree> findAllNutricionistDegrees(Long nutricionistId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NutricionistDegree> query = currentSession.createQuery("FROM NutricionistDegree",NutricionistDegree.class);
        return query.getResultList();
    }

    /**
     * Implementation of interface method to create the nutricionist degrees
     * @param degree object that represents the degree of the nutricionist to persist
     * @return a boolean that indicates if the nutricionist degree has been successfully inserted into DB
     */
    @Override
    @Transactional
    public boolean insertNutricionistDegree(NutricionistDegree degree) {
        Session currentSession = entityManager.unwrap(Session.class);
        try{
            currentSession.save(degree);
            return true;
        }catch(Exception ee){
            return false;
        }
    }
    
    
    
}
