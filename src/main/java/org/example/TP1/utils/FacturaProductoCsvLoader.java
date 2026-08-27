package org.example.TP1.utils;

import org.example.TP1.dao.FacturaProductoDAO;
import org.example.TP1.entidades.Factura_Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;

public class FacturaProductoCsvLoader {

    private final FacturaProductoDAO facturaProductoDAO;

    public FacturaProductoCsvLoader(FacturaProductoDAO facturaProductoDAO) {
        this.facturaProductoDAO = facturaProductoDAO;
    }

    public void cargar(String filePath) {

        try (FileReader reader = new FileReader(filePath, StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .parse(reader)) {

            for (CSVRecord row : parser) {

                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idProducto = Integer.parseInt(row.get("idProducto"));
                int cantidad = Integer.parseInt(row.get("cantidad"));

                Factura_Producto facturaProducto = new Factura_Producto();

                facturaProducto.setIdFactura(idFactura);
                facturaProducto.setIdProducto(idProducto);
                facturaProducto.setCantidad(cantidad);

                facturaProductoDAO.create(facturaProducto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}