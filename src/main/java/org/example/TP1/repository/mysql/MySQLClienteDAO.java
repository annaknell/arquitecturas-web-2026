package org.example.TP1.repository.mysql;

import org.example.TP1.dao.ClienteDAO;
import org.example.TP1.entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLClienteDAO implements ClienteDAO {
    private Connection conn;

    public MySQLClienteDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(Cliente cliente) {
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

    @Override
    public void delete(int idCliente) {
        String query = "DELETE FROM Cliente WHERE idCliente = ?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idCliente);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente findById(int idCliente) {
        String query = "SELECT c.idCliente, c.nombre, c.email " +
                "FROM Cliente c " +
                "WHERE c.idCliente = ?";
        Cliente clienteById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");

                clienteById = new Cliente();
                clienteById.setIdCliente(id);
                clienteById.setNombre(nombre);
                clienteById.setEmail(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clienteById;
    }

    @Override
    public void update(Cliente cliente) {
        String query = "UPDATE Cliente SET nombre = ?, email = ? WHERE idCliente = ?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setInt(3, cliente.getIdCliente());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cliente> findAll() {
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
