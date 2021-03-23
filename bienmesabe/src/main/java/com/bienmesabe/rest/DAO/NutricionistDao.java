/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO;

import com.bienmesabe.rest.domain.Nutricionist;
import java.util.List;

/**
 *
 * @author RAUL
 */
public interface NutricionistDao {
    public List<Nutricionist> findAllNutricionist();
    public Nutricionist findNutricionistById(Long id);
    public Nutricionist findNutricionistByCP(String cp);
    public List<Nutricionist> findNutricionistByCPRange(String cpMin, String cpMax);
    public Long createNutricionist(Nutricionist nutricionist);
    public void modifyNutricionist(Nutricionist nutricionist);
    public void deleteNutricionistById(Long id);
}
