/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
/**
 * This class defines the security of the app
 * @author RAUL
 * @version 10/05/2021
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String SECRET = "BienMeSabeApplicationOfCSRIOC2021M12ProjecteDesenvolupamentAplicacionsWeb";

    /**
     * Method to recover the assessments
     * @param request represents the Http request of the servlet
     * @param response represents the Http response of the servlet
     * @param chain object that represents the filterchain of the security
     * @throws javax.servlet.ServletException throwed error of the type ServletException
     * @throws java.io.IOException throwed error of the type IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
                if (checkJWTToken(request, response)) {
                        Claims claims = validateToken(request);
                        if (claims.get("authorities") != null) {
                                setUpSpringAuthentication(claims);
                        } else {
                                SecurityContextHolder.clearContext();
                        }
                }else {
                        SecurityContextHolder.clearContext();
                }
                chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
                return;
        }
    }	

    /**
     * Method to check if the JWT token has a valid signature
     * @param request represents the Http request of the servlet
     * return the claims of the token
     */
    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    /**
     * Authentication method in Spring flow
     * @param claims object that represents the Claims needed by Spring to allow or deny the access
     */
    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List) claims.get("authorities");


        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                        authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    /**
     * Method to check if the JWT token has a correct header
     * @param request represents the Http request of the servlet
     * @param res represents the Http response of the servlet
     * return a boolean if the token has a correct header or not
     */
    private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
                return false;
        return true;
    }

    
}
