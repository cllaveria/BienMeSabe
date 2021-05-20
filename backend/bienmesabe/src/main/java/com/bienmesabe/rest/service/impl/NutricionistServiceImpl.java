/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.domain.Nutricionist;
import com.bienmesabe.rest.service.NutricionistService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.bienmesabe.rest.DAO.NutricionistDAO;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface NutricionistService (service)
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Service
public class NutricionistServiceImpl implements NutricionistService {

    /**
     * Bean of the nutricionist repository (Interface)
     */
    @Autowired
    private NutricionistDAO nutricionistDAO;
    
    /**
     * Implementation of interface method to recover the nutricionists present in the DB
     * @return a list with the nutricionists present in the DB
     */
    @Override
    public List<Nutricionist> findAllNutricionist() {
        List<Nutricionist> listNutricionists = nutricionistDAO.findAllNutricionist();
        return listNutricionists;
    }

    /**
     * Implementation of interface method to recover the nutricionists present in the DB by id
     * @param id long that represents the id of the nutricionist to search
     * @return the ingredient in the DB filtered by id
     */
    @Override
    public Nutricionist findNutricionistById(Long id) {
        Nutricionist nutricionist = nutricionistDAO.findNutricionistById(id);
        return nutricionist;
    }

    /**
     * Implementation of interface method to recover the nutricionists present in the DB by id
     * @param cp string that represents the postal code of the nutricionists to search
     * @return the ingredient in the DB filtered by postal code
     */
    @Override
    public Nutricionist findNutricionistByCP(String cp) {
        Nutricionist nutricionist = nutricionistDAO.findNutricionistByCP(cp);
        return nutricionist;
    }

    /**
     * Implementation of interface method to recover the nutricionist present in the DB by postal code range
     * @param cpMin string that represents the mimimum postal code of the nutricionists to search
     * @param cpMax string that represents the maximum postal code of the nutricionists to search
     * @return a list with the nutricionists
     */
    @Override
    public List<Nutricionist> findNutricionistByCPRange(String cpMin, String cpMax) {
        List<Nutricionist> listNutricionists = nutricionistDAO.findNutricionistByCPRange(cpMin, cpMax);
        return listNutricionists;
    }

    /**
     * Implementation of interface method to create an nutricionist in the table nutricionists of the DB
     * @param nutricionist object that represents the nutricionist to persist
     * @return a long with the id of the persisted nutricionist
     */
    @Override
    public Long createNutricionist(Nutricionist nutricionist) {
        Long id = nutricionistDAO.createNutricionist(nutricionist);
        return id;
    }

    // TODO
    // split string i crear nuevo nutricionista
     /**
     * Implementation of interface method to modify an nutricionist in the table nutricionists of the DB
     * @param nutricionist object that represents the nutricionist to modify
     */
    @Override
    public void modifyNutricionist(String nutricionist) {
        Long nutricionistId = 0L;
        String companyName = "", companyDirection ="", companyPostalCode ="" , companyCity = "", companyPhone = "";
        String[] splittedNutricionist = nutricionist.split("___");
        String[] splittedNutricionistIds = splittedNutricionist[0].split("---");
        nutricionistId = Long.parseLong(splittedNutricionistIds[1]);
        for (int i = 1; i<splittedNutricionist.length;i++){
            String[] spplitedValues = splittedNutricionist[i].split("---");
            String key = spplitedValues[0];
            String values = spplitedValues[1];
            
            if(key.equals("name")){
                companyName = values;
            }
            if(key.equals("direction")){
               companyDirection = values;
            }
            if(key.equals("postalCode")){
                companyPostalCode = values;
            }
            if(key.equals("city")){
                companyCity = values;
            }
            if(key.equals("phone")){
                companyPhone = values;
            }
        }
        nutricionistDAO.modifyNutricionist(nutricionistId, companyName, companyDirection, companyPostalCode, companyCity, companyPhone);
    }

    /**
     * Implementation of interface method to delete an nutricionist in the table nutricionists of the DB by id
     * @param id long with the id of the nutricionist to delete
     */
    @Override
    public void deleteNutricionistById(Long id) {
        nutricionistDAO.deleteNutricionistById(id);
    }
    
}
