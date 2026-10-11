package org.example.TP3.controller;

import org.example.TP3.entity.Carrera;
import org.example.TP3.service.CarreraService;
import org.example.TP3.dto.ReporteCarreraAnio;

import java.util.List;

public class CarreraController {
    private CarreraService carreraService;
    public CarreraController(CarreraService carreraService){
        this.carreraService = carreraService;
    }

    // f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    public List<Carrera> getOrderByCantEstudiantesInscriptos(){
        return carreraService.listarCarrerasConInscriptosOrdenados();
    }

    // h) generar un reporte de las carreras, que para cada carrera incluya información de los
    //inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
    //presentar los años de manera cronológica.


}
