/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import com.bienmesabe.rest.domain.User;

/**
 *
 * @author RAUL
 */
public interface TokenService {
    public String getJWTToken(User user);
}
