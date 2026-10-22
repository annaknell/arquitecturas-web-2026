package org.example.TP3.repository;

import org.example.TP3.dto.ReporteProjection;
import org.example.TP3.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    @Query("SELECT ec.carrera FROM Estudiante_Carrera ec GROUP BY ec.carrera ORDER BY COUNT(ec.estudiante) DESC")
    public List<Carrera> findAllconInscriptosOrdernados();

    @Query(value = """
        SELECT carrera, anio,
               SUM(inscriptos) AS inscriptos,
               SUM(egresados) AS egresados
        FROM (
            SELECT c.carrera AS carrera,
                   ec.inscripcion AS anio,
                   COUNT(*) AS inscriptos,
                   0 AS egresados
            FROM estudiante_carrera ec
            JOIN carrera c ON c.id = ec.id_carrera
            GROUP BY c.carrera, ec.inscripcion

            UNION ALL

            SELECT c.carrera AS carrera,
                   ec.graduacion AS anio,
                   0 AS inscriptos,
                   COUNT(*) AS egresados
            FROM estudiante_carrera ec
            JOIN carrera c ON c.id = ec.id_carrera
            WHERE ec.graduacion <> 0
            GROUP BY c.carrera, ec.graduacion
        ) AS reporte
        GROUP BY carrera, anio
        ORDER BY carrera ASC, anio ASC
        """, nativeQuery = true)
    List<ReporteProjection> generarReporteCarreras();
}