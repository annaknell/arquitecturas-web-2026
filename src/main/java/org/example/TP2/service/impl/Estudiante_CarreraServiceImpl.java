package org.example.TP2.service.impl;

import org.example.TP2.entidades.Carrera;
import org.example.TP2.entidades.Estudiante;
import org.example.TP2.entidades.Estudiante_Carrera;
import org.example.TP2.repository.CarreraRepository;
import org.example.TP2.repository.EstudianteRepository;
import org.example.TP2.repository.Estudiante_CarreraRepository;
import org.example.TP2.service.Estudiante_CarreraService;

import java.time.Year;

public class Estudiante_CarreraServiceImpl implements Estudiante_CarreraService {

    private Estudiante_CarreraRepository estudiante_carreraRepository;
    private EstudianteRepository estudianteRepository;
    private CarreraRepository carreraRepository;

    public Estudiante_CarreraServiceImpl(Estudiante_CarreraRepository estudiante_carreraRepository,
                                         EstudianteRepository estudianteRepository,
                                         CarreraRepository carreraRepository) {
        this.estudiante_carreraRepository = estudiante_carreraRepository;
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
    }

    @Override
    public void matricularEstudiante(int estudianteDNI, int carreraId) {

        Estudiante estudiante = estudianteRepository.findByDNI(estudianteDNI);
        Carrera carrera = carreraRepository.findById(carreraId);
        Estudiante_Carrera inscripcion = estudiante_carreraRepository.findByEstudianteAndCarrera(estudianteDNI, carreraId);
        if (inscripcion != null) {
            return; // El estudiante ya está inscripto en esta carrera
        }
        if (estudiante != null && carrera != null) {
            Estudiante_Carrera nuevaInscripcion = new Estudiante_Carrera();
            nuevaInscripcion.setEstudiante(estudiante);
            nuevaInscripcion.setCarrera(carrera);
            nuevaInscripcion.setInscripcion(Year.now().getValue());
            nuevaInscripcion.setGraduacion(0);
            nuevaInscripcion.setAntiguedad(0);
            estudiante_carreraRepository.save(nuevaInscripcion);
        }
    }
}
