package org.example.TP3.repository;

import org.example.TP3.dto.ReporteProjection;
import org.example.TP3.entity.EstudianteCarreraId;
import org.example.TP3.entity.Estudiante_Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Estudiante_CarreraRepository extends JpaRepository<Estudiante_Carrera, EstudianteCarreraId> {

    @Query("SELECT ec FROM Estudiante_Carrera ec WHERE ec.estudiante.id = :estudianteId AND ec.carrera.id = :carreraId")
    Estudiante_Carrera findByEstudianteAndCarrera(@Param("estudianteId") Long estudianteId, @Param("carreraId") Long carreraId);

    @Query("""
        SELECT COUNT(ec) FROM Estudiante_Carrera ec
        WHERE ec.carrera.id = :carreraId
          AND ec.inscripcion = :inscripcion
        """)
    Long countInscriptosByCarreraIdAndYear(@Param("carreraId") Long carreraId, @Param("inscripcion") int inscripcion);

    @Query("""
        SELECT COUNT(ec) FROM Estudiante_Carrera ec
        WHERE ec.carrera.id = :carreraId
          AND ec.graduacion = :graduacion
        """)
    Long countEgresadosByCarreraIdAndYear(@Param("carreraId") Long carreraId, @Param("graduacion") int graduacion);

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
        ORDER BY carrera, anio
        """, nativeQuery = true)
    List<ReporteProjection> generarReporteCarreras();
}
