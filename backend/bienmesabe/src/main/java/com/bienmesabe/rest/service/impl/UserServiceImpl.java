/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.UserDAO;
import com.bienmesabe.rest.domain.User;
import com.bienmesabe.rest.service.TokenService;
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
    
    @Autowired 
    private TokenService tokenService;
    
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
     * Implementation of interface method to recover the user by id
     * @param id long that represents the id of the users to search
     * @return the user filtered by id
     */
    @Override
    public User findUserByIdWithAllProperties(Long id) {
        return userDAO.findUserByIdWithAllProperties(id);
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
        
    @Override
    public User authenticateUser(String data){
        User result = new User();
        String[] splittedData = data.split("___");
        String password = splittedData[0].split("---")[1], email = "", alias = "";
        for (int i = 1; i<splittedData.length;i++){
            String[] spplitedValues = splittedData[i].split("---");
            String key = spplitedValues[0];
            String values = spplitedValues.length > 1 ? spplitedValues[1] : "";
            if(key.equals("email") && values != ""){
                email = values;
            }else if(key.equals("alias") && values != ""){
               alias = values;
            }
        }
        if(email != ""){
            result = userDAO.authenticateUserByEmail(email, password);
        }else{
            result = userDAO.authenticateUserByAlias(alias, password);
        }
        if(result != null){
            String token = tokenService.getJWTToken(result);
            result.setToken(token);
            result.setTokenEndValidityDate(tokenService.methodTokenIsValidDate(token));
            return result;
        }
        return null;
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
     * @param user string with the parameters of the user to update
     * @return a boolean that indicates if the user is successfully updated or not
     */
    @Override
    public boolean modifyUser(String user) {
        Long userId = 0L;
        String newNIF ="", imagePath ="", nameNew ="", surnameNew ="", emailNew ="", phoneNew =""; 
        String[] splittedUser = user.split("___");
        String[] splittedUserIds = splittedUser[0].split("---");
        userId = Long.parseLong(splittedUserIds[1]);
        for (int i = 1; i<splittedUser.length;i++){
            String[] spplitedValues = splittedUser[i].split("---");
            String key = spplitedValues[0];
            String values = spplitedValues[1];
            
            if(key.equals("nif")){
                newNIF = values;
            }
            if(key.equals("image")){
               imagePath = values;
            }
            if(key.equals("name")){
                nameNew = values;
            }
            if(key.equals("surname")){
                surnameNew = values;
            }
            if(key.equals("phone")){
                phoneNew = values;
            }
        }

        return userDAO.modifyUser(userId, newNIF, imagePath, nameNew, surnameNew, phoneNew);
    }

     /**
     * Method to modify the password of the user
     * @param pass string with the information of the user for change the password
     * @return a boolean that indicates if the password of the user is successfully updated or not
     */
    @Override
    public boolean modifyUserPassword(String pass){
        Long userId = 0L;
        String[] splittedPass = pass.split("___");
        String[] splittedUserIds = splittedPass[0].split("---");
        userId = Long.parseLong(splittedUserIds[1]);
        String[] splittedPassword = splittedPass[1].split("---");
        String[] splittedPasswordsValues = splittedPassword[1].split(",,,");
        return userDAO.modifyUserPassword(userId, splittedPasswordsValues[0], splittedPasswordsValues[1]);
    }
    
    /**
     * Method to modify the email of the user
     * @param mail string with the information of the user for change the email
     * @return a boolean that indicates if the email of the user is successfully updated or not
     */
    @Override
    public boolean modifyUserEmail(String mail){
        Long userId = 0L;
        String[] splittedMail = mail.split("___");
        String[] splittedUserIds = splittedMail[0].split("---");
        userId = Long.parseLong(splittedUserIds[1]);
        String[] splittedEmails = splittedMail[1].split("---");
        String[] splittedEmailsValues = splittedEmails[1].split(",,,");
        return userDAO.modifyUserEmail(userId, splittedEmailsValues[0], splittedEmailsValues[1]);
    }
    
    /**
     * Method to modify the alias of the user
     * @param alias string with the information of the user for change the alias
     * @return a boolean that indicates if the alias of the user is successfully updated or not
     */
    @Override
    public boolean modifyUserAlias(String alias){
        Long userId = 0L;
        String[] splittedMail = alias.split("___");
        String[] splittedUserIds = splittedMail[0].split("---");
        userId = Long.parseLong(splittedUserIds[1]);
        String[] splittedEmails = splittedMail[1].split("---");
        return userDAO.modifyUserAlias(userId, splittedEmails[1]);
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
