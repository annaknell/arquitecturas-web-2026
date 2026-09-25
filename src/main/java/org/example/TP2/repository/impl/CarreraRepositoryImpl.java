package org.example.TP2.repository.impl;

import org.example.TP2.entidades.Carrera;
import org.example.TP2.repository.CarreraRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class CarreraRepositoryImpl implements CarreraRepository {
    EntityManager em;

    public CarreraRepositoryImpl(EntityManager em){
        this.em = em;
    }

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

    @Override
    public List<Carrera> findAllconInscriptosOrdernados() {
        String query = "SELECT ec.carrera FROM Estudiante_Carrera ec " +
                        "GROUP BY ec.carrera " +
                        "ORDER BY COUNT(ec.estudiante) DESC";
        return em.createQuery(query, Carrera.class).getResultList();
    }
}
