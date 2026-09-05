package org.example.TP1;

import org.example.TP1.dao.ClienteDAO;
import org.example.TP1.dao.FacturaDAO;
import org.example.TP1.dao.FacturaProductoDAO;
import org.example.TP1.dao.ProductoDAO;
import org.example.TP1.entidades.Cliente;
import org.example.TP1.entidades.Producto;
import org.example.TP1.factory.DAOFactory;
import org.example.TP1.factory.DBType;
import org.example.TP1.utils.ClienteCsvLoader;
import org.example.TP1.utils.FacturaCsvLoader;
import org.example.TP1.utils.FacturaProductoCsvLoader;
import org.example.TP1.utils.ProductoCsvLoader;

import java.util.List;

public class Main {
    private static final String RUTA_CLIENTES = "src/main/java/org/example/TP1/csv/clientes.csv";
    private static final String RUTA_PRODUCTOS = "src/main/java/org/example/TP1/csv/productos.csv";
    private static final String RUTA_FACTURAS = "src/main/java/org/example/TP1/csv/facturas.csv";
    private static final String RUTA_FACTURAS_PRODUCTOS = "src/main/java/org/example/TP1/csv/facturas-productos.csv";

    public static void main(String[] args) {
        DAOFactory factory = DAOFactory.getInstance(DBType.MYSQL);
        try {

            ClienteDAO clienteDAO = factory.createUsuarioDAO();
            ProductoDAO productoDAO = factory.createProductoDAO();
            FacturaDAO facturaDAO = factory.createPedidoDAO();
            FacturaProductoDAO facturaProductoDAO = factory.createDetallePedidoDAO();

            new ClienteCsvLoader(clienteDAO).cargar(RUTA_CLIENTES);
            new ProductoCsvLoader(productoDAO).cargar(RUTA_PRODUCTOS);
            new FacturaCsvLoader(facturaDAO).cargar(RUTA_FACTURAS);
            new FacturaProductoCsvLoader(facturaProductoDAO).cargar(RUTA_FACTURAS_PRODUCTOS);

            Producto masVendido = productoDAO.findHighestGrossingProduct();
            if (masVendido != null) {
                System.out.println("ID: " + masVendido.getIdProducto());
                System.out.println("Nombre: " + masVendido.getNombre());
                System.out.println("Valor unitario: " + masVendido.getValor());
            } else {
                System.out.println("No se encontraron datos de facturación.");
            }

        } finally {
            factory.shutdown();
        }
    }



}