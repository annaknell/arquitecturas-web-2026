package org.example.TP1.dao;

import org.example.TP1.entidades.Cliente;
import java.util.List;
public interface ClienteDAO {
    Cliente findById(int idCliente);
    List<Cliente> findAll();
    void create(Cliente u);
    void update(Cliente u);
    void delete(int idCliente);
    List<Cliente> getClientesOrderByNroFacturas();

}


