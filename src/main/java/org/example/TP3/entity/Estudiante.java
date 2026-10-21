package org.example.TP3.entity;

import lombok.*;
import org.example.TP3.entity.Estudiante_Carrera;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    @Id
    @Column(name = "dni")
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private String ciudad;
    @Column
    private int LU;

    @JsonIgnore
    @OneToMany(mappedBy = "estudiante")
    private List<Estudiante_Carrera> carrerasInscriptas;
}

