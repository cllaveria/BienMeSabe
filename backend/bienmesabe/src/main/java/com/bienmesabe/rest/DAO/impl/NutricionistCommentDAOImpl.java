/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.NutricionistCommentDAO;
import com.bienmesabe.rest.domain.NutricionistComment;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface NutricionistCommentDAO (repository)
 * @author RAUL
 */
@Repository
public class NutricionistCommentDAOImpl implements NutricionistCommentDAO{
    
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
    public List<NutricionistComment> findAllComments() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NutricionistComment> query = currentSession.createQuery("FROM NutricionistComment",NutricionistComment.class);
        return query.getResultList();
    }

    /**
     * Implementation of interface method to recover all the comments of the nutricionist
     * @param nutricionistId long that represents the id of the nutricionist
     * @return a list with the comments of the nutricionist
     */
    @Override
    @Transactional
    public List<NutricionistComment> findAllCommentsOfNutricionist(Long nutricionistId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NutricionistComment> query = currentSession.createQuery("FROM NutricionistComment WHERE nutricionistId=:id", NutricionistComment.class);
        query.setParameter("id", nutricionistId);
        List<NutricionistComment> comments = query.getResultList();
        return comments;
    }

    /**
     * Implementation of interface method to recover all the comments of the nutricionist
     * @param comment  object that represents the nutricionist comment to persist
     * @return a list with the comments of the nutricionist
     */
    @Override
    @Transactional
    public boolean createComment(NutricionistComment comment) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NutricionistComment> query = currentSession.createQuery("FROM Comment WHERE nutricionistId=:nutricionist and userId=:user", NutricionistComment.class);
        query.setParameter("nutricionist", comment.getNutricionistId());
        query.setParameter("user", comment.getUserId());
        NutricionistComment commentInDB = new NutricionistComment();
        try{
             commentInDB= query.getSingleResult();
        }catch(Exception e){
            commentInDB = null;
        }
        if(commentInDB==null){
            currentSession.save(comment); 
            return true;
        }
        return false;
    }
    
}
