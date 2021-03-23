/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.controller;

import com.bienmesabe.rest.domain.Nutricionist;
import com.bienmesabe.rest.service.NutricionistService;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author RAUL
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/nutricionist")
public class NutricionistController {
    
    @Autowired
    private NutricionistService nutricionistService;
    
    @GetMapping("/getNutricionists")
    public List<Nutricionist> getUsers(){
        return nutricionistService.findAllNutricionist();
    }
    
    @GetMapping("/getNutricionistById/{ID}")
    public Nutricionist findUserById(@PathVariable Long id){
        Nutricionist nutricionist = nutricionistService.findNutricionistById(id);
        if(nutricionist == null){
            throw new RuntimeException("Nutricionist id not found - " + id);
        }
        return nutricionist;
    }
    @GetMapping("/findNutricionistByCP/{cp}")
    public Nutricionist findNutricionistByCP(@PathVariable String cp){
        Nutricionist nutricionist = nutricionistService.findNutricionistByCP(cp);
        if(nutricionist == null){
            throw new RuntimeException("Nutricionist id not found - " + cp);
        }
        return nutricionist;
    }
    @GetMapping("/findNutricionistByCPRange/{cpMin/{cpMax}")
    public List<Nutricionist> findNutricionistByCPRange(@PathVariable String cpMin, @PathVariable String cpMax){
        List<Nutricionist> nutricionists = nutricionistService.findNutricionistByCPRange(cpMin, cpMax);
        if(nutricionists == null){
            throw new RuntimeException("Nutricionist id not found - ");
        }
        return nutricionists;
    }
    
    @PostMapping("/addNutricionist")
    public Nutricionist addUser(Nutricionist nutricionist){
        nutricionist.setId(0L);
        nutricionistService.createNutricionist(nutricionist);
        return nutricionist;
    }
    
    @PutMapping("/modifyUser")
//    @RequestMapping(value = "/update/")
    public Nutricionist updateUser(Nutricionist nutricionist){
        nutricionistService.modifyNutricionist(nutricionist);
        return nutricionist;
    }
    
    @DeleteMapping("deleteUserById/{id}")
    public String deleteUserById(@PathVariable Long id){
        Nutricionist nutricionist = nutricionistService.findNutricionistById(id);
        if(nutricionist == null) {
            throw new RuntimeException("User id not found -"+id);
        }
        nutricionistService.deleteNutricionistById(id);
        return "Deleted nutricionist id - "+id;
    }
}
