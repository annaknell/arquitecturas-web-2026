package org.example.TP2.repository.impl;

import org.example.TP2.entidades.Estudiante;
import org.example.TP2.repository.EstudianteRepository;

import javax.persistence.EntityManager;

public class EstudianteRepositoryImpl implements EstudianteRepository {

    private EntityManager em;

    @Override
    public void save(Estudiante estudiante) {

    }

    @Override
    public Estudiante findById(int id) {
        return null;
    }

    @Override
    public void delete(Estudiante estudiante) {

    }
}
