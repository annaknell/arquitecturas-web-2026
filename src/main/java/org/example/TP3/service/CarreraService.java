package org.example.TP3.service;

import org.example.TP3.dto.ReporteCarreraAnio;
import org.example.TP3.entity.Carrera;

import java.util.List;

public interface CarreraService {

    List<Carrera> listarCarrerasConInscriptosOrdenados();

    List<ReporteCarreraAnio> generarReporteCarreras();
}
