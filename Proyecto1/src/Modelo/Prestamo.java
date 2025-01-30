
package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Prestamo implements Serializable{
    
    private Estudiante estudiante;
    private Programacion programacion;

    public Prestamo(Estudiante estudiante) {
        this.estudiante = estudiante;
        LocalTime horaInicio = LocalTime.now();
        this.programacion = new Programacion(LocalDate.now(), horaInicio, horaInicio.plusHours(1));
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
    
    public boolean isActivo() {
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        return programacion.getDia().equals(fechaActual) && horaActual.isBefore(programacion.getHoraFin());
    }
    
    public void asignarProgramacion(){
        LocalTime horaInicio = LocalTime.now();
        LocalTime horaFin = horaInicio.plusMinutes(60);
        LocalDate fechaActual = LocalDate.now();
        this.programacion = new Programacion(fechaActual, LocalTime.of(horaInicio.getHour(), horaInicio.getMinute()), LocalTime.of(horaFin.getHour(), horaFin.getMinute()));
    }
}
