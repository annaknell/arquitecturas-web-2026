package org.example.TP1.repository.mysql;

import org.example.TP1.entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLClienteDAO {
    private Connection conn;

    public MySQLClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertCliente(Cliente cliente) {
        String query = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());

            ps.executeUpdate();

            System.out.println("Cliente insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(Integer id) {
        String query = "DELETE FROM Cliente WHERE idCliente = ?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Cliente find(Integer pk) {
        String query = "SELECT c.idCliente, c.nombre, c.email " +
                "FROM Cliente c " +
                "WHERE c.idCliente = ?";
        Cliente clienteById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk);
            rs = ps.executeQuery();
            if (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");

                clienteById = new Cliente();
                clienteById.setIdCliente(idCliente);
                clienteById.setNombre(nombre);
                clienteById.setEmail(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clienteById;
    }

    public boolean update(Cliente dao) {
        String query = "UPDATE Cliente SET nombre = ?, email = ? WHERE idCliente = ?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, dao.getNombre());
            ps.setString(2, dao.getEmail());
            ps.setInt(3, dao.getIdCliente());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Cliente> selectList() {
        String query = "SELECT c.idCliente, c.nombre, c.email " +
                "FROM Cliente c";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cliente> listado = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            listado = new ArrayList<Cliente>();

            while (rs.next()) {

                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");

                Cliente cliente = new Cliente();
                cliente.setIdCliente(idCliente);
                cliente.setNombre(nombre);
                cliente.setEmail(email);

                listado.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listado;
    }


}
