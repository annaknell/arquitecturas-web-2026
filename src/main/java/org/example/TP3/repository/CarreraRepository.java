package org.example.TP3.repository;

import org.example.TP3.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    @Query("SELECT ec.carrera FROM Estudiante_Carrera ec GROUP BY ec.carrera ORDER BY COUNT(ec.estudiante) DESC")
    List<Carrera> findAllconInscriptosOrdernados();

}