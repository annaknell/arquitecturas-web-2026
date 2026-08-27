package org.example.TP1.utils;

import org.example.TP1.dao.ProductoDAO;
import org.example.TP1.entidades.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;

public class ProductoCsvLoader {

    private final ProductoDAO productoDAO;

    public ProductoCsvLoader(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public void cargar(String filePath) {

        try (FileReader reader = new FileReader(filePath, StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .parse(reader)) {

            for (CSVRecord row : parser) {

                int idProducto = Integer.parseInt(row.get("idProducto"));
                String nombre = row.get("nombre");
                double valor = Double.parseDouble(row.get("valor"));

                Producto producto = new Producto();

                producto.setIdProducto(idProducto);
                producto.setNombre(nombre);
                producto.setValor(valor);

                productoDAO.create(producto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}