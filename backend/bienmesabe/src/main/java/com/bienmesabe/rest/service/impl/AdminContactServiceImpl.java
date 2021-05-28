/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.AdminContactDAO;
import com.bienmesabe.rest.domain.AdminContact;
import com.bienmesabe.rest.service.AdminContactService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for implementation of Inteface AdminContactService (service)
 * @author RAUL
 * @version 23/05/2021
 */
@Service
public class AdminContactServiceImpl implements AdminContactService{

    
    /**
     * Bean of the admin contact repository (Interface)
     */
    @Autowired
    private AdminContactDAO adminContactDAO;
    
    /**
     * Implementation of interface method to recover the admin contacts present in the DB
     * @return a list with the admin contacts in the DB
     */
    @Override
    public List<AdminContact> findAllAdminContacts() {
        return adminContactDAO.findAllAdminContacts();
    }

    /**
     * Implementation of interface method to create an admin contact in the table admin_contact of the DB
     * @param adminContact object that represents the admin contact to persist
     * @return a boolean which indcates if the contact has been successfully inserted into the DB or not
     */
    @Override
    public Boolean createAdminContact(AdminContact adminContact) {
        return adminContactDAO.createAdminContact(adminContact);
    }
    
}
