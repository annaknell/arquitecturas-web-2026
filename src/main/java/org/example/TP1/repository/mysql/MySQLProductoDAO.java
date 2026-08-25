package org.example.TP1.repository.mysql;

import org.example.TP1.dao.ProductoDAO;
import org.example.TP1.entidades.Producto;
import java.sql.Connection;
import java.util.List;

public class MySQLProductoDAO implements ProductoDAO {
    private Connection conn;

    public MySQLProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Producto findById(int idProducto) {
        return null;
    }

    @Override
    public List<Producto> findAll() {
        return null;
    }

    @Override
    public void create(Producto p) {
    }

    @Override
    public void update(Producto p) {
    }

    @Override
    public void delete(int idProducto) {
    }
}
