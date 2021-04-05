/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.UserDAO;
import java.util.List;


import com.bienmesabe.rest.domain.User;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author RAUL
 */
@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public List<User> findAllUsers() {
        Session currentSession = entityManager.unwrap(Session.class);
        
        Query<User> query = currentSession.createQuery("from User", User.class);
        
        List<User> users = query.getResultList();
        
        return users;
    }

    @Override
    @Transactional
    public User findUserById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        User user = currentSession.get(User.class, id);
        return user;
    }

    @Override
    @Transactional
    public User findUserByName(String name) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("FROM User WHERE name=:name", User.class);
        query.setParameter("name", name);
        User user = query.getSingleResult();
        return user;
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("FROM User WHERE email=:email", User.class);
        query.setParameter("email", email);
        User user = query.getSingleResult();
        return user;
    }
    
    @Override
    @Transactional
    public User findUserByAlias(String alias) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("FROM User WHERE alias=:alias", User.class);
        query.setParameter("alias", alias);
        User user = query.getSingleResult();
        return user;
    }
    
    @Override
    @Transactional
    public Long createUser(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        Long idGenerado = (Long) currentSession.save(user); 
        return idGenerado;
    }

    @Override
    @Transactional
    public void modifyUser(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user); 
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> query = currentSession.createQuery("delete from User where id=:userId");

        query.setParameter("userId", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteUserByAlias(String alias) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> query = currentSession.createQuery("delete from User where USER_ALIAS=:userAlias");

        query.setParameter("userAlias", alias);
        query.executeUpdate();
    }

}
