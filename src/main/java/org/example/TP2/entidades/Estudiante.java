package org.example.TP2.entidades;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    @Id
    private int DNI;
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

    @OneToMany(mappedBy = "estudiante")
    private List<Estudiante_Carrera> carrerasInscriptas;
}
