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
     * Method to recover all of the users with all properties present in the DB
     * @return a list with the users (with all properties) in the DB
     */
    public List<User> findAllUsersWithAllProperties();
            
    /**
     * Method to recover the user present in the DB by id
     * @param id long that represents the id of the users to search
     * @return the user in the DB filtered by id
     */
    public User findUserById(Long id);
    
    /**
     * Method to recover the user present in the DB by id
     * @param id long that represents the id of the users to search
     * @return the user in the DB filtered by id
     */
    public User findUserByIdWithAllProperties(Long id);
    
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
     * Method to authenticate an user by alias
     * @param alias string that represents the alias of the user
     * @param pass string that represents the password of the user
     * @return an object of type user, or a null value if not exists in the DB
     */
    public User authenticateUserByAlias(String alias, String pass);
    
    /**
     * Method to authenticate an user by email
     * @param email string that represents the email of the user
     * @param pass string that represents the password of the user
     * @return an object of type user, or a null value if not exists in the DB
     */
    public User authenticateUserByEmail(String email, String pass);
    
    /**
     * Method to create a user in the table users of the DB
     * @param user object that represents the user to persist
     * @return a long with the id of the persisted user
     */
    public Long createUser(User user);
    
    /**
     * Method to modify an user in the table users of the DB
     * @param userId long that represents the id of the user to modify
     * @param newNIF string that represents the new NIF of the user
     * @param imagePath string that represents the new image path of the user
     * @param surnameNew string that represents the new surname of the user
     * @param nameNew string that represents the new name of the user
     * @param phoneNew string that represents the new phone of the user
     * @return a boolean that indicates if the user is successfully updated or not
     */
    public boolean modifyUser(Long userId, String newNIF, String imagePath, String nameNew, String surnameNew, String phoneNew);
    
    /**
     * Method to modify the password of the user
     * @param userId long that represents the id of the user to modify
     * @param passwordOld string that represents the old password of the user
     * @param passwordNew string that represents the new password of the user
     * @return a boolean that indicates if the password of the user is successfully updated or not
     */
    public boolean modifyUserPassword(Long userId, String passwordOld, String passwordNew);
    
    /**
     * Method to modify the email of the user
     * @param userId long that represents the id of the user to modify
     * @param emailOld string that represents the old email of the user
     * @param emailNew string that represents the new email of the user
     * @return a boolean that indicates if the email of the user is successfully updated or not
     */
    public boolean modifyUserEmail(Long userId, String emailOld, String emailNew);
    
    /**
     * Method to modify the alais of the user
     * @param userId long that represents the id of the user to modify
     * @param aliasNew string that represents the new alias of the user
     * @return a boolean that indicates if the alias of the user is successfully updated or not
     */
    public boolean modifyUserAlias(Long userId, String aliasNew);
    
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
