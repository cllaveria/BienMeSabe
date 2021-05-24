/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.NutricionistUsersDAO;
import com.bienmesabe.rest.domain.NutricionistUsers;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface NutricionistUsersDAO (repository)
 * @author RAUL
 * @version 24/05/2021
 */
@Repository
public class NutricionistUsersDAOImpl implements NutricionistUsersDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    /**
     * Implementation of interface method to asign the user to the nutricionist in the table nutricionistusers of the DB
     * @param userAssignment object that represents the assessment to persist
     * @return boolean that represents if the user has been successfully asigned to the nutricionist or not
     */
    @Override
    @Transactional
    public boolean createUserAssignmentToNutricionist(NutricionistUsers userAssignment) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NutricionistUsers> query = currentSession.createQuery("FROM NutricionistUsers where userId=:user", NutricionistUsers.class);
        query.setParameter("user", userAssignment.getUserId());
        NutricionistUsers user = query.getSingleResult();
        if(user == null){
            currentSession.save(userAssignment);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Implementation of interface method to remove the user to the nutricionist in the table nutricionistusers of the DB
     * @param userAssignment object that represents the assessment to persist
     * @return boolean that represents if the user has been successfully asigned to the nutricionist or not
     */
    @Override
    @Transactional
    public boolean removeUserAssignmentToNutricionist(NutricionistUsers userAssignment) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NutricionistUsers> query = currentSession.createQuery("delete FROM NutricionistUsers where nutricionistId=:nutricionist and userId=:user");
        query.setParameter("nutricionist", userAssignment.getNutricionistId());
        query.setParameter("user", userAssignment.getUserId());
        try{
            query.executeUpdate();
            return true;
        }catch(Exception ee){
            return false;
        }
    }

    /**
     * Method to recover the asignment of the user
     * @param userId long that represents the id of the user
     * @return an object that represents the asignment
     */
    @Override
    @Transactional
    public NutricionistUsers findUserAssignment(Long userId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NutricionistUsers> query = currentSession.createQuery("FROM NutricionistUsers where userId=:user", NutricionistUsers.class);
        query.setParameter("user", userId);
        NutricionistUsers user = query.getSingleResult();
        return user;
    }
    
    /**
     * Method to recover the asignment of the user
     * @param nutricionistId long that represents the id of the user
     * @return an object that represents the asignment
     */
    @Override
    @Transactional
    public List<NutricionistUsers> findNutricionistAssignments(Long nutricionistId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NutricionistUsers> query = currentSession.createQuery("FROM NutricionistUsers where nutricionistId=:nutricionist", NutricionistUsers.class);
        query.setParameter("nutricionist", nutricionistId);
        List<NutricionistUsers> users = query.getResultList();
        return users;
    }
}
