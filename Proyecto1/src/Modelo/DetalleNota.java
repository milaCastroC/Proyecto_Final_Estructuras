
package Modelo;

import java.io.Serializable;

public class DetalleNota implements Serializable{
    
    private Estudiante estudiante;
    private double valor;

    public DetalleNota(Estudiante estudiante, double valor) {
        this.estudiante = estudiante;
        this.valor = valor;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
