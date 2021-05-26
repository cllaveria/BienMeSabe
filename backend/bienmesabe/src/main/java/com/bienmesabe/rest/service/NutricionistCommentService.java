/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.NutricionistComment;
import java.util.List;

/**
 * Inteface with the service methods to retrive the nutricionist comments
 * @author RAUL
 * @version 24/05/2021
 */
public interface NutricionistCommentService {
    
    /**
     * Method to recover the comments
     * @return a list with the comments
     */
    public List<NutricionistComment> findAllComments();
    
    /**
     * Method to recover all the comments of the nutricionist
     * @param nutricionistId long that represents the id of the nutricionist
     * @return a list with the comments of the nutricionist
     */
    public List<NutricionistComment> findAllCommentsOfNutricionist(Long nutricionistId);
    
    /**
     * Method to create a nutricionist comment in the table nutricionist comments of the DB
     * @param comment object that represents the nutricionist comment to persist
     * @return a boolean taht indicates if the nutricionist comment has been successfully inserted or not 
     */
    public boolean createComment(NutricionistComment comment);
}
