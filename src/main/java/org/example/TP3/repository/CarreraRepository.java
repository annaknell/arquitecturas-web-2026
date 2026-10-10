package org.example.TP3.repository;

import org.example.TP3.dto.ConteoPorAnio;
import org.example.TP3.entity.Carrera;

import java.util.List;

public interface CarreraRepository {
    void save(Carrera carrera);

    Carrera findById(int id);

    void delete(Carrera carrera);

    List<Carrera> findAllconInscriptosOrdernados();

    List<ConteoPorAnio> contarInscriptosPorCarreraYAnio();

    List<ConteoPorAnio> contarEgresadosPorCarreraYAnio();
}