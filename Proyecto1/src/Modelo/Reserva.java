
package Modelo;

import java.io.Serializable;

public class Reserva implements Serializable{

    private Estudiante estudiante;
    private Programacion programacion;

    public Reserva(Estudiante estudiante, Programacion programacion) {
        this.estudiante = estudiante;
        this.programacion = programacion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Programacion getProgramacion() {
        return programacion;
    }

    public void setProgramacion(Programacion programacion) {
        this.programacion = programacion;
    }    
    
    @Override
    public String toString(){
        return programacion.getHoraInicio()+"-"+programacion.getHoraFin();
    }
}
