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

    public List<ReporteCarreraAnio> generarReporteCarreras() {
        return estudiante_carreraRepository.generarReporteCarreras()
                .stream()
                .map(reporte -> new ReporteCarreraAnio(
                        reporte.getCarrera(),
                        reporte.getAnio(),
                        reporte.getInscriptos(),
                        reporte.getEgresados()
                ))
                .toList();
    }
}