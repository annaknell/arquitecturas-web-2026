package org.example.TP2.repository;

import org.example.TP2.entidades.Estudiante;

public interface EstudianteRepository {
    void save(Estudiante estudiante);

    Estudiante findById(int id);

    void delete(Estudiante estudiante);
}
