package org.example.TP2;
import org.example.TP2.entidades.*;
import javax.persistence.*;
import java.io.IOException;

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
    private static void cargarEstudiantes(EntityManager em, String archivo) throws IOException {

    }
    private static void cargarCarreras(EntityManager em, String archivo) throws IOException{

    }
    private static void cargarEstudianteCarrera(EntityManager em, String archivo) throws IOException{

    }
}
