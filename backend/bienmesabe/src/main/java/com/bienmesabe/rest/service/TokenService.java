/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.User;

/**
 * Inteface with the service methods of the token
 * @author RAUL
 */
public interface TokenService {
    
    /**
     * Method to create the token of the indicated user
     * @param user object that represents the user
     * @return a string that contains the token
     */
    public String getJWTToken(User user);
    
    /**
     * Method to check the validity date of the token
     * @param token string that contains the token
     * @return a string with the end date of validity of the token
     */
    public String methodTokenIsValidDate(String token);
}
