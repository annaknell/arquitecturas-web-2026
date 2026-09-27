package org.example.TP2.repository;

import org.example.TP2.entidades.Carrera;

import java.util.List;
import org.example.TP2.dto.ConteoPorAnio;

public interface CarreraRepository {
    void save(Carrera carrera);

    Carrera findById(int id);

    void delete(Carrera carrera);

    List<Carrera> findAllconInscriptosOrdernados();

    List<ConteoPorAnio> contarInscriptosPorCarreraYAnio();

    List<ConteoPorAnio> contarEgresadosPorCarreraYAnio();
}
