package org.example.TP2.repository;

import org.example.TP2.entidades.Estudiante;

import java.util.List;

public interface EstudianteRepository {
    void save(Estudiante estudiante);

    Estudiante findByDNI(int DNI);

    void delete(Estudiante estudiante);

    List<Estudiante> findAllOrderByEdad();

    Estudiante findByLibretaUniversitaria(int LU);
}
