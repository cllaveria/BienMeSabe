/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.Nutricionist;
import java.util.List;

/**
 * Inteface with the service methods to retrive the nutricionists
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
public interface NutricionistService {
    
    /**
     * Method to recover the nutricionists present in the DB
     * @return a list with the nutricionists present in the DB
     */
    public List<Nutricionist> findAllNutricionist();
    
    /**
     * Method to recover the nutricionists present in the DB by id
     * @param id long that represents the id of the nutricionist to search
     * @return the nutricionist in the DB filtered by id
     */
    public Nutricionist findNutricionistById(Long id);
    
    /**
     * Method to recover the nutricionists present in the DB by id
     * @param cp string that represents the postal code of the nutricionists to search
     * @return the list of the nutricionists in the DB filtered by postal code
     */
    public List<Nutricionist> findNutricionistByCP(String cp);
    
    /**
     * Method to recover the nutricionist present in the DB by postal code range
     * @param cpMin string that represents the mimimum postal code of the nutricionists to search
     * @param cpMax string that represents the maximum postal code of the nutricionists to search
     * @return a list with the nutricionists
     */
    public List<Nutricionist> findNutricionistByCPRange(String cpMin, String cpMax);
    
    /**
     * Method to recover the assessment of the nutricionist filtered by id
     * @param id long that represents the id of the nutricionist
     * @return an integer that represents the assessement of the nutricionist
     */
    public int getNutricionistAssessment(String id);
    
    /**
     * Method to create an nutricionist in the table nutricionists of the DB
     * @param nutricionist object that represents the nutricionist to persist
     * @return a long with the id of the persisted nutricionist
     */
    public Long createNutricionist(Nutricionist nutricionist);
    
    /**
     * Method to modify an nutricionist in the table nutricionists of the DB
     * @param nutricionist object that represents the nutricionist to modify
     */
    public void modifyNutricionist(String nutricionist);
    
    /**
     * Method to delete an nutricionist in the table nutricionists of the DB by id
     * @param id long with the id of the nutricionist to delete
     */
    public void deleteNutricionistById(Long id);
    
    /**
     * Method to delete the degrees of the nutricionist
     * @param id long with the id of the nutricionist to delete
     * @return a boolean that represents if the nutricionist degrees has been successfully updated or not
     */
    public boolean deleteNutricionistDegrees(Long id);
    
    /**
     * Method to delete the assessments of the nutricionist
     * @param id long with the id of the nutricionist to delete
     * @return a boolean that represents if the nutricionist assessments has been successfully updated or not
     */
    public boolean deleteNutricionistAssessments(Long id);
    
    /**
     * Method to delete the comments of the nutricionist
     * @param id long with the id of the nutricionist to delete
     * @return a boolean that represents if the nutricionist comments has been successfully updated or not
     */
    public boolean deleteNutricionistComments(Long id);
    
    /**
     * Method to delete the assessments made for the nutricionist
     * @param id long with the id of the nutricionist to delete
     * @return a boolean that represents if the assessments made for the nutricionist has been successfully updated or not
     */
    public boolean deleteNutricionistAssessmentsMade(Long id);
    
    /**
     * Method to delete the comments made for the nutricionist
     * @param id long with the id of the nutricionist to delete
     * @return a boolean that represents if the comments made for the nutricionist has been successfully updated or not
     */
    public boolean deleteNutricionistCommentsMade(Long id);
    
    /**
     * Method to delete the admin contacts made for the nutricionist
     * @param id long with the id of the nutricionist to delete
     * @return a boolean that represents if the comments made for the nutricionist has been successfully updated or not
     */
    public boolean deleteNutricionistAdminContactsMade(Long id);
    
    /**
     * Method to delete the users assigned to the nutricionist
     * @param id long with the id of the nutricionist to delete
     * @return a boolean that represents if the users assigned to the nutricionist has been successfully updated or not
     */
    public boolean deleteNutricionistUsers(Long id);
}
