package org.example.TP3.repository;

import org.example.TP3.entity.Estudiante_Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Estudiante_CarreraRepository extends JpaRepository<Estudiante_Carrera, Integer> {

    @Query("SELECT ec FROM Estudiante_Carrera ec WHERE ec.estudiante.id = :estudianteId AND ec.carrera.id = :carreraId")
    public Estudiante_Carrera findByEstudianteAndCarrera(@Param("estudianteId") Long estudianteId, @Param("carreraId") Long carreraId);

    @Query("SELECT COUNT(ec) FROM Estudiante_Carrera ec WHERE ec.carrera.id = :carreraId AND ec.inscripcion = :inscripcion")
    public Integer countInscriptosByCarreraIdAndYear(@Param("carreraId") Long carreraId, @Param("inscripcion")int inscripcion);

    @Query("SELECT COUNT(ec) FROM Estudiante_Carrera ec WHERE ec.carrera.id =:id AND ec.graduacion = :graduacion")
    public Integer countEgresadosByCarreraIdAndYear(@Param("id") Long id, @Param("graduacion") int graduacion);
}
