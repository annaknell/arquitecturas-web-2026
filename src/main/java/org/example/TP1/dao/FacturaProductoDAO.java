package org.example.TP1.dao;

import org.example.TP1.entidades.Factura_Producto;
import java.util.List;
public interface FacturaProductoDAO {
    Factura_Producto findById(int idFactura,int idProducto);
    List<Factura_Producto> findAll();
    void create(Factura_Producto fp);
    void update(Factura_Producto fp);
    void delete(Factura_Producto fp);

}
