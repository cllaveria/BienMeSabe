/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.NutricionistDegree;
import java.util.List;

/**
 * Inteface with the methods to retrive the DB data of nutricionist degrees
 * @author RAUL RAMOS CENDRERO
 * @version 19/05/2021
 */
public interface NutricionistDegreeDAO {
    
    /**
     * Method to recover the nutricionist degrees
     * @param nutricionistId long that represents the id of the nutricionist
     * @return a list with the degrees
     */
    public List<NutricionistDegree> findAllNutricionistDegrees(Long nutricionistId);
    
    /**
     * Method to create the nutricionist degrees
     * @param degree object that represents the degree of the nutricionist to persist
     * @return a boolean that indicates if the nutricionist degree has been successfully inserted into DB
     */
    public boolean insertNutricionistDegree(NutricionistDegree degree);
}
