package Modelo;

import Excepciones.LaboratorioEnMantenimientoException;
import Excepciones.MantenimientoExistenteException;
import Excepciones.MantenimientoInvalidoException;
import Excepciones.ReservaInexistenteException;
import Excepciones.SinPuestoDisponibleException;
import Util.IList;
import Util.IQueue;
import Util.Listas;
import Util.Queue;
import Util.Singleton;
import java.io.Serializable;
import java.time.LocalDate;

public class Laboratorio implements Serializable {

    private int identificador;
    private Puesto[][] puestos;
    private int cantidadPuestos;
    private IQueue<Estudiante> colaPrestamos;
    private IList<Mantenimiento> mantenimientos;

    public Laboratorio() {
        this.puestos = new Puesto[2][4];
        this.cantidadPuestos = 8;
        this.colaPrestamos = new Queue<>();
        this.mantenimientos = new Listas<>();
        initPuestos();
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public Puesto[][] getPuestos() {
        return puestos;
    }

    public void setPuestos(Puesto[][] puestos) {
        this.puestos = puestos;
    }

    public int getCantidadPuestos() {
        return cantidadPuestos;
    }

    public void setCantidadPuestos(int cantidadPuestos) {
        this.cantidadPuestos = cantidadPuestos;
    }

    public IList<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    public void generarMatrizPuestos(int cantidadPuestos) {
        int filas = cantidadPuestos / 4;
        Puesto[][] nuevosPuestos;

        if (cantidadPuestos % 4 != 0) {
            nuevosPuestos = new Puesto[filas + 1][];

            for (int i = 0; i < filas; i++) {
                nuevosPuestos[i] = new Puesto[4];
            }
            int puestosFaltantes = cantidadPuestos % 4;
            nuevosPuestos[nuevosPuestos.length - 1] = new Puesto[puestosFaltantes];
        } else {
            nuevosPuestos = new Puesto[filas][4];
        }
        cambiarEstadoEstudianteCambioPuestos();
        this.cantidadPuestos = cantidadPuestos;
        this.puestos = nuevosPuestos;
        initPuestos();
        Singleton.getINSTANCIA().escribirLaboratorios();
    }

    public void initPuestos() {
        int identificador = 1;
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                puestos[i][j] = new Puesto("" + identificador);
                identificador += 1;
            }
        }
    }

    public int getFilas() {
        int filas = cantidadPuestos / 4;
        if (cantidadPuestos % 4 != 0) {
            return filas + 1;
        }
        return filas;
    }

    //Reservas
    public void actualizarReservas(Estudiante estudiante) {
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                puestos[i][j].actualizarReservas(estudiante);
            }
        }
    }

    private boolean validarReservaActiva() {
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                IList<Reserva> reservas = puestos[i][j].getReservas();
                for (int k = 0; k < reservas.size(); k++) {
                    if (reservas.get(k).getEstudiante().isTieneReserva().equals(Estudiante.RESERVA_ACTIVA)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean validarReservaEstudiante(Estudiante estudiante) {
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                IList<Reserva> reservas = puestos[i][j].getReservas();
                for (int k = 0; k < reservas.size(); k++) {
                    if (reservas.get(k).getEstudiante().isTieneReserva().equals(Estudiante.RESERVA_ACTIVA)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean tieneReservaEstudiante(Estudiante estudiante) {
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                if (puestos[i][j].tieneReservaEstudiante(estudiante)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void cancelarReserva(Estudiante estudiante) throws ReservaInexistenteException {
        boolean reservaCancelada = false;
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                try {
                    puestos[i][j].cancelarReserva(estudiante);
                    reservaCancelada = true;
                    break;
                } catch (ReservaInexistenteException ex) {
                }
            }
            if (reservaCancelada) {
                break;
            }
        }
        if (!reservaCancelada) {
            throw new ReservaInexistenteException();
        }
    }

    public void cancelarReservasActivas() throws ReservaInexistenteException {
        if (!validarReservaActiva()) {
            throw new ReservaInexistenteException();
        }
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                puestos[i][j].cancelarReservasActivas();
            }
        }
    }

    public void cambiarEstadoEstudianteCambioPuestos() {
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                IList<Reserva> reservas = puestos[i][j].getReservas();
                for (int k = 0; k < reservas.size(); k++) {
                    Reserva reserva = reservas.get(i);
                    reserva.getEstudiante().setTieneReserva(Estudiante.SIN_RESERVA);
                    Notificacion notificacion = new Notificacion();
                    notificacion.notificacionPorModificacionPuestos(reservas.get(k));
                    reservas.get(k).getEstudiante().agregarNotificacion(notificacion);
                }
            }
        }
        Singleton.getINSTANCIA().escribirUsuarios();
    }

    //Prestamos
    public boolean verificarPrestamoActivoEnLaboratorio(String id) {
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                Puesto puesto = puestos[i][j];
                if (puesto.getPrestamo() != null) {
                    if (puesto.getPrestamo().getEstudiante().getId().equals(id) && puesto.getPrestamo().isActivo()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Cola Prestamos
    public Puesto buscarPuestoDisponible() throws SinPuestoDisponibleException {
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                if (puestos[i][j].getEstado().equals(Puesto.DISPONIBLE)) {
                    return puestos[i][j];

                }
            }
        }
        throw new SinPuestoDisponibleException();
    }

    public boolean validarOcupacionLaboratorio() {
        int contador = 0;
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                if (puestos[i][j].getPrestamo() != null) {
                    contador += 1;
                }
            }
        }
        return contador == cantidadPuestos;
    }

    public boolean isColaPrestamoVacia() {
        return colaPrestamos.isEmpty();
    }

    public Estudiante getSiguienteEnCola() {
        if (colaPrestamos.isEmpty()) {
            return null;
        }
        return colaPrestamos.peek();
    }

    public void anadirEstudianteACola(Estudiante estudiante) {
        colaPrestamos.enQueue(estudiante);
        Singleton.getINSTANCIA().escribirLaboratorios();
    }

    public void sacarEstudianteDeCola() {
        if (!validarOcupacionLaboratorio() && !colaPrestamos.isEmpty()) {
            for (int i = 0; i < puestos.length; i++) {
                for (int j = 0; j < puestos[i].length; j++) {
                    if (puestos[i][j].getPrestamo() == null || !puestos[i][j].getPrestamo().isActivo()) {
                        Estudiante estudiante = colaPrestamos.deQueue();
                        Prestamo prestamo = new Prestamo(estudiante);
                        puestos[i][j].setPrestamo(prestamo);
                        puestos[i][j].actualizarEstado();
                        
                        Notificacion notificacion = new Notificacion();
                        notificacion.notificacionPrestamo(prestamo);
                        estudiante.agregarNotificacion(notificacion);
                        
                        Singleton.getINSTANCIA().escribirUsuarios();
                        Singleton.getINSTANCIA().escribirLaboratorios();
                        break;
                    }
                }
                if (colaPrestamos.isEmpty()) {
                    break;
                }
            }
        }
    }

    //Mantenimiento
    
    public void actualizarEstadoMantenimientos() {
        LocalDate fechaActual = LocalDate.now();
        for (int i = 0; i < mantenimientos.size(); i++) {
            Mantenimiento mantenimiento = mantenimientos.get(i);
            if (mantenimiento.getDia().isBefore(fechaActual)) {
                mantenimiento.setEstado(Mantenimiento.HECHO);
            }
        }
    }
    
    public boolean isMantenimiento(){
        LocalDate fechaActual = LocalDate.now();
        for (int i = 0; i < mantenimientos.size(); i++) {
            Mantenimiento mantenimiento = mantenimientos.get(i);
            if(mantenimiento.getEstado().equals(Mantenimiento.PENDIENTE) && mantenimiento.getDia().equals(fechaActual)){
                return true;
            }
        }
        return false;
    }
    public void validarFechaMantenimiento(Mantenimiento mantenimiento) throws MantenimientoInvalidoException {
        LocalDate fechaActual = LocalDate.now();
        if (mantenimiento.getDia().isBefore(fechaActual) || mantenimiento.getDia().isEqual(fechaActual)) {
            throw new MantenimientoInvalidoException();
        }
    }

    public void validarMantenimientoExistente(Mantenimiento mantenimiento) throws MantenimientoExistenteException {
        for (int i = 0; i < mantenimientos.size(); i++) {
            Mantenimiento mantenimientoExisrente = mantenimientos.get(i);
            if(mantenimientoExisrente.getDia().equals(mantenimiento.getDia())){
                throw new MantenimientoExistenteException();
            }
        }
    }

        public void validarReservaPorMantenimiento(LocalDate fechaReserva) throws LaboratorioEnMantenimientoException{
        for(int i = 0; i < mantenimientos.size(); i++){
            if(mantenimientos.get(i).getDia().equals(fechaReserva)){
                throw new LaboratorioEnMantenimientoException();
            }
        }
    }
    public void agregarMantenimiento(Mantenimiento mantenimiento) throws MantenimientoInvalidoException, MantenimientoExistenteException {
        validarFechaMantenimiento(mantenimiento);
        validarMantenimientoExistente(mantenimiento);
        mantenimientos.agregar(mantenimiento);
        cancelarReservasMantenimiento(mantenimiento.getDia());
        Singleton.getINSTANCIA().escribirLaboratorios();
    }
    
    public void cancelarReservasMantenimiento(LocalDate dia){
        for(int i = 0; i < puestos.length; i++){
            for(int j = 0; j < puestos[i].length; j++){
                IList<Reserva>reservas = puestos[i][j].getReservas();
                for(int k = 0; k < reservas.size(); k++){
                    if(reservas.get(k).getProgramacion().getDia().equals(dia)){
                        Notificacion notificacion = new Notificacion();
                        notificacion.notificacionPorMantenimiento(reservas.get(k));
                        reservas.get(k).getEstudiante().agregarNotificacion(notificacion);
                        
                        reservas.get(k).getEstudiante().setTieneReserva(Estudiante.SIN_RESERVA);
                        reservas.eliminar(k);
                        k--;
                    }
                }
            }
        }
        Singleton.getINSTANCIA().escribirUsuarios();
        Singleton.getINSTANCIA().escribirLaboratorios();
    }
    
}
