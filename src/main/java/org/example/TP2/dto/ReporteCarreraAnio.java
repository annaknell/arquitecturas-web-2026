package org.example.TP2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteCarreraAnio {
    private String carrera;
    private int anio;
    private long inscriptos;
    private long egresados;
}