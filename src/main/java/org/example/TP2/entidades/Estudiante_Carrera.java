package org.example.TP2.entidades;
//id,id_estudiante,id_carrera,inscripcion,graduacion,antiguedad

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @Column
    private int inscripcion;
    @Column
    private int graduacion;
    @Column
    private int antiguedad;
}
