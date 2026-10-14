package org.example.TP3.service.impl;

import org.example.TP3.entity.Estudiante;
import org.example.TP3.repository.EstudianteRepository;
import org.example.TP3.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    @Transactional
    public boolean altaEstudiante(Estudiante estudiante) {
        Estudiante existente = estudianteRepository.findByDNI(estudiante.getDNI());
        if (existente != null) {
            return false;
        }
        estudianteRepository.save(estudiante);
        return true;
    }

    @Override
    public List<Estudiante> listarEstudiantesOrdenadosPorEdad() {
        return estudianteRepository.findAllOrderByEdad();
    }

    @Override
    public Estudiante obtenerEstudiantePorLibreta(int LU) {
        return estudianteRepository.findByLibretaUniversitaria(LU);
    }

    @Override
    public List<Estudiante> listarEstudiantesPorGenero(String genero) {
        return estudianteRepository.findAllByGenero(genero);
    }

    @Override
    public List<Estudiante> listarEstudiantesPorCarreraYCiudad(String nombreCarrera, String ciudad) {
        return estudianteRepository.findByCarreraAndCiudad(nombreCarrera,ciudad);
    }
}
