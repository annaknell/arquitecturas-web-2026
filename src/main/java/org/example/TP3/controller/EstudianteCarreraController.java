package org.example.TP3.controller;

import org.example.TP3.service.Estudiante_CarreraService;

public class EstudianteCarreraController {
    private Estudiante_CarreraService estudiante_carreraService;

    public EstudianteCarreraController(Estudiante_CarreraService estudiante_carreraService) {
        this.estudiante_carreraService = estudiante_carreraService;
    }

    // b) matricular un estudiante en una carrera
    public boolean matricularEstudiante(int estudianteDNI, int carreraId){
        return estudiante_carreraService.matricularEstudiante(estudianteDNI, carreraId);
    }
}
