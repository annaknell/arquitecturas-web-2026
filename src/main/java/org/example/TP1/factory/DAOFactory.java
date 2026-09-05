package org.example.TP1.factory;

import org.example.TP1.dao.ClienteDAO;
import org.example.TP1.dao.FacturaDAO;
import org.example.TP1.dao.FacturaProductoDAO;
import org.example.TP1.dao.ProductoDAO;
import org.example.TP1.repository.mysql.MySQLDAOFactory;
import java.sql.Connection;


public abstract class DAOFactory {

    private static volatile DAOFactory instance;

    public static DAOFactory getInstance(DBType type) { // Implementado en caso de extender a mas de una DB
        if (instance == null) {
            synchronized (DAOFactory.class) {
                if (instance == null) {
                    switch (type) {
                        case MYSQL:
                            instance = new MySQLDAOFactory();
                            break;

                        //case DERBY:
                        //    instance = new DerbyDAOFactory();
                        //    break;

                        //    case POSTGRES:
                        //     instance = new PostgresAOFactory();
                        //     break;
                        default:
                            throw new IllegalArgumentException("DBType no soportado: " + type);
                    }
                }
            }
        }
        return instance;
    }

    public static DAOFactory getInstance() {
        String v = System.getProperty("db.type", "MYSQL");
        DBType type = DBType.valueOf(v.toUpperCase());
        return getInstance(type);
    }

    public abstract ClienteDAO createClienteDAO(); // Correccion del nombre
    public abstract ProductoDAO createProductoDAO();
    public abstract FacturaDAO createFacturaDAO(); // Correccion del nombre
    public abstract FacturaProductoDAO createFacturaProductoDAO(); // Correccion del nombre

    protected abstract Connection getConnection();

    public final void shutdown() {
        doShutdown();
        synchronized (DAOFactory.class) {
            instance = null;
        }
    }
    protected abstract void doShutdown();
}
