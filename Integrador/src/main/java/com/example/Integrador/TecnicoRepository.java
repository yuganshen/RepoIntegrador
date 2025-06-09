package com.example.Integrador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, String> {
    // Puedes agregar métodos personalizados si los necesitas
    List<Tecnico> findByEspecialidad(String especialidad);
}
