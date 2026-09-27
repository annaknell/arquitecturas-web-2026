package org.example.TP2.service;

import org.example.TP2.entidades.Carrera;

import java.util.List;
import org.example.TP2.dto.ReporteCarreraAnio;
public interface CarreraService {

    List<Carrera> listarCarrerasConInscriptosOrdenados();

    List<ReporteCarreraAnio> generarReporteCarreras();
}
