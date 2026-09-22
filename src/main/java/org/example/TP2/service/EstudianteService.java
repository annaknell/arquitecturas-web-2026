package org.example.TP2.service;

import org.example.TP2.entidades.Estudiante;

import java.util.List;

public interface EstudianteService {

    void altaEstudiante(Estudiante estudiante);

    List<Estudiante> listarEstudiantesOrdenadosPorEdad();
}
