package org.example.TP1.repository.mysql;

import org.example.TP1.dao.ClienteDAO;
import org.example.TP1.dao.FacturaDAO;
import org.example.TP1.dao.FacturaProductoDAO;
import org.example.TP1.dao.ProductoDAO;
import org.example.TP1.factory.DAOFactory;

import java.sql.Connection;

public class MySQLDAOFactory extends DAOFactory {

    /**
     * Toda la dependencia con MySQL (driver, URL, usuario, password) queda
     * encerrada en MySQLConnectionManager y solo esta clase lo conoce.
     */
    @Override
    protected Connection getConnection() {
        return ConnectionManager.getInstance().getConnection();
    }

    @Override
    protected void doShutdown() {
        ConnectionManager.getInstance().shutdown();
    }
    @Override
    public ClienteDAO createClienteDAO() {
        return new MySQLClienteDAO(getConnection()) ;
    } // Correccion del nombre

    @Override
    public ProductoDAO createProductoDAO() {
        return new MySQLProductoDAO(getConnection());
    }

    @Override
    public FacturaDAO createFacturaDAO() {return new MySQLFacturaDAO(getConnection()); } // Correccion del nombre

    @Override
    public FacturaProductoDAO createFacturaProductoDAO() {return new MySQLFacturaProductoDAO(getConnection()); } // Correccion del nombre



}
