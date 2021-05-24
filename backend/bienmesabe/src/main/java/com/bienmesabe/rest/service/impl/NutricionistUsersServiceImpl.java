/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.NutricionistUsersDAO;
import com.bienmesabe.rest.domain.NutricionistUsers;
import com.bienmesabe.rest.service.NutricionistUsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface NutricionistUsersService (service)
 * @author RAUL
 * @version: 24/05/2021
 */
@Service
public class NutricionistUsersServiceImpl implements NutricionistUsersService{
    
    /**
     * Bean of the nutricionist users repository (Interface)
     */
    @Autowired
    private NutricionistUsersDAO nutricionistUsersDAO;

    /**
     * Method to asign the user to the nutricionist in the table nutricionistusers of the DB
     * @param userAssignment object that represents the assessment to persist
     * @return boolean that represents if the user has been successfully asigned to the nutricionist or not
     */
    @Override
    public boolean createUserAssignmentToNutricionist(NutricionistUsers userAssignment) {
        return nutricionistUsersDAO.createUserAssignmentToNutricionist(userAssignment);
    }

    /**
     * Method to remove the user to the nutricionist in the table nutricionistusers of the DB
     * @param userAssignment object that represents the assessment to persist
     * @return boolean that represents if the user has been successfully asigned to the nutricionist or not
     */
    @Override
    public boolean removeUserAssignmentToNutricionist(NutricionistUsers userAssignment) {
        return nutricionistUsersDAO.removeUserAssignmentToNutricionist(userAssignment);
    }

    /**
     * Method to recover the asignment of the user
     * @param userId long that represents the id of the user
     * @return an object that represents the asignment
     */
    @Override
    public NutricionistUsers findUserAssignment(Long userId) {
        return nutricionistUsersDAO.findUserAssignment(userId);
    }

    /**
     * Method to recover the asignment of the user
     * @param nutricionistId long that represents the id of the user
     * @return an object that represents the asignment
     */
    @Override
    public List<NutricionistUsers> findNutricionistAssignments(Long nutricionistId) {
        return nutricionistUsersDAO.findNutricionistAssignments(nutricionistId);
    }
}
