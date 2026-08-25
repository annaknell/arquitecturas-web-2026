package org.example.TP1.repository.mysql;

import org.example.TP1.dao.FacturaProductoDAO;
import org.example.TP1.entidades.Factura_Producto;
import java.sql.Connection;
import java.util.List;

public class MySQLFacturaProductoDAO implements FacturaProductoDAO {
    private Connection conn;

    public MySQLFacturaProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Factura_Producto findById(int idFactura, int idProducto) {
        return null;
    }

    @Override
    public List<Factura_Producto> findAll() {
        return null;
    }

    @Override
    public void create(Factura_Producto fp) {
    }

    @Override
    public void update(Factura_Producto fp) {
    }

    @Override
    public void delete(Factura_Producto fp) {
    }
}
