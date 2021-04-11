/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.Nutricionist;
import java.util.List;

/**
 * Inteface with the methods to retrive the DB data of nutricionists
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
public interface NutricionistDAO {
    /**
     * Method to recover the nutricionists present in the DB
     * @return a list with the nutricionists present in the DB
     */
    public List<Nutricionist> findAllNutricionist();
    /**
     * Method to recover the nutricionists present in the DB by id
     * @param id long that represents the id of the nutricionist to search
     * @return the ingredient in the DB filtered by id
     */
    public Nutricionist findNutricionistById(Long id);
    
    /**
     * Method to recover the nutricionists present in the DB by id
     * @param cp string that represents the postal code of the nutricionists to search
     * @return the ingredient in the DB filtered by postal code
     */
    public Nutricionist findNutricionistByCP(String cp);
    
    /**
     * Method to recover the nutricionist present in the DB by postal code range
     * @param cpMin string that represents the mimimum postal code of the nutricionists to search
     * @param cpMax string that represents the maximum postal code of the nutricionists to search
     * @return a list with the nutricionists
     */
    public List<Nutricionist> findNutricionistByCPRange(String cpMin, String cpMax);
    
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
    public void modifyNutricionist(Nutricionist nutricionist);
    
    /**
     * Method to delete an nutricionist in the table nutricionists of the DB by id
     * @param id long with the id of the nutricionist to delete
     */
    public void deleteNutricionistById(Long id);
}
