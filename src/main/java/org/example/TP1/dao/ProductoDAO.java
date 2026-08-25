package org.example.TP1.dao;

import org.example.TP1.entidades.Producto;
import java.util.List;
public interface ProductoDAO {
    Producto findById(int idProducto);
    List<Producto> findAll();
    void create(Producto p);
    void update(Producto p);
    void delete(int idProducto);


}
