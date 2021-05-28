/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;

import com.bienmesabe.rest.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for token validity // url: http://localhost:8080/api/tokenValidity
 * @author RAUL
 * @version 20/04/2021
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/tokenValidity")
public class TokenController {
    
    /**
     * Bean of the token service (Interface)
     */
    @Autowired
    private TokenService tokenService;
    
    /**
     * Method to check if the indicated token is expired or not // HTTP verb: GET url: http://localhost:8080/api/tokenValidity/validateDate
     * @param token string that represents the token
     * @return an string that indicates the date and time of the expiration of the token
     */
    @GetMapping("validateDate")
    public String validateDate(@RequestParam String token){
        String res =  tokenService.methodTokenIsValidDate(token);
        return res;
    }
}
