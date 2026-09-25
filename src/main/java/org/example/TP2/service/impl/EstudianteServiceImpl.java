package org.example.TP2.service.impl;

import org.example.TP2.entidades.Estudiante;
import org.example.TP2.repository.EstudianteRepository;
import org.example.TP2.service.EstudianteService;

import java.util.List;

public class EstudianteServiceImpl implements EstudianteService {

    private EstudianteRepository estudianteRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public void altaEstudiante(Estudiante estudiante) {
        Estudiante existente = estudianteRepository.findByDNI(estudiante.getDNI());
        if (existente == null) {
            estudianteRepository.save(estudiante);
        }
    }

    @Override
    public List<Estudiante> listarEstudiantesOrdenadosPorEdad() {
        return estudianteRepository.findAllOrderByEdad();
    }

    @Override
    public Estudiante obtenerEstudiantePorLibreta(int LU) { return estudianteRepository.findByLibretaUniversitaria(LU); }

    @Override
    public List<Estudiante> ListarEstudiantesPorGenero(String genero) {
        return estudianteRepository.findAllByGenero(genero);
    }
}
