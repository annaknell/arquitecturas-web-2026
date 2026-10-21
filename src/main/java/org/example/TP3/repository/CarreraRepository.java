package org.example.TP3.repository;

import org.example.TP3.dto.ConteoPorAnio;
import org.example.TP3.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    @Query("SELECT ec.carrera FROM Estudiante_Carrera ec GROUP BY ec.carrera ORDER BY COUNT(ec.estudiante) DESC")
    public List<Carrera> findAllconInscriptosOrdernados();

    @Query("SELECT NEW org.example.TP3.dto.ConteoPorAnio(ec.carrera.carrera, ec.inscripcion, COUNT(ec))" +
            " FROM Estudiante_Carrera ec " +
            "GROUP BY ec.carrera.carrera, ec.inscripcion " +
            "ORDER BY ec.carrera.carrera, ec.inscripcion")
    public List<ConteoPorAnio> contarInscriptosPorCarreraYAnio();

    @Query("SELECT NEW org.example.TP3.dto.ConteoPorAnio(ec.carrera.carrera, ec.graduacion, COUNT(ec)) " +
            "FROM Estudiante_Carrera ec " +
            "WHERE ec.graduacion <> 0 " +
            "GROUP BY ec.carrera.carrera, ec.graduacion " +
            "ORDER BY ec.carrera.carrera, ec.graduacion")
    public List<ConteoPorAnio> contarEgresadosPorCarreraYAnio();
}