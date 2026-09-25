package org.example.TP2.repository.impl;

import org.example.TP2.entidades.Estudiante;
import org.example.TP2.repository.EstudianteRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {

    private EntityManager em;

    public EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Estudiante estudiante) {
        em.persist(estudiante);
    }

    @Override
    public Estudiante findByDNI(int DNI) {
        return em.find(Estudiante.class, DNI);
    }

    @Override
    public void delete(Estudiante estudiante) {
        em.remove(estudiante);
    }

    @Override
    public List<Estudiante> findAllOrderByEdad() {
        String query = "SELECT e FROM Estudiante e ORDER BY e.edad ASC";
        return em.createQuery(query, Estudiante.class).getResultList();
    }

    @Override
    public Estudiante findByLibretaUniversitaria(int LU) {
        String query = "SELECT e FROM Estudiante e " +
                        "WHERE e.LU = :LU";

            return em.createQuery(query, Estudiante.class)
                .setParameter("LU", LU)
                .getResultStream()
                .findFirst()
                .orElse(null);

    }

    @Override
    public List<Estudiante> findAllByGenero(String genero) {
        String query = "SELECT e FROM Estudiante e " +
                        "WHERE e.genero = :genero";

        return em.createQuery(query, Estudiante.class)
            .setParameter("genero", genero)
            .getResultList();
    }

    @Override
    public List<Estudiante> findByCarreraAndCiudad(String nombreCarrera, String ciudad) {
        String query = "SELECT ec.estudiante FROM Estudiante_Carrera ec " +
                        "WHERE ec.carrera.carrera = :carrera " +
                        "AND ec.estudiante.ciudad = :ciudad";
        return em.createQuery(query, Estudiante.class)
                .setParameter("carrera", nombreCarrera)
                .setParameter("ciudad", ciudad)
                .getResultList();
    }
}
