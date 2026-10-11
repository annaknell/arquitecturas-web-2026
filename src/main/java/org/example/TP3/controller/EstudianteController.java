package org.example.TP3.controller;

import org.example.TP3.entity.Estudiante;
import org.example.TP3.service.EstudianteService;

import java.util.List;

public class EstudianteController {
    private EstudianteService estudianteService;
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    public List<Estudiante> getByCarreraAndCiudad(String carrera, String ciudad){
        return estudianteService.listarEstudiantesPorCarreraYCiudad(carrera, ciudad);
    }
}
