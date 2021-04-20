/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.CommentDAO;
import com.bienmesabe.rest.domain.Comment;
import com.bienmesabe.rest.domain.Ingredient;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Class for implementation of Inteface CommentDAO (repository)
 * @author RAUL RAMOS CENDRERO
 * @version 20/04/2021
 */
@Repository
public class CommentDAOImpl implements CommentDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Long createIngredient(Comment comment) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Comment> query = currentSession.createQuery("FROM Comment WHERE commentValue=:value and recipeId=:recipe and userId=:user", Comment.class);
        query.setParameter("value", comment.getCommentValue());
        query.setParameter("recipe", comment.getRecipeId());
        query.setParameter("user", comment.getUserId());
        Comment commentInDB = new Comment();
        try{
             commentInDB= query.getSingleResult();
        }catch(Exception e){
            commentInDB = null;
        }
        if(commentInDB==null){
            Long idGenerado = (Long) currentSession.save(comment); 
        return idGenerado;
        }
        return 0L;
    }
    
}
