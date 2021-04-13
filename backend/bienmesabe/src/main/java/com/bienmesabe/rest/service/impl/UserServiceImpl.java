/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.UserDAO;
import com.bienmesabe.rest.domain.User;
import com.bienmesabe.rest.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface UserService (service)
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Service
public class UserServiceImpl implements UserService{

    /**
     * Bean of the user repository (Interface)
     */
    @Autowired
    private UserDAO userDAO;
    
    /**
     * Implementation of interface method to recover the users
     * @return a list with the users
     */
    @Override
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    /**
     * Implementation of interface method to recover the user by id
     * @param id long that represents the id of the users to search
     * @return the user filtered by id
     */
    @Override
    public User findUserById(Long id) {
        return userDAO.findUserById(id);
    }

    /**
     * Implementation of interface method to recover the user by name
     * @param name string that represents the name of the users to search
     * @return the user filtered by name
     */
    @Override
    public User findUserByName(String name) {
        return userDAO.findUserByName(name);
    }

    /**
     * Implementation of interface method to recover the user by email
     * @param email string that represents the email of the users to search
     * @return the user filtered by email
     */
    @Override
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    /**
     * Implementation of interface method to recover the user by alias
     * @param alias string that represents the alias of the users to search
     * @return the user filtered by alias
     */
    @Override
    public User findUserByAlias(String alias) {
       return userDAO.findUserByAlias(alias);
    }
        
    /**
     * Implementation of interface method to create a user
     * @param user object that represents the user to persist
     * @return a long with the id of the persisted user
     */    
    @Override
    public Long createUser(User user) {
        return userDAO.createUser(user);
    }

    /**
     * Implementation of interface method to modify an user
     * @param user object that represents the user to modify
     */
    @Override
    public void modifyUser(User user) {
        userDAO.modifyUser(user);
    }

    /**
     * Implementation of interface method to delete an user by id
     * @param id long with the id of the user to delete
     */
    @Override
    public void deleteUserById(Long id) {
        userDAO.deleteUserById(id);
    }

    /**
     * Implementation of interface method to delete an user by alias
     * @param alias long with the alias of the user to delete
     */
    @Override
    public void deleteUserByAlias(String alias) {
        userDAO.deleteUserByAlias(alias);
    }
    
}
