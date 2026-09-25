package org.example.TP2.service.impl;

import org.example.TP2.entidades.Carrera;
import org.example.TP2.repository.CarreraRepository;
import org.example.TP2.service.CarreraService;

import java.util.List;

public class CarreraServiceImpl implements CarreraService {

    private CarreraRepository carreraRepository;

    public CarreraServiceImpl(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    @Override
    public List<Carrera> listarCarrerasConInscriptosOrdenados() {
        return carreraRepository.findAllconInscriptosOrdernados();
    }
}
