/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.NutricionistDegreeDAO;
import com.bienmesabe.rest.domain.NutricionistDegree;
import com.bienmesabe.rest.service.NutricionistDegreeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface NutricionistDegreeService (service)
 * @author RAUL
 * @version 23/05/2021
 */
@Service
public class NutricionistDegreeServiceImpl implements NutricionistDegreeService{
    
    /**
     * Bean for the entity manager
     */
    @Autowired
    private NutricionistDegreeDAO nutricionistDegreeDAO;
    
    /**
     * Implementation of interface method to recover the degrees of the nutricionist present in the DB
     * @param nutricionistId long that represents the id of the nutricionist
     * @return a list with the degrees of the nutricionist present in the DB
     */
    @Override
    public List<NutricionistDegree> findAllNutricionistDegrees(Long nutricionistId) {
        return nutricionistDegreeDAO.findAllNutricionistDegrees(nutricionistId);
    }

    /**
     * Implementation of interface method to create the nutricionist degrees
     * @param degree object that represents the degree of the nutricionist to persist
     * @return a boolean that indicates if the nutricionist degree has been successfully inserted into DB
     */
    @Override
    public boolean insertNutricionistDegree(NutricionistDegree degree) {
        return nutricionistDegreeDAO.insertNutricionistDegree(degree);
    }
}
