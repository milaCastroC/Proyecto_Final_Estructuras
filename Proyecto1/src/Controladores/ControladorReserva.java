package Controladores;

import Excepciones.FechaReservaInvalidaException;
import Excepciones.FueraDeJornadaException;
import Excepciones.LaboratorioEnMantenimientoException;
import Excepciones.ReservaExistenteException;
import Excepciones.ReservaInexistenteException;
import Excepciones.TiempoReservaInvalidoException;
import Excepciones.UsuarioInexistenteException;
import Excepciones.UsuarioNoCoincideException;
import Modelo.Estudiante;
import Modelo.Laboratorio;
import Modelo.Persona;
import Modelo.Puesto;
import Modelo.Reserva;
import Util.IList;
import Util.Singleton;

public class ControladorReserva {

    Puesto puesto;

    public ControladorReserva(Puesto puesto) {
        this.puesto = puesto;
    }

    public Persona buscarEstudiante(String id) throws UsuarioInexistenteException, UsuarioNoCoincideException {
        IList<Persona> usuarios = Singleton.getINSTANCIA().getUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            Persona persona = usuarios.get(i);
            if (usuarios.get(i).getId().equals(id)) {
                if (persona instanceof Estudiante) {
                    return (Estudiante) usuarios.get(i);
                } else {
                    throw new UsuarioNoCoincideException();
                }
            }
        }
        throw new UsuarioInexistenteException();
    }

    public void agendarReserva(Reserva reserva, Laboratorio laboratorio) throws ReservaExistenteException, TiempoReservaInvalidoException, FechaReservaInvalidaException, FueraDeJornadaException, LaboratorioEnMantenimientoException {
        if (reserva.getEstudiante().isTieneReserva().equals(Estudiante.RESERVA_ACTIVA)) {
            throw new ReservaExistenteException();
        }
        laboratorio.validarReservaPorMantenimiento(reserva.getProgramacion().getDia());
        puesto.agregarReserva(reserva);
    }

    public void cancelarReserva(Estudiante estudiante) throws ReservaInexistenteException {
        Laboratorio[][] laboratorios = Singleton.getINSTANCIA().getLaboratorios();
        boolean reservaCancelada = false;
        for (int i = 0; i < laboratorios.length; i++) {
            for (int j = 0; j < laboratorios[i].length; j++) {
                try {
                    laboratorios[i][j].cancelarReserva(estudiante);
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

}
