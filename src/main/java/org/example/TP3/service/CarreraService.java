package org.example.TP3.service;

import org.example.TP3.dto.ConteoPorAnio;
import org.example.TP3.dto.ReporteCarreraAnio;
import org.example.TP3.entity.Carrera;
import org.example.TP3.repository.CarreraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CarreraService {

    private CarreraRepository carreraRepository;

    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
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
        List<ConteoPorAnio> inscriptos = carreraRepository.contarInscriptosPorCarreraYAnio();
        List<ConteoPorAnio> egresados = carreraRepository.contarEgresadosPorCarreraYAnio();

        Map<String, TreeMap<Integer, ReporteCarreraAnio>> reporte = new TreeMap<>();

        for (ConteoPorAnio conteo : inscriptos) {
            reporte.computeIfAbsent(conteo.getCarrera(), clave -> new TreeMap<>()).computeIfAbsent(conteo.getAnio(),
                            anio -> new ReporteCarreraAnio(conteo.getCarrera(), anio, 0, 0)).setInscriptos(conteo.getCantidad());
        }
        for (ConteoPorAnio conteo : egresados) {
            reporte.computeIfAbsent(conteo.getCarrera(), clave -> new TreeMap<>()).computeIfAbsent(conteo.getAnio(),
                            anio -> new ReporteCarreraAnio(conteo.getCarrera(), anio, 0, 0)).setEgresados(conteo.getCantidad());
        }
        List<ReporteCarreraAnio> resultado = new ArrayList<>();

        for (TreeMap<Integer, ReporteCarreraAnio> reportesPorAnio : reporte.values()) {
            resultado.addAll(reportesPorAnio.values());
        }
        return resultado;
    }
}