/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.CommentDAO;
import com.bienmesabe.rest.domain.Comment;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    
    /**
     * Implementation of interface method to recover the comments
     * @return a list with the comments
     */
    @Override
    @Transactional
    public List<Comment> findAllComments() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Comment> query = currentSession.createQuery("FROM Comment",Comment.class);
        return query.getResultList();
    }
    
    /**
     * Implementation of interface method to recover the comments of the recipe present in the DB by name
     * @param recipeId long that represents the id of the recipe to search
     * @return the user in the DB filtered by name
     */
    @Override
    @Transactional
    public List<Comment> findAllCommentsOfRecipe(Long recipeId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Comment> query = currentSession.createQuery("FROM Comment WHERE recipeId=:id", Comment.class);
        query.setParameter("id", recipeId);
        List<Comment> comments = query.getResultList();
        return comments;

    }
    
    /**
     * Implementation of interface method to create a comment in the table comments of the DB
     * @param comment object that represents the comment to persist
     * @return a long with the id of the persisted comment
     */
    @Override
    @Transactional
    public Long createComment(Comment comment) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Comment> query = currentSession.createQuery("FROM Comment WHERE recipeId=:recipe and userId=:user", Comment.class);
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

    /**
     * Implementation of interface method to delete the comments of a recipe in the table assessments of the DB
     * @param recipeId long that represents the id of the recipe
     * @return a boolean that indicates if the comments are successfully deleted or not
     */
    @Override
    @Transactional
    public boolean deleteComments(long recipeId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Comment> query = currentSession.createQuery("DELETE FROM Comment WHERE recipeId=:recipe");
        query.setParameter("recipe", recipeId);
        try{
            query.executeUpdate();
            return true;
        }catch(Exception ee){
            return false;
        }
    }

    

    
    
}
