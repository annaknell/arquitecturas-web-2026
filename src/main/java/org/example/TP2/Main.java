package org.example.TP2;
import org.example.TP2.entidades.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.TP2.repository.CarreraRepository;
import org.example.TP2.repository.EstudianteRepository;
import org.example.TP2.repository.Estudiante_CarreraRepository;
import org.example.TP2.repository.impl.CarreraRepositoryImpl;
import org.example.TP2.repository.impl.EstudianteRepositoryImpl;
import org.example.TP2.repository.impl.Estudiante_CarreraRepositoryImpl;
import org.example.TP2.service.CarreraService;
import org.example.TP2.service.EstudianteService;
import org.example.TP2.service.Estudiante_CarreraService;
import org.example.TP2.service.impl.CarreraServiceImpl;
import org.example.TP2.service.impl.EstudianteServiceImpl;
import org.example.TP2.service.impl.Estudiante_CarreraServiceImpl;

import javax.persistence.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

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
        }

        try{
            EstudianteRepository estudianteRepo = new EstudianteRepositoryImpl(em);
            CarreraRepository carreraRepo = new CarreraRepositoryImpl(em);
            Estudiante_CarreraRepository inscripcionRepo = new Estudiante_CarreraRepositoryImpl(em);

            EstudianteService estudianteService = new EstudianteServiceImpl(estudianteRepo);
            CarreraService carreraService = new CarreraServiceImpl(carreraRepo);

            Estudiante_CarreraService inscripcionService = new Estudiante_CarreraServiceImpl(
                    inscripcionRepo,
                    estudianteRepo,
                    carreraRepo
            );


            // a) dar de alta un estudiante
            em.getTransaction().begin();
            Estudiante nuevoEstudiante = new Estudiante();

            nuevoEstudiante.setDNI(35840722);
            nuevoEstudiante.setNombre("Marcelo");
            nuevoEstudiante.setApellido("Gutierréz");
            nuevoEstudiante.setEdad(35);
            nuevoEstudiante.setGenero("Male");
            nuevoEstudiante.setCiudad("Tandil");
            nuevoEstudiante.setLU(9287);

            estudianteService.altaEstudiante(nuevoEstudiante);
            System.out.println(nuevoEstudiante.getApellido());
            em.getTransaction().commit();

            // b) matricular un estudiante en una carrera
            em.getTransaction().begin();
            int dni = 45608327;
            int idCarrera = 1;
            inscripcionService.matricularEstudiante(dni, idCarrera);
            em.getTransaction().commit();

            // c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple (en nuestro caso, edad)
            List<Estudiante> ageFilteredEstudiantes = estudianteService.listarEstudiantesOrdenadosPorEdad();

            // d) recuperar un estudiante, en base a su número de libreta universitaria
            int LU = 9845;
            Estudiante LUfilteredEstudiante = estudianteService.obtenerEstudiantePorLibreta(LU);

            // e) recuperar todos los estudiantes, en base a su género.
            String genero = "Female";
            List<Estudiante> genderFilterEstudiantes = estudianteService.listarEstudiantesPorGenero(genero);

            // f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
            List<Carrera> carrerarOrdenadas = carreraService.listarCarrerasConInscriptosOrdenados();

            // g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia
            String nombreCarrera = "Ingeniería en Sistemas";
            String ciudad = "Olavarría";
            //List<Estudiante> estudiantesFiltrados = estudianteService.listarEstudiantesPorCarreraYCiudad(nombreCarrera, ciudad);

        }catch (Exception e) {
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
