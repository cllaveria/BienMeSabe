/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.NutricionistDAO;
import com.bienmesabe.rest.domain.Nutricionist;
import com.bienmesabe.rest.domain.NutricionistAssessment;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for implementation of Inteface NutricionistDAO (repository)
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Repository
public class NutricionistDAOImpl implements NutricionistDAO{

    /**
     * Bean for the entity manager
     */
    @Autowired
    private EntityManager entityManager;
    
    /**
     * Implementation of interface method to recover the nutricionists present in the DB
     * @return a list with the nutricionists present in the DB
     */
    @Override
    @Transactional
    public List<Nutricionist> findAllNutricionist() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Nutricionist> query = currentSession.createQuery("from Nutricionist", Nutricionist.class);
        List<Nutricionist> nutricionists = query.getResultList();
        return nutricionists;
    }

    /**
     * Implementation of interface method to recover the nutricionists present in the DB by id
     * @param id long that represents the id of the nutricionist to search
     * @return the nutricionist in the DB by id
     */
    @Override
    @Transactional
    public Nutricionist findNutricionistById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Nutricionist nutricionist = currentSession.get(Nutricionist.class, id);
        return nutricionist;
    }

    /**
     * Implementation of interface method to recover the nutricionists present in the DB by cp
     * @param cp string that represents the postal code of the nutricionists to search
     * @return the list of the nutricionist filtered by postal code
     */
    @Override
    @Transactional
    public List<Nutricionist> findNutricionistByCP(String cp) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Nutricionist> query = currentSession.createQuery("FROM Nutricionist WHERE companyPostalCode=:companyPostalCode", Nutricionist.class);
        query.setParameter("companyPostalCode", cp);
        List<Nutricionist> nutricionist = query.getResultList();
        return nutricionist;
    }

    /**
     * Implementation of interface method to recover the nutricionist present in the DB by postal code range
     * @param cpMin string that represents the mimimum postal code of the nutricionists to search
     * @param cpMax string that represents the maximum postal code of the nutricionists to search
     * @return a list with the nutricionists filtered by postal code range
     */
    @Override
    @Transactional
    public List<Nutricionist> findNutricionistByCPRange(String cpMin, String cpMax) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Nutricionist> query = currentSession.createQuery("FROM Nutricionist WHERE CONVERT(companyPostalCode,unsigned) BETWEEN :minCP AND :maxCP ORDER BY CONVERT(companyPostalCode,unsigned) DESC", Nutricionist.class);
        query.setParameter("minCP", Integer.parseInt(cpMin));
        query.setParameter("maxCP", Integer.parseInt(cpMax));
        List<Nutricionist> nutricionists = query.getResultList();
        return nutricionists;
    }

    /**
     * Implementation of interface method to recover the assessment of the nutricionist in the DB
     * @param id long that represents the id of the nutricionist
     * @return an integer that represents the value of the assessment of the nutricionist
     */
    @Override
    @Transactional
    public int getNutricionistAssessment(Long id){
        float assessment = 0;
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NutricionistAssessment> query = currentSession.createQuery("FROM NutricionistAssessment WHERE nutricionistId=:id", NutricionistAssessment.class);
        query.setParameter("id", id);
        List<NutricionistAssessment> assessments = query.getResultList();
        for(NutricionistAssessment na : assessments){
            assessment+= na.getAssessmentValue();
        }
        
        return Math.round(assessment/assessments.size());
    }
    
    /**
     * Implementation of interface method to create an nutricionist in the table nutricionists of the DB
     * @param nutricionist object that represents the nutricionist to persist
     * @return a long with the id of the persisted nutricionist
     */
    @Override
    public Long createNutricionist(Nutricionist nutricionist) {
        Session currentSession = entityManager.unwrap(Session.class);
        
        Query<Nutricionist> query = currentSession.createQuery("FROM Nutricionist WHERE companyName=:name and companyDirection=:direction and companyPostalCode=:postalCode", Nutricionist.class);
        query.setParameter("name", nutricionist.getName());
        query.setParameter("direction", nutricionist.getCompanyDirection());
        query.setParameter("postalCode", nutricionist.getCompanyPostalCode());
        Nutricionist nutricionistInDB = new Nutricionist();
        try{
             nutricionistInDB= query.getSingleResult();
        }catch(Exception e){
            nutricionistInDB = null;
        }
        if(nutricionistInDB==null){
            try{
                currentSession.save(nutricionist); 
                return 1L;
            }catch(Exception e){
                return 0L;
            }
            
        }
        return 0L;
    }

    /**
     * Implementation of interface method to modify an nutricionist in the table nutricionists of the DB
     * @param nutricionistId long that represents the id of the nutricionist to modify
     * @param companyName string that represents the company name of the nutricionist to asign
     * @param companyDirection string that represents the company direction of the nutricionist to asign
     * @param companyPostalCode string that represents the company postal code of the nutricionist to asign
     * @param companyCity string that represents the company city of the nutricionist to asign
     * @param companyPhone string that represents the company phone of the nutricionist to asign
     * @return a boolean that represents if the nutricionist information has been successfully updated or not
     */
    @Override
    @Transactional
    public Boolean modifyNutricionist(Long nutricionistId, String companyName, String companyDirection, String companyPostalCode, String companyCity, String companyPhone) {
        Session currentSession = entityManager.unwrap(Session.class);
        try{
            String updates = "update Nutricionist set ";
            
            if(companyName != null && !companyName.isEmpty()) updates +=  "companyName=:newCompanyName, ";
            if(companyDirection != null && !companyDirection.isEmpty()) updates +=  "companyDirection=:newCompanyDirection, ";
            if(companyPostalCode != null && !companyPostalCode.isEmpty()) updates +=  "companyPostalCode=:newCompanyPostalCode, ";
            if(companyCity != null && !companyCity.isEmpty()) updates +=  "companyCity=:newCompanyCity, ";
            if(companyPhone != null && !companyPhone.isEmpty()) updates +=  "companyPhone=:newCompanyPhone, ";
            
            updates = updates.substring(0, updates.length() -2);
            updates += " WHERE id=:nutricionistId";
            Query<Nutricionist> query = currentSession.createQuery(updates);
            query.setParameter("nutricionistId", nutricionistId);
            
            if(companyName != null && !companyName.isEmpty()) query.setParameter("newCompanyName", companyName);
            if(companyDirection != null && !companyDirection.isEmpty()) query.setParameter("newCompanyDirection", companyDirection);
            if(companyPostalCode != null && !companyPostalCode.isEmpty()) query.setParameter("newCompanyPostalCode", companyPostalCode);
            if(companyCity != null && !companyCity.isEmpty()) query.setParameter("newCompanyCity", companyCity);
            if(companyPhone != null && !companyPhone.isEmpty()) query.setParameter("newCompanyPhone", companyPhone);
            
            query.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * Implementation of interface method to delete an nutricionist in the table nutricionists of the DB by id
     * @param id long with the id of the nutricionist to delete
     */
    @Override
    @Transactional
    public void deleteNutricionistById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Nutricionist> query = currentSession.createQuery("delete from Nutricionist where id=:nutricionistId");
        query.setParameter("nutricionistId", id);
        query.executeUpdate();
    }
    
}
