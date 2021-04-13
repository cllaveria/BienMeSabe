/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.User;
import java.util.List;

/**
 * Inteface with the methods to retrive the DB data of users
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
public interface UserDAO {
    
    /**
     * Method to recover the users present in the DB
     * @return a list with the users in the DB
     */
    public List<User> findAllUsers();
    
    /**
     * Method to recover the user present in the DB by id
     * @param id long that represents the id of the users to search
     * @return the user in the DB filtered by id
     */
    public User findUserById(Long id);
    
    /**
     * Method to recover the user present in the DB by name
     * @param name string that represents the name of the users to search
     * @return the user in the DB filtered by name
     */
    public User findUserByName(String name);
    
    /**
     * Method to recover the user present in the DB by email
     * @param email string that represents the email of the users to search
     * @return the user in the DB filtered by email
     */
    public User findUserByEmail(String email);
    
    /**
     * Method to recover the user present in the DB by alias
     * @param alias string that represents the alias of the users to search
     * @return the user in the DB filtered by alias
     */
    public User findUserByAlias(String alias);
    
    /**
     * Method to create a user in the table users of the DB
     * @param user object that represents the user to persist
     * @return a long with the id of the persisted user
     */
    public Long createUser(User user);
    
    /**
     * Method to modify an user in the table users of the DB
     * @param user object that represents the user to modify
     */
    public void modifyUser(User user);
    
    /**
     * Method to delete an user in the table users of the DB by id
     * @param id long with the id of the user to delete
     */
    public void deleteUserById(Long id);
    
    /**
     * Method to delete an user in the table users of the DB by alias
     * @param alias long with the alias of the user to delete
     */
    public void deleteUserByAlias(String alias);
}
