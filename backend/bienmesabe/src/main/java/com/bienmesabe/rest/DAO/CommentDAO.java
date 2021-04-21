/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.Comment;

/**
 * Inteface with the methods to retrive the DB data of comments
 * @author RAUL RAMOS CENDRERO
 * @version 20/04/2021
 */
public interface CommentDAO {
    /**
     * Method to create a comment in the table comments of the DB
     * @param comment object that represents the comment to persist
     * @return a long with the id of the persisted comment
     */
    public Long createComment(Comment comment);
}