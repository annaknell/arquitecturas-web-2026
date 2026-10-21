package org.example.TP3.repository;

import org.example.TP3.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    @Query("SELECT e FROM Estudiante e ORDER BY e.edad ASC")
    List<Estudiante> findAllOrderByEdad();

    Estudiante findByLibretaUniversitaria(int LU);

    List<Estudiante> findAllByGenero(String genero);

    @Query("SELECT ec.estudiante FROM Estudiante_Carrera ec " +
            "WHERE ec.carrera.carrera = :carrera " +
            "AND ec.estudiante.ciudad = :ciudad")
    List<Estudiante> findByCarreraAndCiudad(@Param("carrera") String carrera, @Param("ciudad") String ciudad);
}

