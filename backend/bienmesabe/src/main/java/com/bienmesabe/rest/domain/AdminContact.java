/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class defines the administrators contacts
 * @author RAUL
 * @version 23/05/2021
 */
@Entity
@Table(name="adminContact")
public class AdminContact implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    
    @Column(name="message_subject")
    private String messageSubject;
    
    @Column(name="message")
    private String message;
    
    @Column(name="USER_ID")
    private long userId;
    
    @Column(name="answered")
    private boolean answered;

    
    /**
     * Empty Constructor
     */
    public AdminContact() {
    }
    
    /**
     * AdminContact Constructor with the required parameters
     * @param messageSubject string that represents the message subject
     * @param message string that represents the message
     * @param userId long that represents the id of the user
     * @param answered boolean that indicates if the contact is answered or not
     */
    public AdminContact(String messageSubject, String message, long userId,  boolean answered) {
        this.messageSubject = messageSubject;
        this.message = message;
        this.userId = userId;
        this.answered = answered;
    }
    
    /**
     * Method to recover the id of the adminContact
     * @return a long that represents the id of the adminContact
     */
    public Long getId() {
        return id;
    }

     /**
     * Method to asign the id of the adminContact
     * @param id long that represents the id of the adminContact to asign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to recover the message subject
     * @return a string that represents the message subject
     */
    public String getMessageSubject() {
        return messageSubject;
    }

    /**
     * Method to asign the message subject of the adminContact
     * @param messageSubject string that represents the message subject
     */
    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    /**
     * Method to recover the message 
     * @return a string that represents the message 
     */
    public String getMessage() {
        return message;
    }

    /**
     * Method to asign the message of the adminContact
     * @param message string that represents the message 
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Method to recover the id of the user
     * @return a long that represents the id of the user
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Method to asign the id of the user
     * @param userId a long that represents the id of the user
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }
    
    
    
}
