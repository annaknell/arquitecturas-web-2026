package org.example.TP1.repository.mysql;

import org.example.TP1.dao.FacturaProductoDAO;
import org.example.TP1.entidades.Factura_Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLFacturaProductoDAO implements FacturaProductoDAO {


    private Connection conn;


    public MySQLFacturaProductoDAO(Connection conn) {
        this.conn = conn;
    }


    @Override
    public Factura_Producto findById(int idFactura, int idProducto) {
        String query = "SELECT idFactura, idProducto, cantidad FROM Factura_Producto WHERE idFactura = ? AND idProducto = ?";

        Factura_Producto fp = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);

            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);

            rs = ps.executeQuery();

            if (rs.next()) {
                fp = new Factura_Producto();

                fp.setIdFactura(rs.getInt("idFactura"));
                fp.setIdProducto(rs.getInt("idProducto"));
                fp.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fp;
    }

    @Override
    public List<Factura_Producto> findAll() {
        String query = "SELECT idFactura, idProducto, cantidad FROM Factura_Producto";

        List<Factura_Producto> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Factura_Producto fp = new Factura_Producto();

                fp.setIdFactura(rs.getInt("idFactura"));
                fp.setIdProducto(rs.getInt("idProducto"));
                fp.setCantidad(rs.getInt("cantidad"));

                lista.add(fp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void create(Factura_Producto fp) {
        String query = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);

            ps.setInt(1, fp.getIdFactura());
            ps.setInt(2, fp.getIdProducto());
            ps.setInt(3, fp.getCantidad());

            ps.executeUpdate();
            System.out.println("Producto agregado a la factura exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Factura_Producto fp) {
        String query = "UPDATE Factura_Producto SET cantidad = ? WHERE idFactura = ? AND idProducto = ?";

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);

            ps.setInt(1, fp.getCantidad());
            ps.setInt(2, fp.getIdFactura());
            ps.setInt(3, fp.getIdProducto());

            ps.executeUpdate();
            System.out.println("Cantidad actualizada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Factura_Producto fp) {

        String query = "DELETE FROM Factura_Producto WHERE idFactura = ? AND idProducto = ?";

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);

            ps.setInt(1, fp.getIdFactura());
            ps.setInt(2, fp.getIdProducto());

            ps.executeUpdate();
            System.out.println("Producto eliminado de la factura exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

