package org.example.TP1.repository.mysql;

import org.example.TP1.factory.ConnectionM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager implements ConnectionM {
    private static volatile ConnectionManager instance;
    private static Connection conn;

    private static final String url = "jdbc:mysql://localhost:3306/db_tp1";
    private static final String user = "root";
    private static final String password = "password";

    private ConnectionManager(){
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida correctamente con MySQL.");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos.");
            e.getMessage();
        }
    }

    public static ConnectionManager getInstance(){
        if(instance == null){// 1er chequeo: Evita bloquear si ya existe la instancia.
            synchronized (ConnectionManager.class){
                if(instance == null){
                    instance = new ConnectionManager();
                }
            }
        }

        return instance;
    }

    @Override
    public Connection getConnection() {
        return conn;
    }

    @Override
    public void shutdown() {
        try {
            if(conn != null && !conn.isClosed()){
                conn.close();
                System.out.println("Conexión con MySQL cerrada.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            conn = null;
            synchronized (ConnectionManager.class){
                instance = null;
            }
        }
    }
}