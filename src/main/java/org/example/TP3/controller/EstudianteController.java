package org.example.TP3.controller;

import org.example.TP3.entity.Estudiante;
import org.example.TP3.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // a) dar de alta un estudiante   ->  POST /estudiantes
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public boolean altaEstudiante(@RequestBody Estudiante estudiante){
        return estudianteService.altaEstudiante(estudiante);
    }

    // c) recuperar todos los estudiantes, ordenados por edad   ->  GET /estudiantes
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Estudiante> getAllOrderByEdad(){
        return estudianteService.listarEstudiantesOrdenadosPorEdad();
    }

    // d) recuperar un estudiante, en base a su número de libreta universitaria.   ->  GET /estudiantes/libreta/{lu}
    @RequestMapping(value = "/libreta/{lu}", method = RequestMethod.GET, produces = "application/json")
    public Estudiante getByLibretaUniversitaria(@PathVariable("lu") int LU){
        return estudianteService.obtenerEstudiantePorLibreta(LU);
    }

    // e) recuperar todos los estudiantes, en base a su género.   ->  GET /estudiantes/genero/{genero}
    @RequestMapping(value = "/genero/{genero}", method = RequestMethod.GET, produces = "application/json")
    public List<Estudiante> getByGenero(@PathVariable("genero") String genero){
        return estudianteService.listarEstudiantesPorGenero(genero);
    }

    // g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    //     ->  GET /estudiantes/por-carrera?carrera=...&ciudad=...
    @RequestMapping(value = "/por-carrera", method = RequestMethod.GET, produces = "application/json")
    public List<Estudiante> getByCarreraAndCiudad(@RequestParam("carrera") String carrera, @RequestParam("ciudad") String ciudad){
        return estudianteService.listarEstudiantesPorCarreraYCiudad(carrera, ciudad);
    }
}
