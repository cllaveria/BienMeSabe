/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;


import com.bienmesabe.rest.domain.User;
import com.bienmesabe.rest.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author RAUL
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }
    
    @GetMapping("/getUserById/{ID}")
    public User findUserById(@PathVariable Long id){
        User user = userService.findUserById(id);
        if(user == null){
            throw new RuntimeException("User id not found - " + id);
        }
        return user;
    }
    @GetMapping("/findUserByName/{name}")
    public User findUserByName(@PathVariable String name){
        User user = userService.findUserByName(name);
        if(user == null){
            throw new RuntimeException("User id not found - " + name);
        }
        return user;
    }
    @GetMapping("/findUserByEmail/{email}")
    public User findUserByEmail(@PathVariable String email){
        User user = userService.findUserByEmail(email);
        if(user == null){
            throw new RuntimeException("User id not found - " + email);
        }
        return user;
    }
    
    @GetMapping("/findUserByAlias/{alias}")
    public User findUserByAlias(@PathVariable String alias){
        User user = userService.findUserByAlias(alias);
        if(user == null){
            throw new RuntimeException("User id not found - " + alias);
        }
        return user;
    }
    
    @PostMapping("/addUser")
    public User addUser(User user){
        user.setId(0L);
        userService.createUser(user);
        return user;
    }
    
    @PutMapping("/modifyUser")
//    @RequestMapping(value = "/update/")
    public User updateUser(User user){
        userService.modifyUser(user);
        return user;
    }
    
    @DeleteMapping("deleteUserById/{id}")
    public String deleteUserById(@PathVariable Long id){
        User user = userService.findUserById(id);
        if(user == null) {
            throw new RuntimeException("User id not found -"+id);
        }
        userService.deleteUserById(id);
        return "Deleted user id - "+id;
    }
    @DeleteMapping("deleteUserByAlias/{alias}")
    public String deleteUserByAlias(@PathVariable String alias){
        User user = userService.findUserByAlias(alias);
        if(user == null) {
            throw new RuntimeException("User id not found -" + alias);
        }
        userService.deleteUserByAlias(alias);
        return "Deleted user id - " + alias;
    }
    

}
