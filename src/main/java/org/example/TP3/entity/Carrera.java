package org.example.TP3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.TP2.entidades.Estudiante_Carrera;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String carrera;
    @Column
    private int duracion;

    @OneToMany(mappedBy = "carrera")
    private List<Estudiante_Carrera> estudiantesInscriptos;
}
