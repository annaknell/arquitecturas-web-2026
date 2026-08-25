package org.example.TP1.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Factura_Producto {
    private int idFactura;
    private int idProducto;
    private int cantidad;
}
