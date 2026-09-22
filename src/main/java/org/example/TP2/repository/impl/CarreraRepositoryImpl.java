package org.example.TP2.repository.impl;

import org.example.TP2.entidades.Carrera;
import org.example.TP2.repository.CarreraRepository;

import javax.persistence.EntityManager;

public class CarreraRepositoryImpl implements CarreraRepository {

    EntityManager em;

    @Override
    public void save(Carrera carrera) {

    }

    @Override
    public Carrera findById(int id) {
        return null;
    }

    @Override
    public void delete(Carrera carrera) {

    }
}
