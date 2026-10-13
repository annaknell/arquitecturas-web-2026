package org.example.TP3.controller;

import org.example.TP3.entity.Estudiante;
import org.example.TP3.service.EstudianteService;

import java.util.List;

public class EstudianteController {
    private EstudianteService estudianteService;
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // a) dar de alta un estudiante
    public boolean altaEstudiante(Estudiante estudiante){
        return estudianteService.altaEstudiante(estudiante);
    }

    // c) recuperar todos los estudiantes, ordenados por edad
    public List<Estudiante> getAllOrderByEdad(){
        return estudianteService.listarEstudiantesOrdenadosPorEdad();
    }

    // d) recuperar un estudiante, en base a su número de libreta universitaria.
    public Estudiante getByLibretaUniversitaria(int LU){
        return estudianteService.obtenerEstudiantePorLibreta(LU);
    }

    // e) recuperar todos los estudiantes, en base a su género.
    public List<Estudiante> getByGenero(String genero){
        return estudianteService.listarEstudiantesPorGenero(genero);
    }

    // g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    public List<Estudiante> getByCarreraAndCiudad(String carrera, String ciudad){
        return estudianteService.listarEstudiantesPorCarreraYCiudad(carrera, ciudad);
    }
}
