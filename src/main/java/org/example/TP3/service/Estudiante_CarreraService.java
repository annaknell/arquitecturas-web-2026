package org.example.TP3.service;

import org.example.TP3.dto.ReporteCarreraAnio;
import org.example.TP3.entity.Carrera;
import org.example.TP3.entity.Estudiante;
import org.example.TP3.entity.Estudiante_Carrera;
import org.example.TP3.repository.CarreraRepository;
import org.example.TP3.repository.EstudianteRepository;
import org.example.TP3.repository.Estudiante_CarreraRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;

@Service
public class Estudiante_CarreraService {

    private Estudiante_CarreraRepository estudiante_carreraRepository;

    private EstudianteRepository estudianteRepository;

    private CarreraRepository carreraRepository;

    public Estudiante_CarreraService(Estudiante_CarreraRepository estudiante_carreraRepository,
                                     EstudianteRepository estudianteRepository,
                                     CarreraRepository carreraRepository) {
        this.estudiante_carreraRepository = estudiante_carreraRepository;
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
    }

    
    @Transactional
    public boolean matricularEstudiante(Long estudianteId, Long carreraId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId).orElseThrow(() -> new RuntimeException("no existe estudiante con ese id."));
        Carrera carrera = carreraRepository.findById(carreraId).orElseThrow(() -> new RuntimeException("no se encontró carrera con ese id."));

        if (estudiante_carreraRepository.findByEstudianteAndCarrera(estudianteId, carreraId) != null) {
            return false;
        }
        Estudiante_Carrera nuevaInscripcion = new Estudiante_Carrera();
        nuevaInscripcion.setEstudiante(estudiante);
        nuevaInscripcion.setCarrera(carrera);
        nuevaInscripcion.setInscripcion(Year.now().getValue());
        nuevaInscripcion.setGraduacion(0);
        nuevaInscripcion.setAntiguedad(0);
        estudiante_carreraRepository.save(nuevaInscripcion);
        return true;
    }

}
