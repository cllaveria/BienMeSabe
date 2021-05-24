/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.AdminContact;
import java.util.List;

/**
 *Inteface with the methods to retrive the DB data of admin contacts
 * @author RAUL
 * @version 23/05/2021
 */
public interface AdminContactDAO {
    
    /**
     * Method to recover the admin contacts
     * @return a list with the admin contacts
     */
    public List<AdminContact> findAllAdminContacts();
    
    /**
     * Method to create an admin contact in the table admin_contact of the DB
     * @param adminContact object that represents the admin contact to persist
     * @return a boolean which indcates if the contact has been successfully inserted into the DB or not
     */
    public Boolean createAdminContact(AdminContact adminContact);
}
