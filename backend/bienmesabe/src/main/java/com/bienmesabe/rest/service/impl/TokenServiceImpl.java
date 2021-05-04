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
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author RAUL
 */
@Service
public class TokenServiceImpl implements TokenService{

    @Override
    public String getJWTToken(User user) {
    		    
        String secretKey = "BienMeSabeApplicationOfCSRIOC2021M12ProjecteDesenvolupamentAplicacionsWeb";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN,ROLE_NUTRICIONIST");
        
        String token = Jwts
                .builder()
                .setSubject(user.getName())
                .claim("user_name", user.getName())
                .claim("Alias", user.getAlias())
                .claim("authorities", grantedAuthorities.stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        
        return "Bearer " + token;        
                
                
    }
    
   
    
}
