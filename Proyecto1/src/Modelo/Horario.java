package Modelo;

import Excepciones.DiaInvalidoException;
import java.io.Serializable;
import java.time.DayOfWeek;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import java.time.LocalTime;

public class Horario implements Serializable {

    private LocalTime horaInicio;
    private LocalTime horaFin;
    private DayOfWeek diaSemana;

    public Horario(LocalTime horaInicio, LocalTime horaFin, DayOfWeek dia) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diaSemana = dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String convertirDia() throws DiaInvalidoException {
        switch (diaSemana) {
            case MONDAY:
                return "Lunes";
            case TUESDAY:
                return "Martes";
            case WEDNESDAY:
                return "Miercoles";
            case THURSDAY:
                return "Jueves";
            case FRIDAY:
                return "Viernes";
        }
        throw new DiaInvalidoException();
    }

    @Override
    public String toString() {
        return diaSemana + " - " + horaInicio + " - " + horaFin;
    }

}
