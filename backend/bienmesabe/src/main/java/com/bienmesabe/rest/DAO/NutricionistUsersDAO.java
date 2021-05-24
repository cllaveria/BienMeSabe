/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.NutricionistUsers;
import java.util.List;

/**
 * Inteface with the methods to asign or remove a user to the nutricionist the DB data of comments
 * @author RAUL
 * @version: 24/05/2021
 */
public interface NutricionistUsersDAO {
    
    /**
     * Method to asign the user to the nutricionist in the table nutricionistusers of the DB
     * @param userAssignment object that represents the assessment to persist
     * @return boolean that represents if the user has been successfully asigned to the nutricionist or not
     */
    public boolean createUserAssignmentToNutricionist(NutricionistUsers userAssignment);
    
    /**
     * Method to remove the user to the nutricionist in the table nutricionistusers of the DB
     * @param userAssignment object that represents the assessment to persist
     * @return boolean that represents if the user has been successfully asigned to the nutricionist or not
     */
    public boolean removeUserAssignmentToNutricionist(NutricionistUsers userAssignment);
    
    /**
     * Method to recover the asignment of the user
     * @param userId long that represents the id of the user
     * @return an object that represents the asignment
     */
    public NutricionistUsers findUserAssignment(Long userId);
    
    /**
     * Method to recover the asignment of the user
     * @param nutricionistId long that represents the id of the user
     * @return an object that represents the asignment
     */
    public List<NutricionistUsers> findNutricionistAssignments(Long nutricionistId);
}
