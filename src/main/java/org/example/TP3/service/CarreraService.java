package org.example.TP3.service;


import org.example.TP3.dto.ReporteCarreraAnio;
import org.example.TP3.entity.Carrera;
import org.example.TP3.repository.CarreraRepository;
import org.example.TP3.repository.Estudiante_CarreraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarreraService {

    private CarreraRepository carreraRepository;
    private Estudiante_CarreraRepository estudiante_carreraRepository;

    public CarreraService(CarreraRepository carreraRepository, Estudiante_CarreraRepository estudiante_carreraRepository) {
        this.carreraRepository = carreraRepository;
        this.estudiante_carreraRepository = estudiante_carreraRepository;
    }

    @Transactional
    public boolean altaCarrera(Carrera carrera) {
        carreraRepository.save(carrera);
        return true;
    }

    public List<Carrera> listarCarreras() {
        return carreraRepository.findAll();
    }

    public List<Carrera> listarCarrerasConInscriptosOrdenados() {
        return carreraRepository.findAllconInscriptosOrdernados();
    }

    public ReporteCarreraAnio generarReporteCarreraAnio(int carreraId, int anio){

        Carrera carrera = carreraRepository.findById(carreraId);
        if (carrera == null) {
            return null;
        }
        int inscriptos = estudiante_carreraRepository.countInscriptosByCarreraIdAndYear(carreraId, anio);
        int egresados = estudiante_carreraRepository.countEgresadosByCarreraIdAndYear(carreraId, anio);
        ReporteCarreraAnio reporte = new ReporteCarreraAnio();
        reporte.setCarrera(carrera.getCarrera());
        reporte.setAnio(anio);
        reporte.setInscriptos(inscriptos);
        reporte.setEgresados(egresados);
        return reporte;
    }
}