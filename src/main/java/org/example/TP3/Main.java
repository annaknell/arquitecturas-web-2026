package org.example.TP3;

import org.example.TP3.dto.ReporteCarreraAnio;
import org.example.TP3.entity.Carrera;
import org.example.TP3.entity.Estudiante;
import org.example.TP3.repository.CarreraRepository;
import org.example.TP3.repository.EstudianteRepository;
import org.example.TP3.repository.Estudiante_CarreraRepository;
import org.example.TP3.repository.impl.CarreraRepositoryImpl;
import org.example.TP3.repository.impl.EstudianteRepositoryImpl;
import org.example.TP3.repository.impl.Estudiante_CarreraRepositoryImpl;
import org.example.TP3.service.CarreraService;
import org.example.TP3.service.EstudianteService;
import org.example.TP3.service.Estudiante_CarreraService;
import org.example.TP3.service.impl.CarreraServiceImpl;
import org.example.TP3.service.impl.EstudianteServiceImpl;
import org.example.TP3.service.impl.Estudiante_CarreraServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence_tp2");
        EntityManager em = emf.createEntityManager();

        try {
            cargarDatosBase(em);

            EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl(em);
            CarreraRepository carreraRepository = new CarreraRepositoryImpl(em);
            Estudiante_CarreraRepository inscripcionRepository = new Estudiante_CarreraRepositoryImpl(em);

            EstudianteService estudianteService = new EstudianteServiceImpl(estudianteRepository);
            CarreraService carreraService = new CarreraServiceImpl(carreraRepository);
            Estudiante_CarreraService estudianteCarreraService = new Estudiante_CarreraServiceImpl(
                    inscripcionRepository,
                    estudianteRepository,
                    carreraRepository
            );

            // Punto a) Dar de alta un estudiante
            System.out.println("\n=== a) Dar de alta un estudiante ===");
            em.getTransaction().begin();
            Estudiante nuevoEstudiante = new Estudiante();
            nuevoEstudiante.setDNI(20000001);
            nuevoEstudiante.setNombre("Ana");
            nuevoEstudiante.setApellido("López");
            nuevoEstudiante.setEdad(22);
            nuevoEstudiante.setGenero("Femenino");
            nuevoEstudiante.setCiudad("Tandil");
            nuevoEstudiante.setLU(1001);
            boolean alta = estudianteService.altaEstudiante(nuevoEstudiante);
            em.getTransaction().commit();
            System.out.println("Alta exitosa: " + alta + " | " + nuevoEstudiante.getNombre() + " " + nuevoEstudiante.getApellido());

            // Punto b) Matricular un estudiante en una carrera
            System.out.println("\n=== b) Matricular estudiante en carrera ===");
            em.getTransaction().begin();
            boolean matriculado = estudianteCarreraService.matricularEstudiante(20000001, 1);
            em.getTransaction().commit();
            System.out.println("Matriculado: " + matriculado);

            // Punto c) Recuperar todos los estudiantes ordenados por edad
            System.out.println("\n=== c) Recuperar estudiantes ordenados por edad ===");
            List<Estudiante> estudiantesPorEdad = estudianteService.listarEstudiantesOrdenadosPorEdad();
            for (Estudiante e : estudiantesPorEdad) {
                System.out.println(e.getNombre() + " " + e.getApellido() + " | edad: " + e.getEdad() + " | DNI: " + e.getDNI());
            }

            // Punto d) Recuperar un estudiante por número de libreta universitaria
            System.out.println("\n=== d) Recuperar estudiante por libreta universitaria ===");
            Estudiante porLU = estudianteService.obtenerEstudiantePorLibreta(1001);
            System.out.println(porLU == null ? "No existe" : porLU.getNombre() + " " + porLU.getApellido() + " | LU: " + porLU.getLU());

            // Punto e) Recuperar todos los estudiantes según su género
            System.out.println("\n=== e) Recuperar estudiantes por género ===");
            List<Estudiante> estudiantesPorGenero = estudianteService.listarEstudiantesPorGenero("Femenino");
            for (Estudiante e : estudiantesPorGenero) {
                System.out.println(e.getNombre() + " " + e.getApellido() + " | género: " + e.getGenero());
            }

            // Punto f) Recuperar carreras con estudiantes inscriptos y ordenarlas por cantidad
            System.out.println("\n=== f) Carreras con estudiantes inscriptos, ordenadas por cantidad ===");
            List<Carrera> carreras = carreraService.listarCarrerasConInscriptosOrdenados();
            for (Carrera c : carreras) {
                System.out.println(c.getCarrera() + " | id: " + c.getId());
            }

            // Punto g) Recuperar estudiantes de una carrera filtrados por ciudad de residencia
            System.out.println("\n=== g) Estudiantes de una carrera filtrados por ciudad ===");
            List<Estudiante> estudiantesCarreraCiudad = estudianteService.listarEstudiantesPorCarreraYCiudad("Ingeniería en Sistemas", "Olavarría");
            for (Estudiante e : estudiantesCarreraCiudad) {
                System.out.println(e.getNombre() + " " + e.getApellido() + " | ciudad: " + e.getCiudad());
            }

            // Punto h) Generar reporte de carreras con inscriptos y egresados por año
            System.out.println("\n=== h) Reporte de carreras por año ===");
            List<ReporteCarreraAnio> reporte = carreraService.generarReporteCarreras();
            for (ReporteCarreraAnio r : reporte) {
                System.out.println(r.getCarrera() + " | año: " + r.getAnio() + " | inscriptos: " + r.getInscriptos() + " | egresados: " + r.getEgresados());
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void cargarDatosBase(EntityManager em) {
        em.getTransaction().begin();

        Estudiante e1 = new Estudiante();
        e1.setDNI(12345678);
        e1.setNombre("Juan");
        e1.setApellido("Pérez");
        e1.setEdad(20);
        e1.setGenero("Masculino");
        e1.setCiudad("Olavarría");
        e1.setLU(2001);
        em.persist(e1);

        Estudiante e2 = new Estudiante();
        e2.setDNI(87654321);
        e2.setNombre("Maria");
        e2.setApellido("García");
        e2.setEdad(23);
        e2.setGenero("Femenino");
        e2.setCiudad("Olavarría");
        e2.setLU(2002);
        em.persist(e2);

        Carrera c1 = new Carrera();
        c1.setCarrera("Ingeniería en Sistemas");
        c1.setDuracion(5);
        em.persist(c1);

        Carrera c2 = new Carrera();
        c2.setCarrera("Licenciatura en Administración");
        c2.setDuracion(4);
        em.persist(c2);

        em.getTransaction().commit();
    }
}
