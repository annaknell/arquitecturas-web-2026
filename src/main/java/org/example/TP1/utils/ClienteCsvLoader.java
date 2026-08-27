package org.example.TP1.utils;

import org.example.TP1.dao.ClienteDAO;
import org.example.TP1.entidades.Cliente;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;

public class ClienteCsvLoader {

    private final ClienteDAO clienteDAO;

    public ClienteCsvLoader(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void cargar(String filePath) {

        try (FileReader reader = new FileReader(filePath, StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .parse(reader)) {

            for (CSVRecord row : parser) {

                int idCliente = Integer.parseInt(row.get("idCliente"));
                String nombre = row.get("nombre");
                String email = row.get("email");

                Cliente cliente = new Cliente();

                cliente.setIdCliente(idCliente);
                cliente.setNombre(nombre);
                cliente.setEmail(email);

                clienteDAO.create(cliente);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}