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
@RequestMapping("/api/user")
public class UserController {
    
    /**
     * Bean of the user service (Interface)
     */
    @Autowired
    private UserService userService;
    
    /**
     * Method to recover the users  // HTTP verb: GET url: http://localhost:8080/api/user/getUsers
     * @return a list with the users
     */
    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }
    
    /**
     * Method to recover the user by id // HTTP verb: GET url: http://localhost:8080/api/user/getUserById/{UserId}
     * @param id string that represents the id of the users to search
     * @return the user filtered by id
     */
    @GetMapping("/getUserById/{id}")
    public User findUserById(@PathVariable String id){
        User user = userService.findUserById(Long.parseLong(id));
        return user;
    }
    
    /**
     * Method to recover the user by name // HTTP verb: GET url: http://localhost:8080/api/user/findUserByName/{UserName}
     * @param name string that represents the name of the users to search
     * @return the user filtered by name
     */
    @GetMapping("/findUserByName/{name}")
    public User findUserByName(@PathVariable String name){
        User user = userService.findUserByName(name);
        return user;
    }
    
    /**
     * Method to recover the user by email // HTTP verb: GET url: http://localhost:8080/api/user/findUserByName/{UserEmail}
     * @param email string that represents the email of the users to search
     * @return the user filtered by email
     */
    @GetMapping("/findUserByEmail/{email}")
    public User findUserByEmail(@PathVariable String email){
        User user = userService.findUserByEmail(email);
        return user;
    }
    
    /**
     * Method to recover the user by alias // HTTP verb: GET url: http://localhost:8080/api/user/findUserByAlias/{UserAlias}
     * @param alias string that represents the alias of the users to search
     * @return the user filtered by alias
     */
    @GetMapping("/findUserByAlias/{alias}")
    public User findUserByAlias(@PathVariable String alias){
        User user = userService.findUserByAlias(alias);
        return user;
    }
    
    /**
     * Method to create the user // HTTP verb: POST url: http://localhost:8080/api/user/addUser
     * @param user object that represents the user to create
     * @return the created user
     */
    @PostMapping("/addUser")
    public User addUser(User user){
        user.setId(0L);
        Long createdUser = userService.createUser(user);
        if(createdUser > 0){
            user.setId(createdUser);
            return user;
        }
        return new User();
    }
    
    /**
     * Method to create the user // HTTP verb: POST url: http://localhost:8080/api/user/addUser
     * @param user object that represents the user to create
     * @return the created user
     */
    @PostMapping("/loginUser")
    public String loginUser(@RequestParam String data){
        return userService.authenticateUser(data);
    }
    
    /**
     * Method to modify the user // HTTP verb: PUT url: http://localhost:8080/api/user/modifyUser
     * @param user object that represents the user to modify
     * @return the modified user
     */
    @PutMapping("/modifyUser/{user}")
    public boolean updateUser(@PathVariable String user){
        try{
            userService.modifyUser(user);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    /**
     * Method to modify the user // HTTP verb: PUT url: http://localhost:8080/api/user/modifyUser
     * @param pass object that represents the user password to modify
     * @return the modified user
     */
    @PutMapping("/updateUserPassword/{pass}")
    public boolean updateUserPassword(@PathVariable String pass){
        boolean result = false;
        try{
            result = userService.modifyUserPassword(pass);
            return result;
        }catch(Exception e){
            return result;
        }
    }
    
    /**
     * Method to modify the user // HTTP verb: PUT url: http://localhost:8080/api/user/modifyUser
     * @param mail object that represents the user email to modify
     * @return the modified user
     */
    @PutMapping("/updateUserEmail/{mail}")
    public boolean updateUserEmail(@PathVariable String mail){
        try{
            userService.modifyUserEmail(mail);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    /**
     * Method to modify the user // HTTP verb: PUT url: http://localhost:8080/api/user/modifyUser
     * @param alias object that represents the user alias to modify
     * @return the modified user
     */
    @PutMapping("/updateUserAlias/{alias}")
    public boolean updateUserAlias(@PathVariable String alias){
        try{
            userService.modifyUserAlias(alias);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    /**
     * Method to delete the user by id // HTTP verb: DELETE url: http://localhost:8080/api/user/deleteUserById/{UserId}
     * @param id string with the id of the user to delete
     * @return  an string that informs the id of the deleted user
     */
    @DeleteMapping("deleteUserById/{id}")
    public String deleteUserById(@PathVariable String id){
        User user = userService.findUserById(Long.parseLong(id));
        if(user != null) {
           userService.deleteUserById(Long.parseLong(id));
           return "Deleted user id - "+id;
        }
        
        return null;
    }
    
    /**
     * Method to delete the user by alias // HTTP verb: DELETE url: http://localhost:8080/api/user/deleteUserByAlias/{UserAlias}
     * @param alias long with the alias of the user to delete
     * @return an string that informs the id of the deleted user
     */
    @DeleteMapping("deleteUserByAlias/{alias}")
    public String deleteUserByAlias(@PathVariable String alias){
        User user = userService.findUserByAlias(alias);
        if(user != null) {
            userService.deleteUserByAlias(alias);
            return "Deleted user id - " + alias;
        }
        return null;
    }
    

}
