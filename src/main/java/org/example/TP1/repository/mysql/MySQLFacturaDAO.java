package org.example.TP1.repository.mysql;

import org.example.TP1.dao.FacturaDAO;
import org.example.TP1.entidades.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLFacturaDAO implements FacturaDAO {
    private Connection conn;

    public MySQLFacturaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Factura findById(int idFactura) {
        String query = "SELECT f.idFactura, f.idCliente " +
                "FROM Factura f " +
                "WHERE f.idFactura = ?";
        Factura facturaById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idFactura);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("idFactura");
                int idCliente = rs.getInt("idCliente");

                facturaById = new Factura();
                facturaById.setIdFactura(id);
                facturaById.setIdCliente(idCliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return facturaById;
    }


    @Override
    public List<Factura> findAll() {
        String query = "SELECT f.idFactura, f.idCliente " +
                "FROM Factura f";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Factura> listado = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            listado = new ArrayList<Factura>();
            while (rs.next()) {
                int idFactura = rs.getInt("idFactura");
                int idCliente = rs.getInt("idCliente");
                Factura factura = new Factura();
                factura.setIdFactura(idFactura);
                factura.setIdCliente(idCliente);

                listado.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listado;
    }

    @Override
    public void create(Factura factura) {
        String query = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());
            ps.executeUpdate();
            System.out.println("Factura insertada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Factura factura) {
        String query = "UPDATE Factura SET idCliente = ? WHERE idFactura = ?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, factura.getIdCliente());
            ps.setInt(2, factura.getIdFactura());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Factura factura) {
        String query = "DELETE FROM Factura WHERE idFactura = ?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, factura.getIdFactura());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
