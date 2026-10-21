package org.example.TP3.repository;

import org.example.TP3.entity.Estudiante_Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Estudiante_CarreraRepository extends JpaRepository<Estudiante_Carrera, Integer> {

    @Query("SELECT ec FROM Estudiante_Carrera ec WHERE ec.estudiante.DNI = :estudianteDNI AND ec.carrera.id = :carreraId")
    public Estudiante_Carrera findByEstudianteAndCarrera(int estudianteDNI, int carreraId);

    @Query("SELECT COUNT(ec) FROM Estudiante_Carrera ec WHERE ec.carrera.id = :carreraId AND ec.inscripcion = :inscripcion")
    public Integer countInscriptosByCarreraIdAndYear(int carreraId, int inscripcion);

    @Query("SELECT COUNT(ec) FROM Estudiante_Carrera ec WHERE ec.carrera.id =:id AND ec.graduacion = :graduacion")
    public Integer countEgresadosByCarreraIdAndYear(int id, int graduacion);
}
