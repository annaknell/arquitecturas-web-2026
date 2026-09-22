package org.example.TP2.repository;

import org.example.TP2.entidades.Carrera;

public interface CarreraRepository {
    void save(Carrera carrera);

    Carrera findById(int id);

    void delete(Carrera carrera);
}
