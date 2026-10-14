package org.example.TP3.service;

import org.example.TP3.dto.ReporteCarreraAnio;
import org.example.TP3.entity.Carrera;

import java.util.List;

public interface CarreraService {

    boolean altaCarrera(Carrera carrera);

    List<Carrera> listarCarrerasConInscriptosOrdenados();

    List<ReporteCarreraAnio> generarReporteCarreras();
}
