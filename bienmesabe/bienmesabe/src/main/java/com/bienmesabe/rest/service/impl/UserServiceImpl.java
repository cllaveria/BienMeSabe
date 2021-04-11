/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.UserDAO;
import com.bienmesabe.rest.domain.User;
import com.bienmesabe.rest.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RAUL
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;
    
      
    @Override
    public List<User> findAllUsers() {
        List<User> listUsers = userDAO.findAllUsers();
        return listUsers;
    }

    @Override
    public User findUserById(Long id) {
        User user = userDAO.findUserById(id);
        return user;
    }

    @Override
    public User findUserByName(String name) {
        User user = userDAO.findUserByName(name);
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userDAO.findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByAlias(String alias) {
        User user = userDAO.findUserByAlias(alias);
        return user;
    }
    @Override
    public Long createUser(User user) {
        Long id = userDAO.createUser(user);
        return id;
    }

    @Override
    public void modifyUser(User user) {
        userDAO.modifyUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userDAO.deleteUserById(id);
    }

    @Override
    public void deleteUserByAlias(String alias) {
        userDAO.deleteUserByAlias(alias);
    }
    
}
