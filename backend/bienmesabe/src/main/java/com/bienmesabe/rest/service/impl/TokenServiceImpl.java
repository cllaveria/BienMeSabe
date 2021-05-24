/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.domain.User;
import com.bienmesabe.rest.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author RAUL
 */
@Service
public class TokenServiceImpl implements TokenService{

    /**
     * Method to create the token of the indicated user
     * @param user object that represents the user
     * @return a string that contains the token
     */
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
                .setExpiration(new Date(System.currentTimeMillis() + 14400000))//4h
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        
        return "Bearer " + token;        
                
                
    }
    
    /**
     * Method to check the validity date of the token
     * @param token string that contains the token
     * @return a string with the end date of validity of the token
     */
    @Override
    public String methodTokenIsValidDate(String token){
        String[] chunks = token.split("\\.");
        
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        String[] payloadSplitted = payload.split(",");
        for(String element:payloadSplitted){
            if(element.contains("exp")){
                String[] splitted = element.split(":");
                String dateValue = splitted[1].replace("\"", "");
                String datValue = dateValue.replace("}", "");
                Calendar calendar= Calendar.getInstance();

                calendar.setTimeInMillis(Long.parseLong(datValue)*1000);
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                
                return formatter.format(calendar.getTime());
            }

        }
        return "";
    }
    
}
