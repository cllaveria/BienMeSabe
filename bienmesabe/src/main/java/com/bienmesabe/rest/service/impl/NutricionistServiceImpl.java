/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.service.impl;

import com.bienmesabe.rest.DAO.NutricionistDao;
import com.bienmesabe.rest.domain.Nutricionist;
import com.bienmesabe.rest.service.NutricionistService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author RAUL
 */
public class NutricionistServiceImpl implements NutricionistService {

    @Autowired
    private NutricionistDao nutricionistDAO;
    @Override
    public List<Nutricionist> findAllNutricionist() {
        List<Nutricionist> listNutricionists = nutricionistDAO.findAllNutricionist();
        return listNutricionists;
    }

    @Override
    public Nutricionist findNutricionistById(Long id) {
        Nutricionist nutricionist = nutricionistDAO.findNutricionistById(id);
        return nutricionist;
    }

    @Override
    public Nutricionist findNutricionistByCP(String cp) {
        Nutricionist nutricionist = nutricionistDAO.findNutricionistByCP(cp);
        return nutricionist;
    }

    @Override
    public List<Nutricionist> findNutricionistByCPRange(String cpMin, String cpMax) {
        List<Nutricionist> listNutricionists = nutricionistDAO.findNutricionistByCPRange(cpMin, cpMax);
        return listNutricionists;
    }

    @Override
    public Long createNutricionist(Nutricionist nutricionist) {
        Long id = nutricionistDAO.createNutricionist(nutricionist);
        return id;
    }

    @Override
    public void modifyNutricionist(Nutricionist nutricionist) {
        nutricionistDAO.modifyNutricionist(nutricionist);
    }

    @Override
    public void deleteNutricionistById(Long id) {
        nutricionistDAO.deleteNutricionistById(id);
    }
    
}
