package org.example.TP1.dao;

import org.example.TP1.entidades.Factura;
import java.util.List;
public interface FacturaDAO {
    Factura findById(int idFactura);
    List<Factura> findAll();
    void create(Factura f);
    void update(Factura f);
    void delete(Factura f);
}
