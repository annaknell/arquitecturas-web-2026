package org.example.TP1.repository.mysql;

import org.example.TP1.dao.ProductoDAO;
import org.example.TP1.entidades.Cliente;
import org.example.TP1.entidades.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLProductoDAO implements ProductoDAO {
    private Connection conn;

    public MySQLProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Producto findById(int idProducto) {
        String query = "SELECT p.idProducto, p.nombre, p.valor " +
                "FROM Producto p " +
                "WHERE p.idProducto = ?";
        Producto ProductoById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                double valor = rs.getDouble("valor");

                ProductoById = new Producto();
                ProductoById.setIdProducto(id);
                ProductoById.setNombre(nombre);
                ProductoById.setValor(valor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ProductoById;
    }


    @Override
    public List<Producto> findAll() {
        String query = "SELECT p.idProducto, p.nombre, p.valor " +
                "FROM Producto p";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> listado = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            listado = new ArrayList<Producto>();
            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                double valor = rs.getDouble("valor");
                Producto producto = new Producto();
                producto.setIdProducto(idProducto);
                producto.setNombre(nombre);
                producto.setValor(valor);
                listado.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listado;

    }

    @Override
    public void create(Producto p) {
        String query = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, p.getIdProducto());
            ps.setString(2, p.getNombre());
            ps.setDouble(3, p.getValor());
            ps.executeUpdate();
            System.out.println("Producto insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Producto p) {
        String query = "UPDATE Producto SET nombre = ?, valor = ? WHERE idProducto = ?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getValor());
            ps.setInt(3, p.getIdProducto());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int idProducto) {
        String query = "DELETE FROM Producto WHERE idProducto = ?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idProducto);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Producto findHighestGrossingProduct() {
        String query = "SELECT p.idProducto, p.nombre, p.valor, SUM(fp.cantidad) AS recaudacion " +
                "FROM Producto p JOIN Factura_Producto fp ON fp.idProducto = p.idProducto" +
                " GROUP BY p.idProducto, p.nombre, p.valor " +
                "ORDER BY recaudacion DESC " +
                "LIMIT 1";
        try(PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()){
            if (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setValor(rs.getDouble("valor"));
                return producto;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
