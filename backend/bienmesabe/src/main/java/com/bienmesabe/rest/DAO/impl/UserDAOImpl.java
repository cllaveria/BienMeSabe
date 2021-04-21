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
 * Class for implementation of Inteface UserDAO (repository)
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Repository
public class UserDAOImpl implements UserDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    /**
     * Implementation of interface method to recover the users present in the DB
     * @return a list with the users in the DB
     */
    @Override
    @Transactional
    public List<User> findAllUsers() {
        Session currentSession = entityManager.unwrap(Session.class);
        
        Query<User> query = currentSession.createQuery("from User", User.class);
        
        List<User> users = query.getResultList();
        for(int i =0;i<users.size();i++){
            users.get(i).setPassword("");
            users.get(i).setNIF("");
            users.get(i).setPhone("");
        }
        return users;
    }

    /**
     * Implementation of interface method to recover the user present in the DB by id
     * @param id long that represents the id of the users to search
     * @return the user in the DB filtered by id
     */
    @Override
    @Transactional
    public User findUserById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        User user = currentSession.get(User.class, id);
        user.setPassword("");
        user.setNIF("");
        user.setPhone("");
        return user;
    }

    /**
     * Implementation of interface method to recover the user present in the DB by name
     * @param name string that represents the name of the users to search
     * @return the user in the DB filtered by name
     */
    @Override
    @Transactional
    public User findUserByName(String name) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("FROM User WHERE name=:name", User.class);
        query.setParameter("name", name);
        User user = new User();
        try{
            user = query.getSingleResult();
            user.setPassword("");
            user.setNIF("");
            user.setPhone("");
        }catch(Exception e){
            return user;
        }
        return user;
    }

    /**
     * Implementation of interface method to recover the user present in the DB by email
     * @param email string that represents the email of the users to search
     * @return the user in the DB filtered by email
     */
    @Override
    @Transactional
    public User findUserByEmail(String email) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("FROM User WHERE email=:email", User.class);
        query.setParameter("email", email);
        User user = query.getSingleResult();
        user.setPassword("");
        user.setNIF("");
        user.setPhone("");
        return user;
    }
    
    /**
     * Implementation of interface method to recover the user present in the DB by alias
     * @param alias string that represents the alias of the users to search
     * @return the user in the DB filtered by alias
     */
    @Override
    @Transactional
    public User findUserByAlias(String alias) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("FROM User WHERE alias=:alias", User.class);
        query.setParameter("alias", alias);
        User user = query.getSingleResult();
        user.setPassword("");
        user.setNIF("");
        user.setPhone("");
        return user;
    }
    
    /**
     * Implementation of interface method to create a user in the table users of the DB
     * @param user object that represents the user to persist
     * @return a long with the id of the persisted user
     */
    @Override
    @Transactional
    public Long createUser(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("FROM User WHERE name=:name and email=:email and type=:userType", User.class);
        query.setParameter("name", user.getName());
        query.setParameter("email", user.getEmail());
        query.setParameter("userType", user.getType());
        User userInDB = new User();
        try{
             userInDB= query.getSingleResult();
        }catch(Exception e){
            userInDB = null;
        }
        if(userInDB==null){
            Long idGenerado = (Long) currentSession.save(user); 
            return idGenerado;
        }
        return 0L;
    }

    /**
     * Implementation of interface method to modify an user in the table users of the DB
     * @param userId long that represents the id of the user to modify
     * @param newNIF string that represents the new NIF of the user
     * @param imagePath string that represents the new image path of the user
     * @param surnameNew string that represents the new surname of the user
     * @param nameNew string that represents the new name of the user
     * @param phoneNew string that represents the new phone of the user
     * @return a boolean that indicates if the user is successfully updated or not
     */
    @Override
    @Transactional
    public boolean modifyUser(Long userId, String newNIF, String imagePath, String nameNew, String surnameNew, String phoneNew) {
        Session currentSession = entityManager.unwrap(Session.class);
        try{
            String updates = "update User set ";
            
            if(newNIF != null && !newNIF.isEmpty()) updates +=  "NIF=:newNIF, ";
            if(imagePath != null && !imagePath.isEmpty()) updates +=  "image=:imagePath, ";
            if(nameNew != null && !nameNew.isEmpty()) updates +=  "name=:nameNew, ";
            if(surnameNew != null && !surnameNew.isEmpty()) updates +=  "surname=:surnameNew, ";
            if(phoneNew != null && !phoneNew.isEmpty()) updates +=  "phone=:phoneNew, ";
            
            updates = updates.substring(0, updates.length() -2);
            updates += " WHERE id=:userid";
            Query<User> query = currentSession.createQuery(updates);
            query.setParameter("userid", userId);
            
            if(newNIF != null && !newNIF.isEmpty()) query.setParameter("newNIF", newNIF);
            if(imagePath != null && !imagePath.isEmpty()) query.setParameter("imagePath", imagePath);
            if(nameNew != null && !nameNew.isEmpty()) query.setParameter("nameNew", nameNew);
            if(surnameNew != null && !surnameNew.isEmpty()) query.setParameter("surnameNew", surnameNew);
            if(phoneNew != null && !phoneNew.isEmpty()) query.setParameter("phoneNew", phoneNew);
            
            query.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * Implementation of interface method to modify the password of the user
     * @param userId long that represents the id of the user to modify
     * @param passwordOld string that represents the old password of the user
     * @param passwordNew string that represents the new password of the user
     * @return a boolean that indicates if the password of the user is successfully updated or not
     */
    @Override
    @Transactional
    public boolean modifyUserPassword(Long userId, String passwordOld, String passwordNew) {
        Session currentSession = entityManager.unwrap(Session.class);
        User user = new User();
        try{
            Query<User> query = currentSession.createQuery("From User where id=:userId");
            query.setParameter("userId", userId);
            user = query.getSingleResult();
            if(user.getPassword().equals(passwordOld)){

                Query<User> queryU = currentSession.createQuery("update User set password=:passwordNew where id=:userId");
                queryU.setParameter("userId", userId);
                queryU.setParameter("passwordNew", passwordNew);
                queryU.executeUpdate();
                return true;
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }
    
    /**
     * Method to modify the email of the user
     * @param userId long that represents the id of the user to modify
     * @param emailOld string that represents the old email of the user
     * @param emailNew string that represents the new email of the user
     * @return a boolean that indicates if the email of the user is successfully updated or not
     */
    @Override
    @Transactional
    public boolean modifyUserEmail(Long userId, String emailOld, String emailNew){
        Session currentSession = entityManager.unwrap(Session.class);
        User user = new User();
        try{
            Query<User> query = currentSession.createQuery("From User where id=:userId");
            query.setParameter("userId", userId);
            user = query.getSingleResult();
            if(user.getEmail().equals(emailOld)){

                Query<User> queryU = currentSession.createQuery("update User set email=:emailNew where id=:userId");
                queryU.setParameter("userId", userId);
                queryU.setParameter("emailNew", emailNew);
                queryU.executeUpdate();
                return true;
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }
    
    /**
     * Method to modify the alais of the user
     * @param userId long that represents the id of the user to modify
     * @param aliasNew string that represents the new alias of the user
     * @return a boolean that indicates if the alias of the user is successfully updated or not
     */
    @Override
    @Transactional
    public boolean modifyUserAlias(Long userId, String aliasNew){
        Session currentSession = entityManager.unwrap(Session.class);
        try{
            Query<User> queryU = currentSession.createQuery("update User set alias=:aliasNew where id=:userId");
            queryU.setParameter("userId", userId);
            queryU.setParameter("aliasNew", aliasNew);
            queryU.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    /**
     * Implementation of interface method to delete an user in the table users of the DB by id
     * @param id long with the id of the user to delete
     */
    @Override
    @Transactional
    public void deleteUserById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> query = currentSession.createQuery("delete from User where id=:userId");

        query.setParameter("userId", id);
        query.executeUpdate();
    }

    /**
     * Implementation of interface method to delete an user in the table users of the DB by alias
     * @param alias long with the alias of the user to delete
     */
    @Override
    @Transactional
    public void deleteUserByAlias(String alias) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> query = currentSession.createQuery("delete from User where USER_ALIAS=:userAlias");

        query.setParameter("userAlias", alias);
        query.executeUpdate();
    }

    

}
