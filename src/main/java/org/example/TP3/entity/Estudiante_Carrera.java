package org.example.TP3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante_Carrera {
    @EmbeddedId
    private EstudianteCarreraId id = new EstudianteCarreraId();

    @ManyToOne
    @MapsId("idEstudiante")
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("idCarrera")
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @Column
    private int inscripcion;
    @Column
    private int graduacion;
    @Column
    private int antiguedad;
}