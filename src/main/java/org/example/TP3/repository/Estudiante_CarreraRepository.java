package org.example.TP3.repository;

import org.example.TP3.entity.Estudiante_Carrera;

public interface Estudiante_CarreraRepository {

    void save(Estudiante_Carrera inscripcion);

    Estudiante_Carrera findByEstudianteAndCarrera(int estudianteDNI, int carreraId);
}
