package org.example.TP3.service.impl;

import org.example.TP3.entity.Carrera;
import org.example.TP3.entity.Estudiante;
import org.example.TP3.entity.Estudiante_Carrera;
import org.example.TP3.repository.CarreraRepository;
import org.example.TP3.repository.EstudianteRepository;
import org.example.TP3.repository.Estudiante_CarreraRepository;
import org.example.TP3.service.Estudiante_CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;

@Service
public class Estudiante_CarreraServiceImpl implements Estudiante_CarreraService {

    @Autowired
    private Estudiante_CarreraRepository estudiante_carreraRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private CarreraRepository carreraRepository;

    public Estudiante_CarreraServiceImpl(Estudiante_CarreraRepository estudiante_carreraRepository,
                                         EstudianteRepository estudianteRepository,
                                         CarreraRepository carreraRepository) {
        this.estudiante_carreraRepository = estudiante_carreraRepository;
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
    }

    @Override
    @Transactional
    public boolean matricularEstudiante(int estudianteDNI, int carreraId) {
        Estudiante estudiante = estudianteRepository.findByDNI(estudianteDNI);
        Carrera carrera = carreraRepository.findById(carreraId);
        if (estudiante == null || carrera == null) {
            return false;
        }
        if (estudiante_carreraRepository.findByEstudianteAndCarrera(estudianteDNI, carreraId) != null) {
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
