package org.example.TP3.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class EstudianteCarreraId implements Serializable {

    @Column(name = "id_estudiante")
    private Long idEstudiante;

    @Column(name = "id_carrera")
    private Long idCarrera;

}