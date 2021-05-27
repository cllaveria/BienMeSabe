/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.CommentDAO;
import com.bienmesabe.rest.domain.Comment;
import com.bienmesabe.rest.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface IngredientService (service)
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Service
public class CommentServiceImpl implements CommentService{

    /**
     * Bean of the comment repository (Interface)
     */
    @Autowired
    private CommentDAO commentDAO;
    
    /**
     * Implementation of interface method to recover the comments present in the DB
     * @return a list with the comments in the DB
     */
    @Override
    public List<Comment> findAllComments() {
        return commentDAO.findAllComments();
    }
    
    /**
     * Implementation of interface method to recover the recipe comments
     * @param recipeId long that represents the id of the recipe
     * @return a list of the recipe comments
     */
    @Override
    public List<Comment> findAllCommentsOfRecipe(Long recipeId) {
        return commentDAO.findAllCommentsOfRecipe(recipeId);
    }

    
    /**
     * Implementation of interface method to create a comment
     * @param comment object that represents the comment to persist
     * @return a long with the id of the persisted comment
     */
    @Override
    public Long createComment(Comment comment) {
        return commentDAO.createComment(comment);
    }

    /**
     * Implementation of interface method to delete the comments of a recipe
     * @param recipeId long that represents the id of the recipe
     * @return a boolean that indicates if the comments are successfully deleted or not
     */
    @Override
    public boolean deleteComments(long recipeId) {
        return commentDAO.deleteComments(recipeId);
    }

    
    
}
