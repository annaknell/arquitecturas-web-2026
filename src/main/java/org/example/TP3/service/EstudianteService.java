package org.example.TP3.service;

import org.example.TP3.entity.Estudiante;
import org.example.TP3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }


    @Transactional
    public boolean altaEstudiante(Estudiante estudiante) {
        Estudiante existente = estudianteRepository.findById(estudiante.getId()).orElse(null);
        if (existente != null) {
            return false;
        }
        estudianteRepository.save(estudiante);
        return true;
    }


    public List<Estudiante> listarEstudiantesOrdenadosPorEdad() {
        return estudianteRepository.findAllOrderByEdad();
    }


    public Estudiante obtenerEstudiantePorLibreta(int LU) {
        return estudianteRepository.findByLU(LU);
    }


    public List<Estudiante> listarEstudiantesPorGenero(String genero) {
        return estudianteRepository.findAllByGenero(genero);
    }


    public List<Estudiante> listarEstudiantesPorCarreraYCiudad(String nombreCarrera, String ciudad) {
        return estudianteRepository.findByCarreraAndCiudad(nombreCarrera,ciudad);
    }
}
