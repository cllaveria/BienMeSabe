/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;



import com.bienmesabe.rest.domain.Comment;
import com.bienmesabe.rest.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller for Comments // url: http://localhost:8080/api/comments
 * @author RAUL RAMOS CENDRERO
 * @version 20/04/2021
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/comment")
public class CommentController {
    
    /**
     * Bean of the comment service (Interface)
     */
    @Autowired
    private CommentService commentService;
    
    
    @GetMapping("/getComments")
    public List<Comment> getComments(){
        return commentService.findAllComments();
    }
    
    @GetMapping("/getCommentsByRecipeId/{id}")
    public List<Comment> getCommentsById(@PathVariable String id){
        return commentService.findAllCommentsOfRecipe(Long.parseLong(id));
    }

    /**
     * Method to create the comment // HTTP verb: POST url: http://localhost:8080/api/comment/addComment/{recipeId}/{comment}/{userId}
     * @param recipeId string that represents the id of the recipe
     * @param commentValue string taht represents the comment
     * @param userId string that represents the id of the user
     * @return the created user
     */
    @PostMapping("/addComment/{info}")
    public Comment addComment(@RequestParam String commentInfo){
       Comment comment = new Comment();
       comment.setRecipeId(Long.parseLong(commentInfo.split("___")[0]));
       comment.setCommentValue(commentInfo.split("___")[1]);
       comment.setUserId(Long.parseLong(commentInfo.split("___")[2]));
       Long id = commentService.createComment(comment);
       if(id == 0L)
           return null;
       comment.setId(id);
       return comment;
    }
}
