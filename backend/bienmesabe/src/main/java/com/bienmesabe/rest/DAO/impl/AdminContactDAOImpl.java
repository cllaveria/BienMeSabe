/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.AdminContactDAO;
import com.bienmesabe.rest.domain.AdminContact;
import com.bienmesabe.rest.domain.Comment;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface AdminContactDAO (repository)
 * @author RAUL
 * @version 23/05/2021
 */
@Repository
public class AdminContactDAOImpl implements AdminContactDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    /**
     * Implementation of interface method to recover the admin contacts present in the DB
     * @return a list with the admin contacts in the DB
     */
    @Override
    @Transactional
    public List<AdminContact> findAllAdminContacts() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<AdminContact> query = currentSession.createQuery("FROM AdminContact",AdminContact.class);
        return query.getResultList();
    }

    /**
     * Implementation of interface method to create an admin contact in the table admin_contact of the DB
     * @param adminContact object that represents the admin contact to persist
     * @return a boolean which indcates if the contact has been successfully inserted into the DB or not
     */
    @Override
    @Transactional
    public Boolean createAdminContact(AdminContact adminContact) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<AdminContact> query = currentSession.createQuery("FROM AdminContact WHERE message=:messageN and messageSubject=:subject and userId=:user", AdminContact.class);
        query.setParameter("messageN", adminContact.getMessage());
        query.setParameter("subject", adminContact.getMessageSubject());
        query.setParameter("user", adminContact.getUserId());
        AdminContact adminCommentInDB = new AdminContact();
        try{
             adminCommentInDB= query.getSingleResult();
        }catch(Exception e){
            adminCommentInDB = null;
        }
        if(adminCommentInDB==null){
            currentSession.save(adminContact); 
            return true;
        }
        return false;
    }
    
}
