package org.example.TP2.service.impl;

import org.example.TP2.entidades.Carrera;
import org.example.TP2.repository.CarreraRepository;
import org.example.TP2.service.CarreraService;

import java.util.List;
import org.example.TP2.dto.ConteoPorAnio; import org.example.TP2.dto.ReporteCarreraAnio; import java.util.ArrayList; import java.util.Map; import java.util.TreeMap;

public class CarreraServiceImpl implements CarreraService {

    private CarreraRepository carreraRepository;

    public CarreraServiceImpl(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
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
