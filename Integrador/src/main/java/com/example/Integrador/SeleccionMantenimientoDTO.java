package com.example.demInte;

import java.time.LocalDate;
import java.util.List;

public class SeleccionMantenimientoDTO {
    private List<String> tiposSeleccionados;
    private LocalDate fechaSalida;

    public List<String> getTiposSeleccionados() {
        return tiposSeleccionados;
    }

    public void setTiposSeleccionados(List<String> tiposSeleccionados) {
        this.tiposSeleccionados = tiposSeleccionados;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
