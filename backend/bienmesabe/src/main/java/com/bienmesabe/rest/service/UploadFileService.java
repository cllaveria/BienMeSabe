/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Inteface with the service methods to store the images
 * @author RAUL
 * @version: 24/05/2021
 */
public interface UploadFileService {
    
    /**
     * Method to upload the recipe image
     * @param file object that represents the recipe image
     * @return a string with the path of the recipe
     */
    public String saveImageFile(MultipartFile file);
    
}
