/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.DAO.impl;

import com.bienmesabe.rest.DAO.NutricionistDao;
import com.bienmesabe.rest.domain.Nutricionist;
import com.bienmesabe.rest.domain.User;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author RAUL
 */
public class NutricionistDAOImpl implements NutricionistDao{

    @Autowired
    private EntityManager entityManager;
    
    @Override
    public List<Nutricionist> findAllNutricionist() {
        Session currentSession = entityManager.unwrap(Session.class);
        
        Query<Nutricionist> query = currentSession.createQuery("from Nutricionist", Nutricionist.class);
        
        List<Nutricionist> nutricionists = query.getResultList();
        
        return nutricionists;
    }

    @Override
    public Nutricionist findNutricionistById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Nutricionist nutricionist = currentSession.get(Nutricionist.class, id);
        return nutricionist;
    }

    @Override
    public Nutricionist findNutricionistByCP(String cp) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Nutricionist> query = currentSession.createQuery("FROM Nutricionist WHERE companyPostalCode=:companyPostalCode", Nutricionist.class);
        query.setParameter("companyPostalCode", cp);
        Nutricionist nutricionist = query.getSingleResult();
        return nutricionist;
    }

    @Override
    public List<Nutricionist> findNutricionistByCPRange(String cpMin, String cpMax) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Nutricionist> query = currentSession.createQuery("FROM Nutricionist WHERE companyPostalCode>=:minCP and companyPostalCode<= maxCP", Nutricionist.class);
        query.setParameter("minCP", cpMin);
        query.setParameter("maxCP", cpMax);
        List<Nutricionist> nutricionists = query.getResultList();
        return nutricionists;
    }

    @Override
    public Long createNutricionist(Nutricionist nutricionist) {
        Session currentSession = entityManager.unwrap(Session.class);
        Long idGenerado = (Long) currentSession.save(nutricionist); 
        return idGenerado;
    }

    @Override
    public void modifyNutricionist(Nutricionist nutricionist) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(nutricionist);
    }

    @Override
    public void deleteNutricionistById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Nutricionist> query = currentSession.createQuery("delete from Nutricionist where id=:nutricionistId");

        query.setParameter("nutricionistId", id);
        Transaction t = currentSession.beginTransaction();
        query.executeUpdate();
        t.commit();
    }
    
}
