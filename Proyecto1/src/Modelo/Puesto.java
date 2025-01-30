package Modelo;

import Excepciones.FechaReservaInvalidaException;
import Excepciones.FueraDeJornadaException;
import Excepciones.PuestoNoDisponibleException;
import Excepciones.ReservaExistenteException;
import Excepciones.ReservaInexistenteException;
import Excepciones.ReservaProximaException;
import Excepciones.TiempoReservaInvalidoException;
import Util.IList;
import Util.Listas;
import Util.Singleton;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Puesto implements Serializable {

    private String estado;
    private String identificador;
    private IList<Reserva> reservas;
    private Prestamo prestamo;

    public static final String DISPONIBLE = "Disponible";
    public static final String OCUPADO = "Ocupado";

    public Puesto(String identificador) {
        this.identificador = identificador;
        this.estado = DISPONIBLE;
        this.reservas = new Listas<>();
        this.prestamo = null;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public IList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(IList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    
    public void actualizarEstado() {
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        if (prestamo != null) {
            if (hora.isAfter(prestamo.getProgramacion().getHoraFin())) {
                finalizarPrestamo();
            } else {
                estado = OCUPADO;
                return;
            }
        }
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            Programacion programacion = reserva.getProgramacion();
            if (fecha.equals(programacion.getDia())) {
                if (hora.isAfter(programacion.getHoraInicio()) && hora.isBefore(programacion.getHoraFin())) {
                    estado = OCUPADO;
                    return;
                }
            }
        }
        estado = DISPONIBLE;
    }

    //Reservas
    public void actualizarReservas(Estudiante estudiante) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            LocalDate fechaActual = LocalDate.now();
            LocalTime horaActual = LocalTime.now();
            if (reserva.getProgramacion().getDia().isBefore(fechaActual)) {
                estudiante.setTieneReserva(Estudiante.SIN_RESERVA);
                reservas.eliminar(reserva);
                i--;
            } else if (reserva.getProgramacion().getDia().equals(fechaActual) && reserva.getProgramacion().getHoraFin().isBefore(horaActual)) {
                estudiante.setTieneReserva(Estudiante.SIN_RESERVA);
                reservas.eliminar(reserva);
                i--;
            }
        }
        Singleton.getINSTANCIA().escribirLaboratorios();
        Singleton.getINSTANCIA().escribirUsuarios();
    }

    public void validarTiempoReserva(Reserva nuevaReserva) throws ReservaExistenteException {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if (reserva.getProgramacion().getDia().equals(reserva.getProgramacion().getDia())) {
                LocalTime inicioPrimerReserva = reserva.getProgramacion().getHoraInicio();
                LocalTime finPrimerReserva = reserva.getProgramacion().getHoraFin();
                LocalTime inicioSegundaReserva = nuevaReserva.getProgramacion().getHoraInicio();
                LocalTime finSegundaReserva = nuevaReserva.getProgramacion().getHoraFin();
                if (inicioPrimerReserva.isBefore(finSegundaReserva) && (finPrimerReserva.isAfter(inicioSegundaReserva))) {
                    throw new ReservaExistenteException();
                }
            }
        }
    }

    public void validarFecha(Programacion programacion) throws FechaReservaInvalidaException {
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.isAfter(programacion.getDia()) || fechaActual.equals(programacion.getDia())) {
            throw new FechaReservaInvalidaException();
        }
    }

    public void validarJornada(Programacion programacion) throws FueraDeJornadaException {
        LocalTime horaInicio = programacion.getHoraInicio();
        LocalTime horaFin = programacion.getHoraFin();
        if (horaInicio.isBefore(LocalTime.of(7, 0)) || horaFin.isAfter(LocalTime.of(22, 0))) {
            throw new FueraDeJornadaException();
        }
        if(programacion.getDia().getDayOfWeek().equals(DayOfWeek.SATURDAY) || programacion.getDia().getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            throw new FueraDeJornadaException();
        }
    }

    public void validarDuracion(Reserva reserva) throws TiempoReservaInvalidoException {
        long duracion = ChronoUnit.HOURS.between(reserva.getProgramacion().getHoraInicio(), reserva.getProgramacion().getHoraFin());
        if (duracion != 1) {
            throw new TiempoReservaInvalidoException();
        }
    }
    
    public boolean tieneReservaEstudiante(Estudiante estudiante) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getEstudiante().getId().equals(estudiante.getId())) {
                return true;
            }
        }
        return false;
    }

    public void agregarReserva(Reserva reserva) throws ReservaExistenteException, TiempoReservaInvalidoException, FechaReservaInvalidaException, FueraDeJornadaException {
        validarDuracion(reserva);
        validarTiempoReserva(reserva);
        validarFecha(reserva.getProgramacion());
        validarJornada(reserva.getProgramacion());
        reservas.agregar(reserva);
        reserva.getEstudiante().setTieneReserva(Estudiante.RESERVA_ACTIVA);
        Singleton.getINSTANCIA().escribirLaboratorios();
        Singleton.getINSTANCIA().escribirUsuarios();
    }

    public void cancelarReserva(Estudiante estudiante) throws ReservaInexistenteException {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getEstudiante().getId().equals(estudiante.getId())) {
                estudiante.setTieneReserva(Estudiante.SIN_RESERVA);
                reservas.eliminar(i);
                Singleton.getINSTANCIA().escribirLaboratorios();
                Singleton.getINSTANCIA().escribirUsuarios();
                return;
            }
        }
        throw new ReservaInexistenteException();
    }
       
    public void cancelarReservasActivas() {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            LocalDate fechaActual = LocalDate.now();
            if (reserva.getProgramacion().getDia().isAfter(fechaActual)) {
                reservas.eliminar(reserva);
                i--;
            }
        }
        Singleton.getINSTANCIA().escribirLaboratorios();
        Singleton.getINSTANCIA().escribirUsuarios();
    }


   //Prestamos
    
    public void validarReservaProxima(LocalTime horaInicio)throws ReservaProximaException{
       LocalTime horaSiguiente = horaInicio.plusHours(1); 
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            LocalTime horaInicioReserva = reserva.getProgramacion().getHoraInicio();
            if(horaInicioReserva.equals(horaSiguiente) || (horaInicioReserva.isAfter(horaInicio) && horaInicioReserva.isBefore(horaSiguiente))){
                throw new ReservaProximaException();
            }
        }
    }
        
    public void agregarPrestamo(Estudiante estudiante) throws PuestoNoDisponibleException, ReservaProximaException {
        LocalTime horaInicio = LocalTime.now(); 
        validarReservaProxima(horaInicio);
         if (estado.equals(OCUPADO)) {
            throw new PuestoNoDisponibleException();
        }
        if (prestamo != null) {
            throw new PuestoNoDisponibleException();
        }
        prestamo = new Prestamo(estudiante);
        estado = OCUPADO;
        Singleton.getINSTANCIA().escribirLaboratorios();
    }

    public void finalizarPrestamo() {
        if (prestamo != null) {
            estado = DISPONIBLE;
            prestamo = null;
            Singleton.getINSTANCIA().escribirLaboratorios();
        }
    }
}
