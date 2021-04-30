/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.domain.User;
import com.bienmesabe.rest.service.TokenService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author RAUL
 */
@Service
public class TokenServiceImpl implements TokenService{

    @Override
    public String getJWTToken(User user) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        String signatureString = signatureAlgorithm.toString();
        long nowMillis = System.currentTimeMillis();
        //Creating the Header of token JWT
        HashMap<String, Object> header = new HashMap<String,Object>();
        header.put("alg", signatureString);
        header.put("typ","JWT");		  
	    
        JwtBuilder tokenJWT = Jwts					
                .builder()					
                .setHeader(header)
                .setSubject(user.getEmail())
                .setExpiration(new Date(nowMillis + 3600000))
                .claim("name", user.getName())
                .claim("pass", user.getPassword())
                .signWith(SignatureAlgorithm.HS512,"BienMeSabeApplicationOfCSRIOC2021M12ProjecteDesenvolupamentAplicacionsWeb".getBytes());
	    
        return tokenJWT.compact();		    		    		    			
    }
    
}
