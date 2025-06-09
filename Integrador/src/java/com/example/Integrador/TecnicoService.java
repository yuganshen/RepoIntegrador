package com.example.Integrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico buscarPorId(String id) {
        return tecnicoRepository.findById(id).orElse(null);
    }

    public void guardar(Tecnico tecnico) {
        tecnicoRepository.save(tecnico);
    }

    public void eliminar(String id) {
        tecnicoRepository.deleteById(id);
    }

    // Métodos adicionales según tu lógica de negocio
}
