/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;


import com.bienmesabe.rest.domain.AdminContact;
import com.bienmesabe.rest.domain.NutricionistAssessment;
import com.bienmesabe.rest.domain.NutricionistComment;
import com.bienmesabe.rest.domain.NutricionistUsers;
import com.bienmesabe.rest.domain.User;
import com.bienmesabe.rest.service.AdminContactService;
import com.bienmesabe.rest.service.AssessmentService;
import com.bienmesabe.rest.service.NutricionistAssessmentService;
import com.bienmesabe.rest.service.NutricionistCommentService;
import com.bienmesabe.rest.service.NutricionistUsersService;
import com.bienmesabe.rest.service.UserService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.Consumes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class UserController{
    
    /**
     * Bean of the user service (Interface)
     */
    @Autowired
    private UserService userService;
    
    /**
     * Bean of the admin contact service (Interface)
     */
    @Autowired
    private AdminContactService adminContactService;
    
    /**
     * Bean of the nutricionist assessment service (Interface)
     */
    @Autowired
    private NutricionistAssessmentService nutricionistAssessmentService;
    
    /**
     * Bean of the nutricionist Comment service (Interface)
     */
    @Autowired
    private NutricionistCommentService nutricionistCommentService;
    
    /**
     * Bean of the assessment service (Interface)
     */
    @Autowired
    private AssessmentService assessmentService;
    
    /**
     * Bean of the Nutricionist Users service (Interface)
     */
    @Autowired
    private NutricionistUsersService nutricionistUsersService;
    
    /**
     * Method to recover the users  // HTTP verb: GET url: http://localhost:8080/api/user/getUsers
     * @return a list with the users
     */
    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }
    
    /**
     * Method to recover the user by id // HTTP verb: GET url: http://localhost:8080/api/user/getUserById/{id}
     * @param id string that represents the id of the users to search
     * @return the user filtered by id
     */
    @GetMapping("/getUserById/{id}")
    public User findUserById(@PathVariable String id){
        User user = userService.findUserById(Long.parseLong(id));
        return user;
    }
    
    /**
     * Method to recover the user by id with all properties // HTTP verb: GET url: http://localhost:8080/api/user/getUserByIdWithAllProperties/{id}
     * @param id string that represents the id of the users to search
     * @return the user filtered by id with all properties
     */
    @GetMapping("/getUserByIdWithAllProperties/{id}")
    public User findUserByIdWithAllProperties(@PathVariable String id){
        User user = userService.findUserByIdWithAllProperties(Long.parseLong(id));
        return user;
    }
    
    /**
     * Method to recover the user by name // HTTP verb: GET url: http://localhost:8080/api/user/findUserByName/{name}
     * @param name string that represents the name of the users to search
     * @return the user filtered by name
     */
    @GetMapping("/findUserByName/{name}")
    public User findUserByName(@PathVariable String name){
        User user = userService.findUserByName(name);
        return user;
    }
    
    /**
     * Method to recover the user by email // HTTP verb: GET url: http://localhost:8080/api/user/findUserByEmail/{email}
     * @param email string that represents the email of the users to search
     * @return the user filtered by email
     */
    @GetMapping("/findUserByEmail/{email}")
    public User findUserByEmail(@PathVariable String email){
        User user = userService.findUserByEmail(email);
        return user;
    }
    
    /**
     * Method to recover the user by alias // HTTP verb: GET url: http://localhost:8080/api/user/findUserByAlias/{alias}
     * @param alias string that represents the alias of the users to search
     * @return the user filtered by alias
     */
    @GetMapping("/findUserByAlias/{alias}")
    public User findUserByAlias(@PathVariable String alias){
        User user = userService.findUserByAlias(alias);
        return user;
    }
    
    /**
     * Method to recover the user assessment // HTTP verb: GET url: http://localhost:8080/api/user/getUserValoration/{id}
     * @param id string that represents the id of the user
     * @return an integer that represents the value of the assessment of the user
     */
    @GetMapping("/getUserValoration/{id}")
    public int getUserValoration(@PathVariable int id){
        return userService.getUserValoration(id);
    }
    
    /**
     * Method to recover the admin contacts // HTTP verb: GET url: http://localhost:8080/api/user/getAdminContacts
     * @return alist with all the admin contacts
     */
    @GetMapping("/getAdminContacts")
    public List<AdminContact> getAdminContacts(){
        return adminContactService.findAllAdminContacts();
    }
    
    /**
     * Method to recover the asigned nutricionist of the user // HTTP verb: GET url: http://localhost:8080/api/user/getUserNutricionist/{userId}
     * @param userId string that represents the id of the user
     * @return alist with all the admin contacts
     */
    @GetMapping("/getUserNutricionist/{userId}")
    public long getUserNutricionist(@PathVariable String userId){
        NutricionistUsers nutricionist = nutricionistUsersService.findUserAssignment(Long.parseLong(userId));
        if(nutricionist != null){
            return nutricionist.getNutricionistId();
        }else{
            return 0L;
        }
    }
    
    /**
     * Method to recover the asigned nutricionist of the user // HTTP verb: GET url: http://localhost:8080/api/user/getNutricionistUsers/{nutricionistId}
     * @param nutricionistId string that represents the id of the nutricionist
     * @return alist with all the admin contacts
     */
    @GetMapping("/getNutricionistUsers/{nutricionistId}")
    public List<Long> getNutricionistUsers(@PathVariable String nutricionistId){
        List<NutricionistUsers> users = nutricionistUsersService.findNutricionistAssignments(Long.parseLong(nutricionistId));
        if(users != null){
            List<Long> assignedUsers = new ArrayList<Long>();
            for(NutricionistUsers user : users){
                assignedUsers.add(user.getUserId());
            }
            return assignedUsers;
        }else{
            return Collections.emptyList();
        }
    }
    
    
    /**
     * Method to get contact with the administrators // HTTP verb: POST url: http://localhost:8080/api/user/adminContact/{contact}
     * @param contact object that represents the admin contact to persist
     * @return the modified user
     */
    @PostMapping("/adminContact")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public boolean adminContact(@RequestBody AdminContact contact){
        return adminContactService.createAdminContact(contact);
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
     * Method to create the nutricionist assessment // HTTP verb: POST url: http://localhost:8080/api/user/createNutricionistAssessment
     * @param assessment object that represents the nutricionist assessment to persist
     * @return a boolean that indicates if the nutricionist assessment has been successfully inserted or not
     */
    @PostMapping("/createNutricionistAssessment")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public Boolean createNutricionistAssessment(@RequestBody NutricionistAssessment assessment){
        return nutricionistAssessmentService.createNutricionistAssessment(assessment);
    }
    
    /**
     * Method to create the nutricionist comment // HTTP verb: POST url: http://localhost:8080/api/user/createNutricionistComment
     * @param comment object that represents the nutricionist comment to persist
     * @return a boolean that indicates if the nutricionist v has been successfully inserted or not
     */
    @PostMapping("/createNutricionistComment")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public Boolean createNutricionistComment(@RequestBody NutricionistComment comment){
        return nutricionistCommentService.createComment(comment);
    }
    
    /**
     * Method to create an user association with nutricionist // HTTP verb: POST url: http://localhost:8080/api/user/createUserAssignmentToNutricionist/{data}
     * @param data object that represents the user assignment to nutricionist 
     * @return a boolean that indicates if the assignment has been successfully inserted or not
     */
    @PostMapping("/createUserAssignmentToNutricionist/{data}")
    public Boolean createUserAssignmentToNutricionist(@PathVariable String data){
        NutricionistUsers user = new NutricionistUsers();
        user.setNutricionistId(Long.parseLong(data.split("___")[0]));
        user.setUserId(Long.parseLong(data.split("___")[1]));
        
        return nutricionistUsersService.createUserAssignmentToNutricionist(user);
    }
    
    /**
     * Method to login the user // HTTP verb: POST url: http://localhost:8080/api/user/loginUser
     * @param data string that represents the user to log in
     * @return the logged user
     */
    @PostMapping("/loginUser")
    public User loginUser(@RequestParam String data){
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
     * Method to modify the user password // HTTP verb: PUT url: http://localhost:8080/api/user/updateUserPassword/{pass}
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
     * Method to modify the user email // HTTP verb: PUT url: http://localhost:8080/api/user/updateUserEmail/{mail}
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
     * Method to modify the user alias // HTTP verb: PUT url: http://localhost:8080/api/user/updateUserAlias/{alias}
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
     * Method to modify the nutricionist assessment // HTTP verb: PUT url: http://localhost:8080/api/user/modifyNutricionistAssessment
     * @param assessment object that represents the nutricionist assessment to modify
     * @return a boolean that indicates if the nutricionist assessment has been successfully updated or not
     */
    @PutMapping("/modifyNutricionistAssessment")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateNutricionistAssessment(@RequestBody NutricionistAssessment assessment){
        return nutricionistAssessmentService.modifyAssessment(assessment);
    }

    /**
     * Method to modify the recipe assessment // HTTP verb: PUT url: http://localhost:8080/api/user/modifyRecipeAssessment/{data}
     * @param data string that represents the recipe assessment to modify
     * @return a boolean that indicates if the recipe assessment has been successfully updated or not
     */
    @PutMapping("/modifyRecipeAssessment/{data}")
    public Boolean updateNutricionistAssessment(@PathVariable String data){
        return assessmentService.modifyAssessment(data);
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

    /**
     * Method to delete an user association with nutricionist // HTTP verb: DELETE url: http://localhost:8080/api/user/removeUserAssignmentToNutricionist/{data}
     * @param data object that represents the user assignment to nutricionist 
     * @return a boolean that indicates if the assignment has been successfully deleted or not
     */
    @DeleteMapping("removeUserAssignmentToNutricionist/{data}")
    public boolean removeUserAssignmentToNutricionist(@PathVariable String data){
       NutricionistUsers user = new NutricionistUsers();
       user.setNutricionistId(Long.parseLong(data.split("___")[0]));
       user.setUserId(Long.parseLong(data.split("___")[1]));
       return nutricionistUsersService.removeUserAssignmentToNutricionist(user);
    }

}
