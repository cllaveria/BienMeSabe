/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.NutricionistCommentDAO;
import com.bienmesabe.rest.domain.NutricionistComment;
import com.bienmesabe.rest.service.NutricionistCommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class for implementation of Inteface NutricionistCommentService (service)
 * @author RAUL
 * @version 24/05/2021
 */
public class NutricionistCommentServiceImpl implements NutricionistCommentService{
    
    /**
     * Bean of the rnutricionist comments repository (Interface)
     */
    @Autowired
    private NutricionistCommentDAO nutricionistCommentDAO;

    /**
     * Implementation of interface method to recover the comments
     * @return a list with the comments
     */
    @Override
    public List<NutricionistComment> findAllComments() {
        return nutricionistCommentDAO.findAllComments();
    }

    /**
     * Implementation of interface method to recover all the comments of the nutricionist
     * @param nutricionistId long that represents the id of the nutricionist
     * @return a list with the comments of the nutricionist
     */
    @Override
    public List<NutricionistComment> findAllCommentsOfNutricionist(Long nutricionistId) {
        return nutricionistCommentDAO.findAllCommentsOfNutricionist(nutricionistId);
    }

    /**
     * Implementation of interface method to create a comments of the nutricionist
     * @param comment object that represents the nutricionist comment
     * @return a boolean that indicates if the comment of the nutricionist has been successfully inserted into DB
     */
    @Override
    public boolean createComment(NutricionistComment comment) {
        return nutricionistCommentDAO.createComment(comment);
    }
    
    
}
