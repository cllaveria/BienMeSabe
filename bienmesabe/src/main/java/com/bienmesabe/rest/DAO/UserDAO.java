/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author RAUL
 */
public interface UserDAO {
    public List<User> findAllUsers();
    public User findUserById(Long id);
    public User findUserByName(String name);
    public User findUserByEmail(String email);
    public User findUserByAlias(String alias);
    public Long createUser(User user);
    public void modifyUser(User user);
    public void deleteUserById(Long id);
    public void deleteUserByAlias(String alias);
}
