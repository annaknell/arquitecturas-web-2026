package org.example.TP3.controller;

import org.example.TP3.service.Estudiante_CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteCarreraController {

    @Autowired
    private Estudiante_CarreraService estudiante_carreraService;

    // b) matricular un estudiante en una carrera
    //     ->  POST /estudiantes/matricular?dni=...&carreraId=...
    @RequestMapping(value = "/matricular", method = RequestMethod.POST, produces = "application/json")
    public boolean matricularEstudiante(@RequestParam("dni") Long estudianteDNI, @RequestParam("carreraId") Long carreraId){
        return estudiante_carreraService.matricularEstudiante(estudianteDNI, carreraId);
    }
}
