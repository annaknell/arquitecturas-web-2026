package org.example.TP2.repository.impl;

import org.example.TP2.entidades.Carrera;
import org.example.TP2.repository.CarreraRepository;

import javax.persistence.EntityManager;

public class CarreraRepositoryImpl implements CarreraRepository {

    EntityManager em;

    @Override
    public void save(Carrera carrera) {
        em.getTransaction().begin();    
        em.persist(carrera);
        em.getTransaction().commit();
    }

    @Override
    public Carrera findById(int id) {
       return em.find(Carrera.class, id);

    }

    @Override
    public void delete(Carrera carrera) {
        em.getTransaction().begin();

        if (!em.contains(carrera)) {
            carrera = em.merge(carrera);
        }
        em.remove(carrera);

        em.getTransaction().commit();
    }
}
