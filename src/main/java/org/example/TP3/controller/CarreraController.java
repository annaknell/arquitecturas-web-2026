package org.example.TP3.controller;

import org.example.TP3.entity.Carrera;
import org.example.TP3.service.CarreraService;
import org.example.TP3.dto.ReporteCarreraAnio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    // alta de una carrera   ->  POST /carreras
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public boolean altaCarrera(@RequestBody Carrera carrera){
        return carreraService.altaCarrera(carrera);
    }

    // listar todas las carreras, sin filtrar   ->  GET /carreras/todas
    @RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
    public List<Carrera> getAllCarreras(){
        return carreraService.listarCarreras();
    }

    // f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    //     ->  GET /carreras
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Carrera> getOrderByCantEstudiantesInscriptos(){
        return carreraService.listarCarrerasConInscriptosOrdenados();
    }

    // h) generar un reporte de las carreras, que para cada carrera incluya información de los
    //inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
    //presentar los años de manera cronológica.
    //     ->  GET /carreras/reporte
    @RequestMapping(value = "/reporte", method = RequestMethod.GET, produces = "application/json")
    public List<ReporteCarreraAnio> getReporteCarreras(){
        return carreraService.generarReporteCarreras();
    }
}
