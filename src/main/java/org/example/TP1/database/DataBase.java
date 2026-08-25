package org.example.TP1.database;
import java.sql.*;
import java.sql.Connection;

public class DataBase {
    public static void main(String[] args){
        String url = "jdbc:postgres://localhost:5432/db_tp1";
        String user = "root";
        String password = "password";

        try{
            Connection conn = DriverManager.getConnection(url, user, password);
            createTables(conn);

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void createTables(Connection conn) throws SQLException{
        String cliente = "CREATE TABLE IF NOT EXISTS Cliente(" +
                "idCliente INT," +
                "nombre VARCHAR(50) IS NOT NULL," +
                "email VARCHAR(50) IS NOT NULL," +
                "PRIMARY KEY(idCliente))";

        String factura = "CREATE TABLE IF NOT EXISTS Factura(" +
                "idFactura INT," +
                "idCliente INT NULL," +
                "PRIMARY KEY(idFactura)," +
                "FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente))";

        String producto = "CREATE TABLE IF NOT EXISTS Producto(" +
                "idProducto INT," +
                "nombre VARCHAR(50) IS NOT NULL," +
                "valor FLOAT IS NOT NULL," +
                "PRIMARY KEY(idProducto))";

        String factura_producto = "CREATE TABLE IF NOT EXISTS Factura_Producto(" +
                "idFactura INT ," +
                "idProducto INT," +
                "cantidad INT IS NOT NULL," +
                "PRIMARY KEY(idFactura, idProducto))";

        try(Statement stm = conn.createStatement()){
            stm.execute(cliente);
            stm.execute(factura);
            stm.execute(producto);
            stm.execute(factura_producto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
