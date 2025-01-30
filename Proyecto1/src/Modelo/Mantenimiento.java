
package Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Mantenimiento implements Serializable {
    
    private String motivo;
    private String estado;
    private LocalDate dia;
    
    public static final String PENDIENTE = "Pendiente";
    public static final String HECHO = "Hecho";

    public Mantenimiento(String motivo, LocalDate dia) {
        this.motivo = motivo;
        this.dia = dia;
        this.estado = PENDIENTE;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }
    
}
