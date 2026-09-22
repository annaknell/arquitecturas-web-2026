package org.example.TP2.repository.impl;

import org.example.TP2.entidades.Carrera;
import org.example.TP2.entidades.Estudiante;
import org.example.TP2.entidades.Estudiante_Carrera;
import org.example.TP2.repository.Estudiante_CarreraRepository;

import javax.persistence.EntityManager;

public class Estudiante_CarreraRepositoryImpl implements Estudiante_CarreraRepository {

    private EntityManager em;

    public Estudiante_CarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Estudiante_Carrera inscripcion) {
        em.persist(inscripcion);
    }

    @Override
    public Estudiante_Carrera findByEstudianteAndCarrera(int estudianteDNI, int carreraId) {
        String query = "SELECT ec FROM Estudiante_Carrera WHERE estudianteDNI = :estudianteDNI AND carreraId = :carreraId";
        return em.createQuery(query, Estudiante_Carrera.class)
                .setParameter("estudianteDNI", estudianteDNI)
                .setParameter("carreraId", carreraId)
                .getSingleResult();
    }


}
