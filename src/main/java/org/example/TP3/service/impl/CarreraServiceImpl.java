package org.example.TP3.service.impl;

import org.example.TP3.dto.ConteoPorAnio;
import org.example.TP3.dto.ReporteCarreraAnio;
import org.example.TP3.entity.Carrera;
import org.example.TP3.repository.CarreraRepository;
import org.example.TP3.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    public CarreraServiceImpl(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    @Override
    @Transactional
    public boolean altaCarrera(Carrera carrera) {
        carreraRepository.save(carrera);
        return true;
    }

    @Override
    public List<Carrera> listarCarreras() {
        return carreraRepository.findAll();
    }

    @Override
    public List<Carrera> listarCarrerasConInscriptosOrdenados() {
        return carreraRepository.findAllconInscriptosOrdernados();
    }

    @Override
    public List<ReporteCarreraAnio> generarReporteCarreras() {
        List<ConteoPorAnio> inscriptos = carreraRepository.contarInscriptosPorCarreraYAnio();
        List<ConteoPorAnio> egresados = carreraRepository.contarEgresadosPorCarreraYAnio();

        Map<String, TreeMap<Integer, ReporteCarreraAnio>> reporte = new TreeMap<>();

        for (ConteoPorAnio c : inscriptos) {
            reporte
                    .computeIfAbsent(c.getCarrera(), k -> new TreeMap<>())
                    .computeIfAbsent(c.getAnio(), a -> new ReporteCarreraAnio(c.getCarrera(), a, 0, 0))
                    .setInscriptos(c.getCantidad());
        }

        for (ConteoPorAnio c : egresados) {
            reporte
                    .computeIfAbsent(c.getCarrera(), k -> new TreeMap<>())
                    .computeIfAbsent(c.getAnio(), a -> new ReporteCarreraAnio(c.getCarrera(), a, 0, 0))
                    .setEgresados(c.getCantidad());
        }

        List<ReporteCarreraAnio> resultado = new ArrayList<>();
        for (TreeMap<Integer, ReporteCarreraAnio> anios : reporte.values()) {
            resultado.addAll(anios.values());
        }

        return resultado;
    }
}