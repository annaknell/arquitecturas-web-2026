package org.example.TP2.repository.impl;

import org.example.TP2.entidades.Estudiante;
import org.example.TP2.repository.EstudianteRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {

    private EntityManager em;

    public EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Estudiante estudiante) {
        em.persist(estudiante);
    }

    @Override
    public Estudiante findByDNI(int DNI) {
        return em.find(Estudiante.class, DNI);
    }

    @Override
    public void delete(Estudiante estudiante) {
        em.remove(estudiante);
    }

    @Override
    public List<Estudiante> findAllOrderByEdad() {
        String query = "SELECT e FROM Estudiante e ORDER BY e.edad ASC";
        return em.createQuery(query, Estudiante.class).getResultList();
    }
}
