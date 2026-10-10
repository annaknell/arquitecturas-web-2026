package org.example.TP3.service;

import org.example.TP3.entity.Estudiante;

import java.util.List;

public interface EstudianteService {

    void altaEstudiante(Estudiante estudiante);

    List<Estudiante> listarEstudiantesOrdenadosPorEdad();

    Estudiante obtenerEstudiantePorLibreta(int LU);

    List<Estudiante> listarEstudiantesPorGenero(String genero);

    List<Estudiante> listarEstudiantesPorCarreraYCiudad(String nombreCarrera, String ciudad);
}
