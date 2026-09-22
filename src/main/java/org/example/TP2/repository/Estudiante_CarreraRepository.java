package org.example.TP2.repository;

import org.example.TP2.entidades.Carrera;
import org.example.TP2.entidades.Estudiante;
import org.example.TP2.entidades.Estudiante_Carrera;

public interface Estudiante_CarreraRepository {

    void save(Estudiante_Carrera inscripcion);

    Estudiante_Carrera findByEstudianteAndCarrera(int estudianteDNI, int carreraId);
}
