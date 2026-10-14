package org.example.TP3.repository.impl;

import org.example.TP3.dto.ConteoPorAnio;
import org.example.TP3.entity.Carrera;
import org.example.TP3.repository.CarreraRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CarreraRepositoryImpl implements CarreraRepository {
    EntityManager em;

    public CarreraRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public void save(Carrera carrera) {
        em.persist(carrera);
    }

    @Override
    public Carrera findById(int id) {
        return em.find(Carrera.class, id);

    }

    @Override
    public void delete(Carrera carrera) {

        if (!em.contains(carrera)) {
            carrera = em.merge(carrera);
        }
        em.remove(carrera);

    }

    @Override
    public List<Carrera> findAllconInscriptosOrdernados() {
        String query = "SELECT ec.carrera FROM Estudiante_Carrera ec " +
                "GROUP BY ec.carrera " +
                "ORDER BY COUNT(ec.estudiante) DESC";
        return em.createQuery(query, Carrera.class).getResultList();
    }

    @Override public List<ConteoPorAnio> contarInscriptosPorCarreraYAnio() {
        String query = "SELECT NEW org.example.TP3.dto.ConteoPorAnio(ec.carrera.carrera, ec.inscripcion, COUNT(ec)) " +
                "FROM Estudiante_Carrera ec " +
                "GROUP BY ec.carrera.carrera, ec.inscripcion " +
                "ORDER BY ec.carrera.carrera, ec.inscripcion";
        return em.createQuery(query, ConteoPorAnio.class).getResultList();
    }

    @Override public List<ConteoPorAnio> contarEgresadosPorCarreraYAnio() {
        String query = "SELECT NEW org.example.TP3.dto.ConteoPorAnio(ec.carrera.carrera, ec.graduacion, COUNT(ec)) " +
                "FROM Estudiante_Carrera ec " +
                "WHERE ec.graduacion <> 0 " +
                "GROUP BY ec.carrera.carrera, ec.graduacion " +
                "ORDER BY ec.carrera.carrera, ec.graduacion";
        return em.createQuery(query, ConteoPorAnio.class).getResultList();
    }
}
