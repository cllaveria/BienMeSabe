/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.service.UploadFileService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Class for implementation of Inteface UploadFileService (service)
 * @author RAUL
 * @version: 24/05/2021
 */
@Service
public class UploadFileServiceImpl implements UploadFileService{

    private String upload_folder = "E:\\\\Google Drive\\\\CURSO CFGS DAW\\\\Practicas\\\\6 - Semestre 6\\\\0 - Proyecto\\\\00_proyecto\\\\Sergio\\\\BienMeSabe\\\\frontend\\\\public\\\\media\\\\";
    
    /**
     * Implementation of interface method to upload the recipe image
     * @param file object that represents the recipe image
     * @return a string with the path of the recipe
     */
    @Override
    public String saveImageFile(MultipartFile file) {
        try{
            if(!file.isEmpty()){
                byte[] bytes = file.getBytes();
                Path path = Paths.get(upload_folder + file.getOriginalFilename());
                Files.write(path,bytes);
                return path.toString();
            }
            return "";
        }catch(Exception e){
            return "";
        }
    }

    
}
