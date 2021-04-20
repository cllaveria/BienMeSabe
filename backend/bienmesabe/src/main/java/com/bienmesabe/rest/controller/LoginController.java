/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;


import com.bienmesabe.rest.domain.User;
import com.bienmesabe.rest.service.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Users // url: http://localhost:8080/api/user
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class LoginController {
    
    /**
     * Bean of the user service (Interface)
     */
    @Autowired
    private UserService userService;
    
    /**
     * Method to create the user // HTTP verb: POST url: http://localhost:8080/api/user/addUser
     * @param user object that represents the user to create
     * @return the created user
     */
    @PostMapping("/login")
    public User addUser(@RequestParam("email") String email, @RequestParam("alias") String alias, @RequestParam("pwd") String pass){
        
        return new User();
    }
    private String getJWTToken(String email) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        String signatureString = signatureAlgorithm.toString();
        //Creating the Header of token JWT
        HashMap<String, Object> header = new HashMap<String,Object>();
        header.put("alg", signatureString);
        header.put("typ","JWT");		    
        //-------------------------------------------------------------------------------
        //CREATING TOKEN + ADDING HEADER		    //-------------------------------------------------------------------------------			
        //Generate tokenJWT + adding header		    
        JwtBuilder tokenJWT = Jwts					
                .builder()					
                .setHeader(header).setPayload(email);		    
        //-------------------------------------------------------------------------------								
        //CREATING TOKEN + ADDING HEADER		    
        //-------------------------------------------------------------------------------			
        //Compact the tokenJWT + save the value in tokenJWTString		    
        String tokenJWTString = tokenJWT.compact();		    
        System.out.println(tokenJWTString);		    		    
        //Response to Request from Controller			
        return tokenJWTString;		
    }
}
