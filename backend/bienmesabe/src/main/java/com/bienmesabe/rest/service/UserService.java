/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.User;
import java.util.List;

/**
 * Inteface with the service methods to retrive the users
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
public interface UserService {
    
    /**
     * Method to recover the users
     * @return a list with the users
     */
    public List<User> findAllUsers();
    
    /**
     * Method to recover the user by id
     * @param id long that represents the id of the users to search
     * @return the user filtered by id
     */
    public User findUserById(Long id);
    
    /**
     * Method to recover the user by id
     * @param id long that represents the id of the users to search
     * @return the user filtered by id
     */
    public User findUserByIdWithAllProperties(Long id);
    
    /**
     * Method to recover the user by name
     * @param name string that represents the name of the users to search
     * @return the user filtered by name
     */
    public User findUserByName(String name);
    
    /**
     * Method to recover the user by email
     * @param email string that represents the email of the users to search
     * @return the user filtered by email
     */
    public User findUserByEmail(String email);
    
    /**
     * Method to recover the user by alias
     * @param alias string that represents the alias of the users to search
     * @return the user filtered by alias
     */
    public User findUserByAlias(String alias);
    
    public int getUserValoration(long id);
    
    
    public User authenticateUser(String data);

    /**
     * Method to create a user
     * @param user object that represents the user to persist
     * @return a long with the id of the persisted user
     */
    public Long createUser(User user);
    
    /**
     * Implementation of interface method to modify an user
     * @param user string with the parameters of the user to update
     * @return a boolean that indicates if the user is successfully updated or not
     */
    public boolean modifyUser(String user);
    
    /**
     * Method to modify the password of the user
     * @param pass string with the information of the user for change the password
     * @return a boolean that indicates if the password of the user is successfully updated or not
     */
    public boolean modifyUserPassword(String pass);
    
    /**
     * Method to modify the email of the user
     * @param mail string with the information of the user for change the email
     * @return a boolean that indicates if the mail of the user is successfully updated or not
     */
    public boolean modifyUserEmail(String mail);
    
    /**
     * Method to modify the alias of the user
     * @param alias string with the information of the user for change the alias
     * @return a boolean that indicates if the alias of the user is successfully updated or not
     */
    public boolean modifyUserAlias(String alias);
    
            
    /**
     * Method to delete an user by id
     * @param id long with the id of the user to delete
     */
    public void deleteUserById(Long id);
    
    /**
     * Method to delete an user by alias
     * @param alias long with the alias of the user to delete
     */
    public void deleteUserByAlias(String alias);
}
