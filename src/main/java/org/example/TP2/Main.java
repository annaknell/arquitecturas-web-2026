package org.example.TP2;
import org.example.TP2.entidades.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence_tp2");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        try {
            cargarEstudiantes(em, "src/main/java/org/example/TP2/csv/estudiantes.csv");
            cargarCarreras(em, "src/main/java/org/example/TP2/csv/carreras.csv");

            cargarEstudianteCarrera(em, "src/main/java/org/example/TP2/csv/estudianteCarrera.csv");

            em.getTransaction().commit();
            System.out.println("Datos cargados exitosamente.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    // CSV: DNI,nombre,apellido,edad,genero,ciudad,LU
    private static void cargarEstudiantes(EntityManager em, String archivo) throws IOException {
        try (CSVParser parser = CSVParser.parse(Paths.get(archivo), StandardCharsets.UTF_8,
                CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).get())) {

            for (CSVRecord fila : parser) {
                Estudiante estudiante = new Estudiante();

                estudiante.setDNI(Integer.parseInt(fila.get("DNI")));
                estudiante.setNombre(fila.get("nombre"));
                estudiante.setApellido(fila.get("apellido"));
                estudiante.setEdad(Integer.parseInt(fila.get("edad")));
                estudiante.setGenero(fila.get("genero"));
                estudiante.setCiudad(fila.get("ciudad"));
                estudiante.setLU(Integer.parseInt(fila.get("LU")));

                em.persist(estudiante);
            }
        }
    }

    // CSV: id_carrera,carrera,duracion
    private static void cargarCarreras(EntityManager em, String archivo) throws IOException{
        try (CSVParser parser = CSVParser.parse(Paths.get(archivo), StandardCharsets.UTF_8,
                CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).get())) {

            for (CSVRecord fila : parser) {
                Carrera carrera = new Carrera();

                carrera.setCarrera(fila.get("carrera"));
                carrera.setDuracion(Integer.parseInt(fila.get("duracion")));

                em.persist(carrera);
            }
        }
    }

    // CSV: id,id_estudiante,id_carrera,inscripcion,graduacion,antiguedad
    private static void cargarEstudianteCarrera(EntityManager em, String archivo) throws IOException{
        try (CSVParser parser = CSVParser.parse(Paths.get(archivo), StandardCharsets.UTF_8,
                CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).get())) {

            for (CSVRecord fila : parser) {

                int dni = Integer.parseInt(fila.get("id_estudiante"));
                int idCarrera = Integer.parseInt(fila.get("id_carrera"));

                Estudiante estudiante = em.find(Estudiante.class, dni);
                Carrera carrera = em.find(Carrera.class, idCarrera);

                if (estudiante == null || carrera == null) {
                    System.out.println("Inscripcion " + fila.get("id") + " cancelada: no existe el estudiante "
                            + dni + " o la carrera " + idCarrera);
                    continue;
                }

                Estudiante_Carrera inscripcion = new Estudiante_Carrera();

                inscripcion.setEstudiante(estudiante);
                inscripcion.setCarrera(carrera);
                inscripcion.setInscripcion(Integer.parseInt(fila.get("inscripcion")));
                inscripcion.setGraduacion(Integer.parseInt(fila.get("graduacion")));
                inscripcion.setAntiguedad(Integer.parseInt(fila.get("antiguedad")));

                em.persist(inscripcion);
            }
        }
    }
}
